package Model;

import java.io.File;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class Ringtone {
	private File location;
	private AudioClip audio;

	public Ringtone() {
		this.location = new File("Ringtone\\Default.mp3");
		Media b = new Media(location.toURI().toString());
		audio = new AudioClip(b.getSource());
	}

	void setRingtone(String path) {
		this.location = new File(path);
		if (location.exists()) {
			Media b = new Media(location.toURI().toString());
			audio = new AudioClip(b.getSource());
		} else {
			System.out.println("Path does not exist");
		}
	}

	public void play() {
		audio.play();
	}

	public void stop() {
		audio.stop();
	}
}
