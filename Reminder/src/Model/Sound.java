package Model;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Sound extends Application  {
	 public static void main(String[] args) {
	        launch(args);
	    }
	    
	    public void start(Stage primaryStage) {
	    	File a = new File("Ringtone\\Default.mp3");
	    	if(a.exists()) {
		    	Media b = new Media(a.toURI().toString());
		    	MediaPlayer c = new MediaPlayer(b);
		    	c.play();
	    	} else {
	    		System.out.println("Cannot find the file you are looking for");
	    	}

	    }
}
	