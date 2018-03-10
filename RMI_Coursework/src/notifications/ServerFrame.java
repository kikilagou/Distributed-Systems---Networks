package notifications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// This class creates the GUI frame for the server side of the application
public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ServerFrame(String url) throws RemoteException, MalformedURLException {
		// Setting a title for the frame
		super("RMI Gallery");

		// Creates a rmi register using port 2001
		LocateRegistry.createRegistry(2001);

		// Create the notification source using the given URL
		final NotificationSource source = new NotificationSource(url);

		// Text fields to hold the title, artist and content of each piece
		// submitted
		final JTextField title = new JTextField(20);
		final JTextField artist = new JTextField(20);
		final JTextArea content = new JTextArea(20, 30);

		// Limits the content JTextArea so that all the writing is contained
		// within the specified size and no more
		content.setWrapStyleWord(true);
		content.setLineWrap(true);

		// Labels to hold information about where to input the data to create a
		// new notification
		final JLabel titleLabel = new JLabel("Title:");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		final JLabel artistLabel = new JLabel("Artist URL:");
		artistLabel.setAlignmentX(CENTER_ALIGNMENT);
		final JLabel contentLabel = new JLabel("Insert art:");
		contentLabel.setAlignmentX(CENTER_ALIGNMENT);

		content.setBorder(new JTextField().getBorder());

		// Submit button - will send a notification to all subscribed sinks when
		// clicked
		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				source.notifySinks(new ArtPiece(title.getText(), content.getText(), artist.getText()));
			}
		});

		// Main JPanel to contain all the components
		JPanel panel = new JPanel();
		// Small padding around the outside for better look to the GUI
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(box);

		panel.add(titleLabel);
		panel.add(title);
		panel.add(artistLabel);
		panel.add(artist);
		panel.add(contentLabel);
		panel.add(content);
		panel.add(submitButton);
		setContentPane(panel);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();

	}

}
