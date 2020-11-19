package Model;

import java.time.LocalDateTime;

public class Alarm {
	private int hour;
	private int minute;
	private double val;
	private String unit;
	private String alarmName;
	private Ringtone ringtone = new Ringtone();
	private boolean status;
	private int id;

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

	public void notification() {
		try {
			LocalDateTime now = LocalDateTime.now();
			int curr_hour = now.getHour();
			int curr_minute = now.getMinute();

			if (hour == curr_hour && minute == curr_minute) {
				ringtone.play();
				Thread.sleep(30000); // Play for 30s then stop. Need to have a way to enable user to stop the alarm
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
