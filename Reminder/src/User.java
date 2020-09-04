import java.util.*;

/*
 * Potentially change hashMap of string array list to hashmap of 
 * medicine array list, need to implement equal and hashcode inside medicine
 */
public class User {
	// Name of User
	private String userName;

	// A hash map of a string which represents the name of the medicine and an
	// arrayList of Alarm
	private HashMap<String, ArrayList<Alarm>> medTime; // Each medicine has a list of alarm.

	public User(String userName) {
		this.setUserName(userName);
		medTime = new HashMap<String, ArrayList<Alarm>>();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * Check if the medicine is already ex
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
					}
				}
			}
		}, 0, 60000);
	}

}
