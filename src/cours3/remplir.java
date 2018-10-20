package cours3;


	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.FlowLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.BufferedReader;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.io.Reader;
	import java.io.UnsupportedEncodingException;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import javax.swing.JButton;
	import javax.swing.JFileChooser;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JMenuItem;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JProgressBar;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.filechooser.FileNameExtensionFilter;
	import com.mysql.jdbc.Statement;

		
		public class remplir extends Thread {
		private BufferedReader br2;
		private BufferedReader br;


	/*	static void ext(JMenuItem newMenuItem)
		{
			newMenuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Thread t=new Thread(new extraction());
					t.start();
					
				}
				
			});
		}*/

		@Override
		public void run() {
			Statement s;
			// TODO Auto-generated method stub
			String corpus=null,dico=null;
			//le frame qui contient les substances dans subst et corpus
			JFrame f=new JFrame("extraction");
			JTextArea lab1 = new JTextArea();
			lab1.setSize(250, 400);
			lab1.setVisible(true);
		
	        lab1.setEditable(false);    
	        JScrollPane sp=new JScrollPane(lab1);
	        
	        sp.setPreferredSize(new Dimension(250,400));
	        sp.setVisible(true);
	        
	        FlowLayout desposition=new FlowLayout();
	        f.setLayout(desposition);
	        f.setSize(300, 500);
	        f.setLocation(500, 0);
	        JPanel pan=new JPanel();
	        f.add(pan);
	        
	        pan.setPreferredSize(new Dimension(300, 450));
			
	        // la vitesse de progression
	        String cmp= JOptionPane.showInputDialog("Donner la vitesse de progression");  
			 int a=Integer.parseInt(cmp);
			 
			 JProgressBar barre=new JProgressBar();
			JLabel Label=new JLabel("traitement:");
			
	         //Choisir à partir du repertoire le fichier corpus                
			barre.setStringPainted(true);
			JButton open= new JButton();
			JFileChooser f1=new JFileChooser();
			f1.setCurrentDirectory(new java.io.File("."));
			f1.setDialogTitle("Choisir le fichier ''corpus''");
			f1.setFileSelectionMode(JFileChooser.FILES_ONLY);
			f1.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter=new FileNameExtensionFilter("TXT file", "txt");
			f1.addChoosableFileFilter(filter);
			if(f1.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
				corpus=f1.getSelectedFile().getAbsolutePath();
			System.out.println(corpus);
			
			//Choisir à partir du repertoire le fichier subst
			JFileChooser f2=new JFileChooser();
			f2.setCurrentDirectory(new java.io.File("."));
			f2.setDialogTitle("choisir le fichier ''subst.dic''");
			f2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			f2.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter1=new FileNameExtensionFilter("DIC file", "dic");
			f2.addChoosableFileFilter(filter1);
			if(f2.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
			dico=f2.getSelectedFile().getAbsolutePath();
			System.out.println(dico);
			
			pan.add(Label);
			pan.add(barre);
			pan.add(sp);
	        f.setVisible(true);
			
			//fichier de sortie  html
			PrintWriter ecrire = null ;
	        try {
	ecrire =new PrintWriter("sortie.html","UTF-16LE");
	} catch (FileNotFoundException ex) {
	Logger.getLogger(Asp4Vidal.class.getName()).log(Level.SEVERE, null, ex);
	} catch (UnsupportedEncodingException ex) {
	Logger.getLogger(Asp4Vidal.class.getName()).log(Level.SEVERE, null, ex);
	}
	        ecrire.write("\uFEFF");
			
	        //base de donnees
	        
	        String req;
	    	//Statement s;
	    	
	    		java.sql.Connection c = null;
	    		//connection a la base de donnees
	    		try{
	    		c =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/medicaments","root","");
	    		System.out.println("la base est connectee");
	    	}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		//vider la table
	    		/*try{
	    		req="delete from medoc ";
	    		s=(Statement) c.createStatement();
	    		s.executeLargeUpdate(req);
	    		}catch(Exception e){System.out.println(e.toString()); System.exit(1); }
	        */
	        
			
			try{
				String ligne;
				InputStream ips=new FileInputStream(dico);
				InputStreamReader ipsr=new InputStreamReader(ips,"UTF-16");
				br = new BufferedReader(ipsr);
				int i=0;
				String sub=null;
				int line_count=0;
				int prog=1;
				
				//Calculer le nombre de lignes de subst
				InputStream ips0 = new FileInputStream(dico);
				InputStreamReader ipsr0=new InputStreamReader(ips0,"UTF-16");
				BufferedReader br0 = new BufferedReader(ipsr0);
				long nbr_ligne=br0.lines().count();
				br0.close();
				int n=Math.toIntExact(nbr_ligne);
				//lire le fichier subst par ligne et parcourir à chaque fois tout le fichier corpus
				//Boucle principale qui lit subst
				br = new BufferedReader(ipsr);
				while((ligne=br.readLine())!=null){
					barre.setValue(barre.getValue()+1);
					prog++;
					String tab[]=ligne.split(",.N");
					sub=tab[0];
					
					String ligne2;
					InputStream ips2=new FileInputStream(corpus);
					InputStreamReader ipsr2=new InputStreamReader(ips2,"UTF-16");
					br2 = new BufferedReader(ipsr2);
					
					line_count=0;
					//Boucle qui lit corpus
					while((ligne2=br2.readLine())!=null)
					{
						line_count++;
						
						Pattern p=Pattern.compile("(.)*\\b"+sub+"\\b(.)*",Pattern.CASE_INSENSITIVE);
					Matcher m=p.matcher(ligne2);
					if(m.matches())
					{i++;
					
						System.out.println(sub+" "+i);
						lab1.append(sub+"\r\t"+line_count+"\n");
						//la vitesse de progression
						java.lang.Thread.sleep(a);
						try{
							//Inserer ces données au niveau de la table
							
						req="INSERT INTO medicaments.Table(Id, Substance, NumLigne) VALUES ('"+i+"','"+sub+"','"+line_count+")";
								//"INSERT INTO medicaments.Table(Id, substance ,NumLigne) VALUES ("+i+",'"+sub+"',"+line_count+")";
						s= (Statement) c.createStatement();
						s.executeLargeUpdate(req);
						ecrire.write("<span style=\"color:red\">"+sub+"</span><p>"+ligne2+"</p><p>numero:"+line_count+"</p>"+"\n");
						
							
						
						}catch(Exception e){}
						}
						
						
					}
					
					}
				
				
				}catch(Exception e){System.out.println(e.toString());}
			Label.setText("Terminer:");
			System.out.println("fin");
			ecrire.close();
			
		}
		
		
	/*	static void vider_base(JMenuItem newMenuItem)
		{newMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 //base de donnees
		        
		        String req;
		    	Statement s;
		    	
		    		java.sql.Connection c = null;
		    		//connection a la base
		    		try{
		    		
		    		c =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/base_simple","root","");
		    		
		    	}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    		//vider la table
		    		try{
		    		req="delete from medoc ";
		    		s=(Statement) c.createStatement();
		    		s.executeLargeUpdate(req);
		    		}catch(Exception e){System.out.println(e.toString()); System.exit(1); }
		    		JOptionPane.showMessageDialog(null, "la table est a present vide !");
		    		
			}
		});
			
		}
		*/

	}

