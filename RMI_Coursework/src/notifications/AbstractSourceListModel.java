package notifications;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;

// Provides the JList Model for the list of sources a sink is subscribed to.
public class AbstractSourceListModel extends AbstractListModel<Object> {

	private static final long serialVersionUID = 1L;

	// Variable to hold the specific sink which is being managed
	private NotificationSinkInterface sink;
	// Variable to hold the source the sink will register with
	NotificationSourceInterface source;
	// List to hold the sources the NotificationSink sink is registered with
	private List<Source> sources;

	// Constructor passing the instance of the class a sink to manage
	public AbstractSourceListModel(NotificationSinkInterface sink) {

		this.sink = sink;

		// Instantiate the ArrayList to hold the sources the sink is registered
		// to
		sources = new ArrayList<Source>();
	}

	// Method to check if the JList already contains the source
	public boolean sourcePresent(String url) {

		// Goes through each subscribed to source in the ArrayList and checks if
		// the passed URL matches one of them
		for (Source s : sources) {
			if (s.getURL().equals(url))
				return true;
		}
		return false;
	}

	// This method allows the sink to subscribe to a source - specific URL - to
	// recieve notifications from it
	public void register(String url) throws MalformedURLException, RemoteException, NotBoundException {

		// Registers the sink with the source
		source = NotificationSource.getNotificationSource(url);
		source.registerSinks(sink);

		// Adds the source to the list of subscribed to sources for the sink
		sources.add(new Source(source, url));
		fireIntervalAdded(this, sources.size() - 1, sources.size() - 1);
	}

	// Removes a specified source (given by its index in the JList) from the
	// list of subscribed to soures for the sink. The sink will no longer
	// receive notifications from this source.
	public void unregister(int index) {
		try {

			// Removes the source from the list of sources
			Source toRemove = sources.remove(index);

			// Unregisters the sink from the source so no longer receiving
			// notifications
			toRemove.getSource().unregisterSink(sink);

			fireIntervalRemoved(this, index, index);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null,
					"An error occured while you were trying to unsubscribe! Please check your connection and try again.");
		}
	}

	// Method from AbstractListModel to be implemented - returns a specific
	// source url in the list
	@Override
	public Object getElementAt(int index) {
		return sources.get(index).getURL();
	}

	// Method from AbstractListModel to be implemented - returns the size of the
	// ArrayList of sources
	@Override
	public int getSize() {
		return sources.size();
	}

}