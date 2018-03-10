package notifications;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//Class to represent the NotificationSource object
public class NotificationSource extends UnicastRemoteObject implements NotificationSourceInterface {

	private static final long serialVersionUID = 1L;

	// List to hold all the registered sinks
	private List<NotificationSinkInterface> sinks;

	// Creates a new instance of a notification source and assigns it the
	// specific URL
	public NotificationSource(String url) throws RemoteException, MalformedURLException {
		super();

		// Instantiate the ArrayList of sinks
		sinks = new ArrayList<NotificationSinkInterface>();

		// Binds the NotificationSourch to a specific URL in the registry
		Naming.rebind(url, this);

	}

	// Concrete implementation of the interface method - when a sink registers
	// to a source, it is added to a list of registered sinks so it can be
	// notified of special events
	@Override
	synchronized public void registerSinks(NotificationSinkInterface sink) {
		sinks.add(sink);
	}

	// Concrete implementation of the interface method - when a sink is
	// unregistered it is removed from the list of registered sinks
	@Override
	synchronized public void unregisterSink(NotificationSinkInterface sink) {
		sinks.remove(sink);
	}

	// Concrete implementation of the interface method - Looping through a list
	// of registered sinks and sending them notifications
	@Override
	synchronized public void notifySinks(Notification notification) {

		// Checks if the ArrayList of sinks contains sinks, if it is empty then
		// it prints an error.
		// If the sinks ArrayList is not empty:
		if (!sinks.isEmpty()) {
			for (NotificationSinkInterface ns : sinks) {
				try {

					// Loops through every sink in the List and calls the
					// method notifySink, which sends a Notification to that
					// sink.
					ns.notifySink(this, notification);
				} catch (RemoteException e) {
					// If there is a problem then the sink is removed from the
					// list
					sinks.remove(ns);
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "There appear to be no sinks!");
		}
	}

	// Returns the specific NotificationSource for a given URL
	public static NotificationSourceInterface getNotificationSource(String url)
			throws MalformedURLException, RemoteException, NotBoundException {
		return (NotificationSourceInterface) Naming.lookup(url);
	}

}
