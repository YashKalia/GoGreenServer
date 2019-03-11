package application;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
public void start(Stage primaryStage) {
    File song = new File("C:\\Users\\PRADHYUMNAA.G\\Downloads\\SubWoofer.mp3");
    MusicPlayer player = new MusicPlayer(song.toURI().toString());
    player.mediaplayer.play();
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/application/MainFXML.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) { 
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
