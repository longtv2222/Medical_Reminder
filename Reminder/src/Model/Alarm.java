package Model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Alarm {
	private int id;
	private double val;
	private Ringtone ringtone = new Ringtone();
	private boolean status;
	private LocalDateTime time;
	private String unit;
	private String alarmName;

	public Alarm(int id, int hour, int minute, double val, String unit, String alarmName) {
		setAlarm(hour, minute);
		this.setId(id);
		this.val = val;
		this.unit = unit;
		this.alarmName = alarmName;
		this.status = true;
	}

	public Alarm(int hour, int minute, double val, String unit, String alarmName) {
		setAlarm(hour, minute);
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

	/*
	 * Set LocalDateTime Object with today's date with specified hour and minute.
	 * Ignore units that are smaller than minutes.
	 */
	public void setAlarm(int hour, int minute) {
		time = LocalDateTime.now().with(LocalTime.of(hour, minute)).truncatedTo(ChronoUnit.MINUTES);
	}

	/*
	 * Check if the current time is equal to alarm time. If it's equal, return
	 * ringtone object. Else return null
	 */
	public Ringtone notification(String medName) {
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		if (time.isEqual(now)) {
			return ringtone;
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHour() {
		return time.getHour();
	}

	public int getMinute() {
		return time.getMinute();
	}

}
