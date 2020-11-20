package Model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class User implements Runnable {
	// Name of User
	private String userName;
	// id of user.
	private final int id;

	// A hash map of a string which represents the name of the medicine and an
	// arrayList of Alarm
	private ConcurrentHashMap<String, ArrayList<Alarm>> medTime; // Each medicine has a list of alarm.

	public User(String userName, int id) {
		this.setUserName(userName);
		medTime = new ConcurrentHashMap<String, ArrayList<Alarm>>();
		this.id = id;
	}

	public ConcurrentHashMap<String, ArrayList<Alarm>> getMedTime() {
		return this.medTime;
	}

	public ArrayList<String> getMedList() {
		ArrayList<String> med = new ArrayList<String>();
		for (String medName : medTime.keySet()) {
			med.add(medName);
		}
		return med;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * Check if the medicine is already exists
	 */
	public void addMedicine(String name) {
		if (medTime.containsKey(name))
			System.out.println("The key already exists");
		else
			medTime.put(name, new ArrayList<Alarm>());
	}

	public void addingAlarm(String name, Alarm alarm) {
		if (medTime.containsKey(name)) {
			medTime.get(name).add(alarm);
		} else {
			System.out.println("Could not find medicine with name " + name);
		}
	}

	/*
	 * This function check if you have an alarm at current time or not every 30
	 * seconds.
	 */
	public void recursiveCheckAlarm() {
		Timer time = new Timer();

		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				for (Map.Entry<String, ArrayList<Alarm>> entry : medTime.entrySet()) {
					for (Alarm alarm : entry.getValue()) {
						if (alarm.getStatus()) {
							alarm.notification(entry.getKey());
						}
					}
				}
			}
		}, 0, 10000);
	}

	public ArrayList<Alarm> getAlarmListOfMed(String medName) {
		if (medTime.containsKey(medName)) {
			return medTime.get(medName);
		} else
			return null;
	}

	public void stringRemoveAlarm(String medName, String alarmName) {
		int index = 0;
		for (Alarm alarm : medTime.get(medName)) {
			if (alarm.getAlarmName().equals(alarmName))
				medTime.get(medName).remove(index);
			else
				index++;
		}
	}

	public ArrayList<String> getAlarmNameListOfMed(String medName) {
		ArrayList<String> alarmName = new ArrayList<String>();
		for (Alarm alarm : getAlarmListOfMed(medName)) {
			alarmName.add(alarm.getAlarmName());
		}
		return alarmName;
	}

	@Override
	public void run() {
		this.recursiveCheckAlarm();
	}

	public int getId() {
		return id;
	}

	public void addAlarm(String medName, Alarm alarm) {
		this.getAlarmListOfMed(medName).add(alarm);
	}

	public void removeAlarm(int id) {
		for (Map.Entry<String, ArrayList<Alarm>> entry : medTime.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).getId() == id)
					entry.getValue().remove(i);
			}
		}
	}

	public void removeMedicine(String medName) {
		medTime.remove(medName);
	}
}
