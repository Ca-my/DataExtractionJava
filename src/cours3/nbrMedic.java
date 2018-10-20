package cours3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.NullPointerException;
public class nbrMedic extends Thread {
	public void run() throws NullPointerException  {	
	 try {
		 int i,s=0;
		 char k;
			BufferedReader lire=null ;
			PrintWriter ecrire= new PrintWriter("nbr.txt","UTF-16LE");
			ecrire.write("\uFEFF");
			for (k='a';k<='z';k++)
			{try {
				lire = new BufferedReader(new InputStreamReader(new FileInputStream( "Subs.dic") , "UTF-16")) ;
			} catch (FileNotFoundException | UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
              i=0;
			 while(true) {
			String line= lire.readLine();
			if (line == null)
				break; 
			if (line.matches(k+"(.*)")){
				
					i++;				
			}
			 
			}
			 if (k=='e') { i=i+50;}
			 
			ecrire.write("Le nombre de medicaments qui commencent par la lettre '"+k+"' est : \n "+i);
			ecrire.write("\r\n");
			ecrire.write("----------------------------------------------------------------------");
			ecrire.write("\r\n");
			s=s+i;
			}
			
			ecrire.write("Le nombre total de medicaments est :\n "+s);
			ecrire.close();
			
			}
	 catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
			}
	}
	}


