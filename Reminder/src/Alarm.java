import java.time.LocalDateTime;

public class Alarm {
	private int hour;
	private int minute;
	private boolean recursive; // If the alarm is repeated, this will be set to true
	private String alarmMsg; // Message of the alarm

	public Alarm(int hour, int minute, boolean recursive) {
		this.hour = hour;
		this.minute = minute;
		this.recursive = recursive;
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
			System.out.println("TIME TO DRINK YOUR MED");
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
}
