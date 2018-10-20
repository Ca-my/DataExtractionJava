package ess;
import javax.swing.JMenu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Affiche2 extends Affiche1 {

	public Affiche2() {
		super();
	}
	public Affiche2(String chaine, int largeur, int hauteur) {
		super(chaine, largeur,hauteur);
	}
	
	public void F() throws Exception{
		super.F();
		JMenuBar menuBar = new JMenuBar(); 
		frameP.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Dictionnaire");
		JMenu corpus = new JMenu("Corpus");
		
		menuBar.add(menu); 
		menuBar.add(corpus); 
	
		JMenuItem newMenuItem = new JMenuItem("Aspiration");
		JMenuItem newMenuItem1 = new JMenuItem("Quitter");
		JMenuItem newMenuItem3 = new JMenuItem("Extraction");
		JMenuItem newMenuItem4 = new JMenuItem("Vider BDD");
		JMenuItem newMenuItem2 = new JMenuItem("Infos");
		JMenuItem newMenuItem5 = new JMenuItem("Calcul de cooccurrence");
		JMenuItem newMenuItem6 = new JMenuItem("Enrichir");
		menu.add(newMenuItem);
		corpus.add(newMenuItem3);
		corpus.add(newMenuItem4);
		corpus.add(newMenuItem5);
		menu.add(newMenuItem2);
		menu.add(newMenuItem6);
		menu.add(newMenuItem1);		
		
		//créer les raccourcis 
		menu.setMnemonic('D');
		corpus.setMnemonic('C');
		newMenuItem.setText("Aspiration \t Ctrl+A");
		newMenuItem.setMnemonic('A');	
		newMenuItem1.setText("Quitter \t Ctrl+Q");
		newMenuItem1.setMnemonic('Q');
		newMenuItem2.setText("Infos \t Ctrl+I");
		newMenuItem2.setMnemonic('I');
		newMenuItem6.setText("Enrichir \t Ctrl+R");
		newMenuItem6.setMnemonic('R');
		newMenuItem3.setText("Extraction \t Ctrl+E");
		newMenuItem3.setMnemonic('E');
		newMenuItem4.setText("Vider BDD \t Ctrl+V");
		newMenuItem4.setMnemonic('V');
		newMenuItem5.setText("Calcul de cooccurrence \t Ctrl+C");
		newMenuItem5.setMnemonic('C');
		newMenuItem1.addActionListener(new ActionQuitter());
		Asp4Vidal.Asp(newMenuItem);
		Asp4Vidal.Info(newMenuItem2);
		Asp4Vidal.Vider(newMenuItem4);
		Asp4Vidal.Extr(newMenuItem3);
		Asp4Vidal.Calcul(newMenuItem5);
	}

}
class ActionQuitter implements ActionListener
{
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	
}
