package ess;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.*;
import javax.swing.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Barre extends JFrame {
	JFrame frame = new JFrame ("Aspiration");
	JProgressBar barre1 = new JProgressBar();
	JProgressBar barre2 = new JProgressBar();
	Object[] alph = new Object[] {"-","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	JComboBox liste1 = new JComboBox(alph);
	JComboBox liste2 = new JComboBox(alph);
	 private JTextField jtf = new JTextField("URL");

	JLabel inf = new JLabel();
	JLabel sup = new JLabel();
	JLabel UrlLabel1 =new JLabel();
	JPanel pan1 =new JPanel();
	JLabel UrlLabel2 =new JLabel();
	JPanel pan2 =new JPanel();
	JPanel pan3 =new JPanel();
	FlowLayout disposition = new FlowLayout();
	Pattern p,p2 ;
	Matcher m,m2 ;
	 
	 
	JButton button = new JButton("Entrer") ;
	
	
	public Barre() throws IOException,InterruptedException{
		frame.setLayout(disposition);
		frame.setLocation(400,200);
		frame.add(pan1);
		frame.add(pan2);
		frame.add(pan3);
		frame.setSize(500,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		pan2.add(inf);
		inf.setText("La borne inf:");
		pan2.add(liste1);
		
		
		pan2.add(sup);
		sup.setText("La borne sup:");
		pan2.add(liste2);
		pan2.add(button) ;
		
		jtf.setPreferredSize(new Dimension(150, 30));
	    jtf.setForeground(Color.BLUE);
	    pan1.add(jtf);
	    barre1.setStringPainted(true);
	    barre1.setMaximum(100);
	    barre1.setMinimum(0);
	    pan3.add(UrlLabel1);
	    pan3.add(barre1);
	    barre2.setStringPainted(true);	
		barre2.setMaximum(100);
		barre2.setMinimum(0);
		pan3.add(UrlLabel2);
		pan3.add(barre2);
	
		button.addActionListener(new getTexte());
    

	}


   class getTexte implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String chaine = JOptionPane.showInputDialog("Donner la vitesse de l'aspiration "); 
		int v = Integer.parseInt(chaine) ;
		String Bi = liste1.getSelectedItem().toString();
		String Bs = liste2.getSelectedItem().toString();
		char mot1 [],mot2 [] ,l1,l2 ;
		mot1 = Bi.toCharArray();
		l1 = mot1[0];
		mot2 = Bs.toCharArray();
		l2=mot2[0];
		String str = jtf.getText() ;
		String str1 = null;
	    int i=0 ,k; char x;		
	    
	  //création et écriture dans le fichier Fichier_sortie
		PrintWriter ecrire = null;
		try {
			ecrire = new PrintWriter("Fichier_sortie.txt","UTF-16LE");
			ecrire.write("\uFEFF");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	  //création et écriture dans le fichier Subs.dic
		PrintWriter ecrireS = null;
		try {
			ecrireS = new PrintWriter("Subs.dic","UTF-16LE");
			ecrireS.write("\uFEFF");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		p = Pattern.compile("(.*-Substances-)" );
		m = p.matcher(str) ;
		if(m.find()) 
			str=m.group(1);
		
		int nbrT =0 ,nbrL;
        int nbrPage =0;
		
		for( x=l1;x<=l2;x++){
			nbrPage++;
			}
	    //Aspiration
	    for (x=l1; x<=l2 ; x++ ){	
	    	 try {
				java.lang.Thread.sleep(v);
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}
			barre1.setMaximum(nbrPage);
			barre1.setValue(barre1.getValue()+1);
			UrlLabel1.setText("URL ==> ");
			UrlLabel2.setText("URL ==> "+ x);	
			
			String url_vidal = str + x + ".htm";
			URL url = null;
			try {
				url = new URL(url_vidal);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			System.out.println("URL � aspirer ==>"+url);
			BufferedReader lire = null;
			try {
				lire = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			k=0;
						
			while( true ){
			
				String line = null;
				try {
					line = lire.readLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(line);
				if(line == null)
					break;
				p= Pattern.compile(".*<a href=\"Substance/.*\">(.*)</a>" );
				m = p.matcher(line) ;
				if(m.find()) {
					ecrireS.write(m.group(1)+",.N");
					nbrT++;
					ecrireS.write("\r\n");
				}
				ecrire.write(line);
				ecrire.write("\r\n");	
				barre2.setValue(k);
				k++;
			}		
			}
			ecrire.close();
			ecrireS.close();
			frame.setVisible(false);	  
	}
   }
}

