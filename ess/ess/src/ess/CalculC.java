package ess;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculC extends JFrame {
	PrintWriter ecrire = null;
	BufferedReader corpus=null;
	Pattern p ;
	Matcher m ;
	int i=0 ;
	
	public CalculC() {
		String line ;
		String chaine = JOptionPane.showInputDialog("Donner la chaine de la cooccurrence ( de 2 à 5 tokens )"); 
		String ch = Character.toString(chaine.charAt(0)); ;
	
		if ( ch.equals(" ") ) JOptionPane.showMessageDialog(null , "Erreur : Chaine qui contient un espace au début !! ");
		else {
			p = Pattern.compile("[0-9].*",Pattern.CASE_INSENSITIVE );
			m = p.matcher(chaine) ;
			if ( m.matches() )  JOptionPane.showMessageDialog(null , "Erreur : Chaine qui contient  une valeur numérique au début !! ");
		else {
			String [] nbr = chaine.split(" ") ;
			String [] nbr2 = chaine.split("'") ;
			System.out.println(nbr2.length);
			if( (nbr.length+nbr2.length)-1 > 5 )
				JOptionPane.showMessageDialog(null , " Nombre de token sup à 5 ");
			else {
		
				try {
					ecrire = new PrintWriter("Fichier.txt","UTF-16LE");
					corpus = new BufferedReader(new InputStreamReader(new FileInputStream( "corpus.txt" ), "UTF-16"));
					ecrire.write("\uFEFF");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				try {
					while( (line=corpus.readLine()) != null ) {
						p = Pattern.compile("(.)*\\b"+chaine+"\\b(.)*",Pattern.CASE_INSENSITIVE );
						m = p.matcher(line) ;
						if(m.matches()){
							i++ ;
						}	
					}
			
					ecrire.write("Le nbr de cooc de : "+chaine + " est : " + i );
			
					JOptionPane.showMessageDialog(null , " fichier creez ");
			
					ecrire.close();
					corpus.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
		
			}
		}
	}
	
	
	}	
	

}

