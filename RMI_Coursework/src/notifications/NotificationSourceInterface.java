package notifications;

import java.rmi.Remote;
import java.rmi.RemoteException;

//This is the interface that all sources inherit from. Several sinks may subscribe to any single sink.
//The concrete implementation for this is provided by NotificationSource
public interface NotificationSourceInterface extends Remote {

	//Method to register a sink to the source - sink will be able to receive notifications
	public void registerSinks(NotificationSinkInterface sink) throws RemoteException;
	
	//Method to notify every sink that is registered to a source
	public void notifySinks(Notification notification) throws RemoteException;
	
	//Method to unregister sinks from the source - the sink will no longer receive notifications from the source
	public void unregisterSink(NotificationSinkInterface sink) throws RemoteException;
	
}
