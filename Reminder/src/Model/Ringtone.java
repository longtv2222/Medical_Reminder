package Model;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class Ringtone {
	private File location;
	private AudioClip audio = null;

	Ringtone() {
		location = new File("Ringtone\\Default.mp3");
		if(location.exists()) {
			System.out.println("Cool");
		}else {
			System.out.println("Error");
		}
	}

	public void play() {
			Media b = new Media(location.toURI().toString());
			audio = new AudioClip(b.getSource());
			audio.setCycleCount(0);
			audio.play();
	}
	
	public void stop() {
		if(audio != null)
			audio.stop();
	}
}
