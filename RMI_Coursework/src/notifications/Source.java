package notifications;

// Models the items in the JList of sources
public class Source {

	// Holds the source reference
	private final NotificationSourceInterface source;
	// Holds the source url
	private final String url;

	protected Source(NotificationSourceInterface source, String url) {
		this.url = url;
		this.source = source;
	}

	protected NotificationSourceInterface getSource() {
		return this.source;
	}

	protected String getURL() {
		return this.url;
	}

}