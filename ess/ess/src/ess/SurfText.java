package ess;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SurfText extends JFrame {
	
	JFrame f=new JFrame("extraction");
	JTextArea textPane = new JTextArea();
	JScrollPane sp = new JScrollPane(textPane);
    FlowLayout desposition = new FlowLayout();
    JPanel pan = new JPanel();
	JLabel Label = new JLabel("traitement:");
	JButton open = new JButton("Selectionner un fichier"); //nouveau bouton open
    JTextField status = new JTextField("Pas de fichier chargé!"); //nouveau champs de texte
    PrintWriter ecrire = null;
    BufferedReader corpus=null , subs=null ;
    
    int i =0;
    Th2 tt = new Th2() ;
    String corp = null , dico=null ;   
    
	public SurfText ( ){
		
		textPane.setSize(250, 400);
		textPane.setVisible(true);
		textPane.setEditable(false);    
        sp.setPreferredSize(new Dimension(250,400));
        sp.setVisible(true);
        f.setLayout(desposition);
        f.setSize(300, 500);
        f.setLocation(500, 100);
        f.add(pan);       
        pan.setPreferredSize(new Dimension(300, 450));	

		
		JFileChooser chooser = new JFileChooser();//création dun nouveau filechosser
		FileNameExtensionFilter imagesFilter ;
		imagesFilter = new FileNameExtensionFilter("txt" ,"txt" );
		//chooser.setMultiSelectionEnabled(true) ;
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(imagesFilter);
		chooser.setFileFilter(imagesFilter);
		chooser.setApproveButtonText("Choix du fichier..."); //intitulé du bouton
		chooser.setCurrentDirectory(new File ("C:\\Users\\ASUS\\Desktop\\ProjetAF "));
		 
		//affiche la boite de dialogue		
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && i==0)
	    	{	
			status.setText(chooser.getSelectedFile().getAbsolutePath()); //si un fichier est selectionné, récupérer le fichier puis son path et l'afficher dans le champs de texte
			 corp = chooser.getSelectedFile().getAbsolutePath();
				
				if(  chooser.getSelectedFile().getName().equals("corpus.txt")) { i=1 ;System.out.println(corp);}
				else
					  JOptionPane.showMessageDialog(null, "Veuillez sélectionner le corpus." );
			}
			 imagesFilter =new FileNameExtensionFilter("dic" ,"dic" );
			 chooser.addChoosableFileFilter(imagesFilter);
			 chooser.setFileFilter(imagesFilter);
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && i==1)
	    	{	
			status.setText(chooser.getSelectedFile().getAbsolutePath()); //si un fichier est selectionné, récupérer le fichier puis son path et l'afficher dans le champs de texte
			 dico = chooser.getSelectedFile().getAbsolutePath();
			 if( chooser.getSelectedFile().getName().equals("Subs.dic")) { i=2;	System.out.println(dico);}
			 else
			  JOptionPane.showMessageDialog(null, "Veuillez sélectionner le fichier subs." );
			}
			
			pan.add(Label);
			pan.add(sp);
	        f.setVisible(true);
		
		if (i==2) {
		    //entrer le corpus en lécture
			
			try {
				corpus = new BufferedReader(new InputStreamReader(new FileInputStream( corp ), "UTF-16"));
				subs = new BufferedReader(new InputStreamReader(new FileInputStream( dico ), "UTF-16"));
			} catch (UnsupportedEncodingException | FileNotFoundException e2) {
				e2.printStackTrace();
			}
		   //création du fichier html 
			
			try {
				ecrire = new PrintWriter("fich.html","UTF-16LE");
				ecrire.write("\uFEFF");
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		Pattern p,p2 ;
		Matcher m,m2 ;
		boolean b =true ;
		String url2 = "jdbc:mysql://127.0.0.1:3306/medicaments";
		 String utilisateur = "root";
		 String motDePasse = "";
		 //se connecter à la base de donnée
			Connection cn =null;
			try {
				cn = (Connection) DriverManager.getConnection(url2,utilisateur,motDePasse);
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			 java.sql.Statement s =null;
		
		String sub=null;
		int idf=1 , th=0 ;
		

		
		try {
			
			String str=null,ex=null ;
			int i = 1,l=0 ;
			
			while ( true) {
				String S = subs.readLine() ;
				if(S == null)
					break;
				//Suppression des caractères ,.N
				p = Pattern.compile("(.*),.N" );
				m = p.matcher(S) ;
				if(m.find()) 
					str=m.group(1);
				//b=true ;
				l=0;
				corpus = new BufferedReader(new InputStreamReader(new FileInputStream( corp ), "UTF-16"));
				//extraire les médicaments par substance active communs entre subs et corpus
				while (b ) {
					String line = corpus.readLine();
					l++ ;
					if(line == null)
						break;
					String g = ".*"+str+".*" ;
					p2 = Pattern.compile("(.)*\\b"+str+"\\b(.)*",Pattern.CASE_INSENSITIVE );
					m2 = p2.matcher(line) ;
					if(m2.matches()){
				
					System.out.println(m2.group()) ;
					System.out.println(str) ;
					
					textPane.append(str+"\r\t"+l+"\n");
						//insertion des médicaments par substance active dans la table de la BDD
						String req=" INSERT INTO medicaments.table (Idf,Substance,Numl) VALUES ('"+i+"','"+str+"','"+l+"' ) ;";
						
						try {
							s = cn.createStatement();
							 s.executeUpdate(req);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						ecrire.write("<span style =\"color:red\"> "+str +" </span></br>");
						ecrire.write("<p>"+line+" </p><p>numéro: "+l +"</p></br>");
						ecrire.write("\r\n");
						i++ ;
					//	textPane.append( str+"\r\t" +l+ "\n");	
						//b=false ;
					
					}
				}
				
			}
			System.out.println(i) ;
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		
		ecrire.close() ;
		try {
			corpus.close();
			subs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
