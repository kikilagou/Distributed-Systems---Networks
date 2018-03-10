package notifications;

import javax.swing.JFrame;

public class Client {

	public static void main(String[] args) {
		try {
			
			// Creates a new sink 
			JFrame mainWindow = new ClientFrame();
			mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainWindow.setLocationRelativeTo(null);
			mainWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
