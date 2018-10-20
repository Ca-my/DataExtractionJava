package cours3;
import javax.swing.JFrame;
public class affiche1 extends JFrame  {
	public String nomFrame ;
	public int largeur ;
	public int hauteur;
	JFrame f;
	public affiche1(/*String nomFrame, int largeur, int hauteur*/) {
		super();
		this.nomFrame = /*nomFrame*/ "nom";
		this.largeur = /*largeu*/ 3;
		this.hauteur = /*hauteur*/5;
	}
public void F ()throws Exception 
{
    f = new JFrame ("EXTRACTION D'INFORMATION..");
	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	f.setSize(500, 500);
	f.setVisible(true);
}

}
