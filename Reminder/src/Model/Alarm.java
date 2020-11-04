package Model;

import java.time.LocalDateTime;

public class Alarm {

	private int hour;
	private int minute;
	private double val;
	private String unit;
	private Ringtone ringtone = new Ringtone();

	public Alarm(int hour, int minute, double val, String unit) {
		this.hour = hour;
		this.minute = minute;
		this.val = val;
		this.unit = unit;
	}

	public void setAlarm(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public void notification() {
		try {
			LocalDateTime now = LocalDateTime.now();
			int curr_hour = now.getHour();
			int curr_minute = now.getMinute();

			if (hour == curr_hour && minute == curr_minute) {
				ringtone.play();
				Thread.sleep(30000);  //Play for 30s then stop. Need to have a way to enable user to stop the alarm
				ringtone.stop();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}
}
