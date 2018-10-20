package ess;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Affiche1 extends JFrame {
	protected String chaine  ;
	protected int largeur;
	protected int hauteur;
	JFrame frameP;
    
	public Affiche1() {
		
	}
    
	public Affiche1(String chaine, int largeur, int hauteur) {
		this.chaine = chaine ;
		this.largeur = largeur;
		this.hauteur = hauteur;		
	}
    
	public void F() throws Exception{
		//Frame principal
		frameP = new JFrame (chaine);
		frameP.setSize(largeur,hauteur);
		frameP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameP.setVisible(true);
		frameP.setLocation(400,200);
		
	}
}
