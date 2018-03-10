package notifications;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ClientFrame() throws RemoteException {
		super("RMI Gallery");

		// Manages the notifications the sink will be receiving
		AbstractMessageListModel listModel = new AbstractMessageListModel();
		// Passes the listModel to the specified sink
		NotificationSinkInterface sink = new NotificationSink(listModel);
		// Manages the list of sources the sink is subscribed to 
		AbstractSourceListModel listOfSources = new AbstractSourceListModel(sink);

		// Main panel
		JPanel panel = new JPanel();

		BoxLayout box = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(box);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel notificationsDisplay = new JPanel();
		BoxLayout boxS = new BoxLayout(notificationsDisplay, BoxLayout.Y_AXIS);
		notificationsDisplay.setLayout(boxS);
		JLabel l = new JLabel("Subscribe to:");
		notificationsDisplay.add(l);
		l.setAlignmentX(CENTER_ALIGNMENT);
		JTextField addSource = new JTextField("Insert URL subscribe to.		");
		addSource.setMaximumSize(addSource.getPreferredSize());
		addSource.setAlignmentX(CENTER_ALIGNMENT);

		panel.add(notificationsDisplay);

		JList<Object> JList = new JList<Object>(listModel);
		JScrollPane sPane = new JScrollPane(JList);
		sPane.setPreferredSize(new Dimension(500, 400));
		panel.add(sPane);

		JButton addButton = new JButton("Subscribe");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!listOfSources.sourcePresent(addSource.getText().trim()))
						listOfSources.register(addSource.getText());
					addSource.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Error occured! Check that the URL inputed is correct.");
				}
			}
		});

		final JList<Object> sourceList = new JList<>(listOfSources);
		panel.add(new JScrollPane(sourceList));

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!sourceList.isSelectionEmpty())
					listOfSources.unregister(sourceList.getSelectedIndex());
			}
		});

		notificationsDisplay.add(addSource);
		notificationsDisplay.add(Box.createRigidArea(new Dimension(0, 10)));
		notificationsDisplay.add(addButton);
		notificationsDisplay.add(Box.createRigidArea(new Dimension(0, 10)));
		notificationsDisplay.add(removeButton);

		addButton.setAlignmentX(CENTER_ALIGNMENT);
		removeButton.setAlignmentX(CENTER_ALIGNMENT);

		setContentPane(panel);
		setResizable(false);
		pack();
	}
}
