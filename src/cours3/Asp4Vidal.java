package cours3;
import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

//import com.mysql.jdbc.Connection;

import javax.swing.*;

public class Asp4Vidal {
	 static ThreadBarre b =new ThreadBarre();
static void Asp(JMenuItem newMenuItem,JMenuItem newMenuItem1,JMenuItem newMenuItem2,JMenuItem newMenuItem3,JMenuItem newMenuItem4,JMenuItem newMenuItem5) throws Exception{
	newMenuItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			try {
				b.start();
				} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
		  
		}
	}); 
	newMenuItem1.addActionListener(new ActionListener(){
		 nbrMedic n= new nbrMedic();
		public void actionPerformed(ActionEvent e){
			try {
				n.start();
				} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
		  
		}
		}
	); 
	newMenuItem2.addActionListener(new ActionListener(){
		nbrMedic n= new nbrMedic();
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}); 


newMenuItem3.addActionListener(new ActionListener(){
remplir v = new remplir();
public void actionPerformed(ActionEvent e){
	try {
		v.start();
		} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
}
});
newMenuItem4.addActionListener(new ActionListener(){
	vider i= new vider();
	public void actionPerformed(ActionEvent e){
		try {
		i.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
});
newMenuItem5.addActionListener(new ActionListener(){
	
	public void actionPerformed(ActionEvent e){
		try {
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
});
}

}

