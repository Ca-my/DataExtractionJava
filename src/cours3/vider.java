package cours3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class vider extends Thread {
	
	public void run()  {
		Statement stat;	
		
		Connection c;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1/medicaments","root","");
			
			stat = c.createStatement();
			String req ="DELETE from medicaments.table where 1";
			stat.executeUpdate(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//String url1 = "jdbc:mysql://127.0.0.1/medicaments";
		 
		}
		}

