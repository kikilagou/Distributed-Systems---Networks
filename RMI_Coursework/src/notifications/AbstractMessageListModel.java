package notifications;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

// This class provides a JList Model for the list of notifications that a sink receives - displayed in a JList
public class AbstractMessageListModel extends AbstractListModel<Object> {

	private static final long serialVersionUID = 1L;

	private List<ArtPiece> artPieces;

	public AbstractMessageListModel() {
		
		// Instatiate the ArrayList that will hold the art pieces
		artPieces = new ArrayList<ArtPiece>();
	}

	// Method that adds new Notifications to the ArrayList of Notifications to be displayed 
	public void newNotification(NotificationSourceInterface source, Notification notification) {
		
			// Add the artwork to the list
			artPieces.add((ArtPiece) notification);
			fireIntervalAdded(this, 0, 0);
		
	}

	@Override
	public Object getElementAt(int index) {
		// Return the elements in reverse order so that the newest notification
		// appears at the top
		ArtPiece art = artPieces.get(getSize() - index - 1);
		return String.format("<html><h2>%s</h1><p>%s</p><p><em>%s</em></p></html>", art.getTitle(),
				art.getContent(), art.getArtist());
	}

	// Method inherited from AbstractListModel - returns the size of the ArrayList of Notification objects
	@Override
	public int getSize() {
		return artPieces.size();
	}

}
