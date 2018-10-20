package ess;


import java.awt.Frame;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.ClosedChannelException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.spi.DirectoryManager;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Asp4Vidal {

	static void Asp(JMenuItem newMenuItem) throws Exception{
		newMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				th t = new th() ;
				t.start();
			} });
	}
	
	static void Info(JMenuItem newMenuItem2) {
		newMenuItem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String sub=null ;
				int nS=0 , nT=0 ;
				String mot=null , c=null ;
				PrintWriter sortie = null;
				//Entrer fichier subs en lecture
				BufferedReader entree = null ;
				try {
					entree = new BufferedReader(new InputStreamReader(new FileInputStream( "Subs.dic") , "UTF-16")) ;
				} catch (FileNotFoundException | UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}
				//Entrer fichier NbrSubs en écriture
				try {
					sortie = new PrintWriter ("NbrSubs.txt" ,"UTF-16LE");
					sortie.write("\uFEFF");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//Calculer le nombre de médicaments de chaque lettre et le nombre total
				while (true)
				{
					nS=0;
					try {
						sub = entree.readLine() ;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if (sub == null) 
						break ;
					nS=1 ;
					
					Collator frCollator = Collator.getInstance(Locale.FRANCE);
					frCollator.setStrength(Collator.SECONDARY);
					//récupérer le premier caractère du médicament
					mot = Character.toString(sub.charAt(0)) ;	
					 c = Character.toString(sub.charAt(0)) ;
					 
				  while( frCollator.compare(mot, c) == 0  ) {					  
					  try {
						sub = entree.readLine() ;
					  } catch (IOException e1) {
						e1.printStackTrace();
					  }
					  if (sub == null) 
							break ;
					  mot = Character.toString(sub.charAt(0)) ;
					     nS++ ;
				  }
				  
				  sortie.println("Le nombre de medicament de la lettre : '" +c+"' :" +nS) ;
				  nT = nT + nS ;
				
			   }
				
			   sortie.println("nbre totale :" +nT) ;
			   sortie.close () ;
			   JOptionPane.showMessageDialog(null, "Fichier crée" );				 
			} });
	}

	static void Vider(JMenuItem newMenuItem) throws Exception{
	newMenuItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			 String url2 = "jdbc:mysql://127.0.0.1:3306/medicaments";
			 String utilisateur = "root";
			 String motDePasse = "";
			 //se connecter à la base de donnée
				Connection cn =null;
				try {
					cn = DriverManager.getConnection(url2,utilisateur,motDePasse);
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
				 java.sql.Statement s =null;
			 //requête pour vider la table des médicaments 	 
			 String req=" TRUNCATE `table` ;";
					
					try {
						s = cn.createStatement();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
					  try {
						int i = s.executeUpdate(req);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			
			 JOptionPane.showMessageDialog(null, "BDD Vider" );
					  
		} });
}

	static void Extr(JMenuItem newMenuItem) throws Exception{
	newMenuItem.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e) {	
			Th2 t = new Th2() ;
			t.start();
			
		} });
}

	static void Calcul(JMenuItem newMenuItem) throws Exception{
	newMenuItem.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e) {	
			Th3 t = new Th3() ;
			t.start();
			
		} });
}


}


