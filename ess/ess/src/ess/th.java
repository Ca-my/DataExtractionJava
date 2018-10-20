package ess;

import java.io.IOException;

public class th extends Thread {

	public void run() {
		try {
			Barre B = new Barre() ;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
