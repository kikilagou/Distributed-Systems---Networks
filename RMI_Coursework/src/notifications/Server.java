package notifications;

import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) {
		try {
			
			// Creates a new server using the local machines name
			JFrame server = new ServerFrame("rmi://localhost:2001/" + InetAddress.getLocalHost().getHostName());
			server.setLocationRelativeTo(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"An error occured!\nUnable to start the server.\nPlease check a RMI registry is running on the specified machine.");
			System.exit(1);
		}
	}

}
