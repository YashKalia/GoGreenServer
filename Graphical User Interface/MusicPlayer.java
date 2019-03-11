package application;



import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MusicPlayer extends BorderPane {
	
	Media media;
	MediaPlayer mediaplayer;
	MediaView view;
	
	public MusicPlayer(String name) {
		media = new Media(name);
		mediaplayer = new MediaPlayer(media);
		view = new MediaView(mediaplayer);
		setCenter(view);
		
	}

}
