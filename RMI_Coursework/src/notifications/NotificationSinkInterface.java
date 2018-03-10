package notifications;

import java.rmi.Remote;
import java.rmi.RemoteException;

//This class defines an interface for the sinks - sinks can subscribe to multiple sources
public interface NotificationSinkInterface extends Remote {
	
	//Transfers notifications to sources
	public void notifySink(NotificationSourceInterface source, Notification notification) throws RemoteException;
	
}
