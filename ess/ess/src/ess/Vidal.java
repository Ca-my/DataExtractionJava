package ess;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Vidal { 
	public static void main(String[] args) throws IOException,InterruptedException, Exception{
		//instanciation du frame principal
	    Affiche2 a = new Affiche2("Extraction d'information" ,500,300) ;
		a.F() ;
		
		
		
	}

	
	
}
