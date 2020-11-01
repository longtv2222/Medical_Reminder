package Model;
import java.time.LocalDateTime;

public class Alarm {

	private int hour;
	private int minute;
	private double val;
	private String unit;
	private Ringtone ringtone;


	public Alarm(int hour, int minute,double val,String unit) {
		this.hour = hour;
		this.minute = minute;
		this.val = val;
		this.unit = unit;;
		ringtone = new Ringtone();
	}

	public void setAlarm(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public void notification(String medName) {
		LocalDateTime now = LocalDateTime.now();
		int curr_hour = now.getHour();
		int curr_minute = now.getMinute();

		if (hour == curr_hour && minute == curr_minute) {
			System.out.println("It is time to drink");
			ringtone.play();
		}
	}


	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}
}
