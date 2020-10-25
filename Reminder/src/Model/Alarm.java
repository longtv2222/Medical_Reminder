package Model;
import java.time.LocalDateTime;

public class Alarm {

	private int hour;
	private int minute;
	private boolean recursive; // If the alarm is repeated, this will be set to true

	// The variable ring is true only before notification is sent, after
	// notification is sent, it will be false.
	private boolean ring;

	public Alarm(int hour, int minute, boolean recursive) {
		this.hour = hour;
		this.minute = minute;
		this.recursive = recursive;
		this.setRing(false);
	}

	public void setAlarm(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
		recursive = true;
	}

	public void setMode(boolean mode) {
		recursive = mode;
	}

	public void notification() {
		LocalDateTime now = LocalDateTime.now();
		int curr_hour = now.getHour();
		int curr_minute = now.getMinute();

		if (hour == curr_hour && minute == curr_minute) {
			setRing(true); // Alarm goes off thus ring is true.
		}
	}

	public boolean isCheck() {
		return recursive;
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public boolean isRing() {
		return ring;
	}

	public void setRing(boolean ring) {
		this.ring = ring;
	}
}
