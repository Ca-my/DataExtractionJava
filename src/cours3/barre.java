package cours3;
import java.awt.*;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import javax.swing.*;
public class barre extends ThreadBarre{

	JFrame frame = new JFrame("Aspiration..");
	JProgressBar barre = new JProgressBar();
    JLabel UrlLabel = new JLabel();
	FlowLayout disposition = new FlowLayout();
	JProgressBar barre1 = new JProgressBar();
	JLabel UrlLabel1 = new JLabel();
	FlowLayout disposition1 = new FlowLayout();
	JPanel p = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JLabel BI =new JLabel();
	 JLabel BS =new JLabel();
	 JTextField bi =new JTextField();
	 JTextField bs =new JTextField();
	 JOptionPane job, job1, job2 ;
	
	// String url = "jdbc:mysql://127.0.0.1/medicaments";
	 Statement stat;
	
public barre() throws Exception,IOException, InterruptedException{
		int j=0,k=0,z=0;
     	try {
			Connection c =DriverManager.getConnection("jdbc:mysql://127.0.0.1/medicaments","root","");
			stat = c.createStatement();
		//String url1 = "jdbc:mysql://127.0.0.1/medicaments";
			   
			
		 } catch(SQLException e) {
			 e.printStackTrace();	
			 }
		
		frame.setSize(500, 200);
		barre.setStringPainted(true);
		frame.setLayout(disposition);
		frame.add(p);
		
		/*p.add(BI);
		BI.setText("la borne inf est :");
	    BI.setBounds(30, 30,150,150);
	    p.add(bi);
	    bi.setLocation(200,30);
		bi.setPreferredSize(new Dimension(70,30));*/
	    
	//	p.add(BS);
		//BS.setText("la borne sup est :");
		//BS.setBounds(30, 100,150,150);
		//p.add(bs);
	//	bs.setLocation(200,100);
		//bs.setPreferredSize(new Dimension(70,30));
		frame.setVisible(true);
		
		frame.add(UrlLabel);
		frame.add(barre);
		frame.add(p1);
		p1.add(UrlLabel);
		p1.add(barre);
		frame.setLayout(disposition1);
		frame.setLocation(400,200);
		frame.add(UrlLabel1);
		frame.add(barre1);
		frame.add(p2);
		p1.add(UrlLabel1);
		p1.add(barre1);
		
		barre1.setStringPainted(true);

		frame.setLayout(disposition1);

		frame.add(UrlLabel1);
		frame.add(barre1);
		PrintWriter ecrire= new PrintWriter("Fichier_Sortie.txt","UTF-16LE");
		ecrire.write("\uFEFF");
		PrintWriter medic= new PrintWriter("subs.dic","UTF-16LE");
		medic.write("\uFEFF");
		//PrintWriter nbr= new PrintWriter("nbr.txt","UTF-16LE");
		/*String BorneI=bi.getText();
		String BorneS=bs.getText();
		char inf = BorneI.charAt(0);
		char sup = BorneS.charAt(0);*/
		job2 = new JOptionPane();
		String inp =job2.showInputDialog(null, "URL", "entrez l'URL!", JOptionPane.QUESTION_MESSAGE);
		Pattern p1 = Pattern.compile("(.*-Substances-)" );
		Matcher m1 = p1.matcher(inp) ;
		if(m1.find()) 
			inp=m1.group(1);
		job = new JOptionPane();
		String inf =job.showInputDialog(null, "la borne inferieure", "entrez la borne inf !", JOptionPane.QUESTION_MESSAGE);
		char in = inf.charAt(0);
		job1 = new JOptionPane();
		String sup =job1.showInputDialog(null, "la borne superieure", "entrez la borne sup !", JOptionPane.QUESTION_MESSAGE);
		char su = sup.charAt(0);
	
		int nbrPage =0;
		
		for(char x=in;x<=su;x++){
			nbrPage++;
			}
for(char x=in;x<=su;x++)
		{
	String url_vidal = inp + x + ".htm";//"http://127.0.0.1/home/vidal-Sommaires-Substances-"+x+".htm";
		URL url = new URL(url_vidal);
		System.out.println("URL à aspirer ==>"+url);
	    BufferedReader lire = new BufferedReader (new InputStreamReader(url.openStream(),"UTF-8"));
	    BufferedReader lire1 = new BufferedReader (new FileReader ("corpus.txt"));
	    //BufferedReader lire = new BufferedReader (new FileReader(nom du fichier));
		ecrire.write("------------------------------------LA NOUVELLE PAGE EST----------------------------- "+x);
		
		while(true)
		{  
        
			String line= lire.readLine();
			System.out.println(line);
			if (line == null)
				break;
			ecrire.write(line);
			ecrire.write("\r\n");
			    
		if (line.matches(".*<a href=\"Substance/.*\">(.*)</a>")){
				Pattern p=Pattern.compile(".*<a href=\"Substance/.*\">(.*)</a>");
				Matcher m=p.matcher(line);
				while(m.find()){ 
					   medic.write(m.group(1));
					   String mot = m.group(1);
					   /* while(true) {
					   String line1= lire1.readLine();
					  
					   if (line1 == null)
							break;
					   
						   nbr.write("lalala");
						   nbr.write("\r\n");
						   z++;
					   if (line1.matches(".*"+mot+".*")){
						   nbr.write("le medic qui existe est : "+mot);
						   
						   
					   }}*/
					   
					 /* try {
						String req ="INSERT INTO medicaments.table(Id, Substance) VALUES ("+k+",'"+mot+"')";
					       stat.executeUpdate(req);
						 } catch(SQLException e) {
							 e.printStackTrace();	
							 }*/
				
					   medic.write(",.N");
				       medic.write("\r\n");
				       k++;}
			}
		
		} 	  for (int i=0; i<=100;i++) {  
			  java.lang.Thread.sleep(1);
			  barre1.setValue(i);
			  UrlLabel1.setText("URL==>"+x);
			   }
		//java.lang.Thread.sleep(2);
	    barre.setMaximum(nbrPage);
		barre.setValue(barre.getValue()+1);
		UrlLabel.setText("URL==>");
	
		}
//frame.setVisible(false);
ecrire.close();
		//medic.write("le nombre totale de meduc est"+k);
		medic.close();
	//	nbr.close();
		
		}


}

//}
//}