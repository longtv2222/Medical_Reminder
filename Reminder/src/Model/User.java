package Model;
import java.util.*;

/*
 * Potentially change hashMap of string array list to hashmap of 
 * medicine array list, need to implement equal and hashcode inside medicine.
 */
public class User {
	// Name of User
	private String userName;
	private int id;

	// A hash map of a string which represents the name of the medicine and an
	// arrayList of Alarm
	private HashMap<String, ArrayList<Alarm>> medTime; // Each medicine has a list of alarm.

	public User(String userName,int id) {
		this.setUserName(userName);
		medTime = new HashMap<String, ArrayList<Alarm>>();
		this.id = id;
	}
	
	public HashMap<String, ArrayList<Alarm>> getMedTime() {
		return this.medTime;
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

	public void showAllMedicine() {
		for (Map.Entry<String, ArrayList<Alarm>> entry : medTime.entrySet()) {
			System.out.println(entry.getKey());
		}
	}

	public void printAllAlarm(String name) {
		if (!medTime.containsKey(name))
			System.out.println("The key does not exist");
		else {
			int count = 1;
			for (Alarm iterate : medTime.get(name)) {
				System.out.println("Alarm " + count);
				System.out.println("Hour: " + iterate.getHour() + " Minute: " + iterate.getMinute());
				System.out.println();
				count++;
			}
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
					for (Alarm iterator : entry.getValue()) {
						iterator.notification();
						if (iterator.isRing() == true) {
							System.out.println(iterator.getHour() + " :" + iterator.getMinute() + " It is time to drink "
									+ entry.getKey());
							iterator.setRing(false); // After the alarm goes off, set the variable ring to false.
						}
					}
				}
			}
		}, 0, 60000);
	}
	
	

}
