package cours3;

import java.net.URL;
import java.io.*;
import javax.swing.*;



public class ThreadBarre extends Thread {

	public void run() {
		try {
			barre b = new barre();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	
