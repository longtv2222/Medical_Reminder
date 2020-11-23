package Model;

import java.time.LocalDateTime;

public class Alarm {
	private int hour;
	private int minute;
	private double val;
	private Ringtone ringtone = new Ringtone();
	private boolean status;
	private int id;

	private String unit;
	private String alarmName;

	public Alarm(int id, int hour, int minute, double val, String unit, String alarmName) {
		this.setId(id);
		this.hour = hour;
		this.minute = minute;
		this.val = val;
		this.unit = unit;
		this.alarmName = alarmName;
		this.status = true;
	}

	public Alarm(int hour, int minute, double val, String unit, String alarmName) {
		this.hour = hour;
		this.minute = minute;
		this.val = val;
		this.unit = unit;
		this.alarmName = alarmName;
		this.status = true;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUnit() {
		return this.unit;
	}

	public double getVal() {
		return this.val;
	}

	public String getAlarmName() {
		return this.alarmName;
	}

	public void setAlarm(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public boolean notification(String medName) {
		LocalDateTime now = LocalDateTime.now();
		int curr_hour = now.getHour();
		int curr_minute = now.getMinute();
		if (hour == curr_hour && minute == curr_minute) {
			ringtone.play();
			return true;
		}
		return false;
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
