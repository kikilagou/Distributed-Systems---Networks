package notifications;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Class to represent the NotificationSink object
public final class NotificationSink extends UnicastRemoteObject implements NotificationSinkInterface {

	private static final long serialVersionUID = 1L;

	// Used to help manage the sinks' notifications in the JList of the GUI
	AbstractMessageListModel model = new AbstractMessageListModel();

	public NotificationSink(AbstractMessageListModel model) throws RemoteException {
		super();
		this.model = model;
	}

	// Concrete implementation of the interface method used to check when a
	// NotiicationSink receives a Notification and display it on the JList
	@Override
	public void notifySink(NotificationSourceInterface source, Notification notification) {
		model.newNotification(source, notification);
	}

}
