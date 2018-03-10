package notifications;

//Specific implementation of the notification interface for my RMI Gallery application.
public class ArtPiece implements Notification {

	private static final long serialVersionUID = 1L;

	// Holds the source
	private String artist;
	// Hold the title of the artwork
	private String title;
	// Holds the art - this will be where sources input the art to be pushed to
	// the sinks
	private String content;

	// Constructor that ensures every art piece has a title, content(art) and a
	// source
	public ArtPiece(String title, String content, String source) {
		this.artist = source;
		this.title = title;
		this.content = content;

	}

	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
