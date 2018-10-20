package cours3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class verifCorpus extends Thread {
	public void run()  {	
		try {
		BufferedReader lire ;
		BufferedReader lire1 ;
		PrintWriter ecrire= new PrintWriter("nbr.txt");
		int i=0;
		lire = new BufferedReader (new FileReader ("subs.dic"));
		lire1 = new BufferedReader (new FileReader ("Corpus.txt"));
		String mot=null ;
			 while(true) { 
				 lire1 = new BufferedReader (new FileReader ("Corpus.txt"));
					String line= lire.readLine();
					if (line == null) break;
					if (line.matches("(.*),.N")){
					Pattern p=Pattern.compile("(.*),.N");
					Matcher m=p.matcher(line);
					
					if (m.find()){
						 mot = m.group(1);
					}
								
					while (true){
						String line1= lire1.readLine();
						if (line1 == null) break;
					
						//if (line1.matches("(.)*\\b"+sub+"\\b(.)*",Pattern.CASE_INSENSITIVE)){
							Pattern p1=Pattern.compile("(.)*\\b"+mot+"\\b(.)*",Pattern.CASE_INSENSITIVE);
							Matcher m1=p1.matcher(line1);
							
							if (m1.find()){
								ecrire.write("le medic est : "+mot);
						
								ecrire.write("\r\n");
							}
						//}
						
							
							} ecrire.close();
						
											
					}
			 
				 
			 	}} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		}
	


}