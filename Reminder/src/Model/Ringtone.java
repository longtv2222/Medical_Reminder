package Model;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class Ringtone {
	private File location;
	private AudioClip audio;

	Ringtone() {
		this.location = new File("Ringtone\\Default.mp3");
		Media b = new Media(location.toURI().toString());
		audio = new AudioClip(b.getSource());
	}

	public void play() {
		audio.play();
	}

	public void stop() {
		audio.stop();
	}
}
