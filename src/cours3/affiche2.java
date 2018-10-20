package cours3;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class affiche2 extends affiche1 {
	public affiche2(){
		super();
	}
public void F() throws Exception{
	super.F();
	JMenuBar menuBar = new JMenuBar();
	f.setJMenuBar(menuBar);
	JMenu menu = new JMenu("Dictionnaire");
	menuBar.add(menu);
	JMenuItem newMenuItem = new JMenuItem ("Aspiration");
	menu.add(newMenuItem);
	newMenuItem.setMnemonic('A'); 
	JMenuItem newMenuItem1 = new JMenuItem ("Infos");
	menu.add(newMenuItem1);
	newMenuItem1.setMnemonic('I');
	JMenuItem newMenuItem2 = new JMenuItem ("Quitter");
	menu.add(newMenuItem2);
	newMenuItem2.setMnemonic('Q');
	JMenuItem newMenuItem5 = new JMenuItem ("Enrichir");
	menu.add(newMenuItem5);
	newMenuItem5.setMnemonic('E');
	JMenu menu1 = new JMenu("Corpus");
	menuBar.add(menu1);
	JMenuItem newMenuItem3 = new JMenuItem ("Extraction");
	menu1.add(newMenuItem3);
	newMenuItem3.setMnemonic('X');
	JMenuItem newMenuItem4 = new JMenuItem ("Vider BDD");
	menu1.add(newMenuItem4);
	newMenuItem4.setMnemonic('V');
	JMenuItem newMenuItem6 = new JMenuItem ("Calcul de Cooccurrence");
	menu1.add(newMenuItem6);
	newMenuItem6.setMnemonic('L');
	f.setVisible(true);
    Asp4Vidal.Asp(newMenuItem,newMenuItem1,newMenuItem2,newMenuItem3,newMenuItem4,newMenuItem5);
    
}
}
