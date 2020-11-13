package controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Model.Alarm;
import Model.DBManager;
import Model.User;
import View.GUI;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Controller {
	private GUI gui;
	private DBManager db;
	private ArrayList<User> user_list;
	public static int id = 0;

	public Controller(DBManager db, ArrayList<User> user_list) {
		this.db = db;
		this.user_list = user_list;
		this.createGUI();
	}

	public ArrayList<Button> leftPanelButtons() {
		ArrayList<Button> buttons = new ArrayList<Button>(5);

		Button button = gui.roundedButton("View alarm", 300, 60);
		button.setOnMouseClicked(e -> this.viewAlarm());
		buttons.add(button);

		Button button2 = gui.roundedButton("View medicine", 300, 60);
		button2.setOnMouseClicked(e -> this.viewMedicine());
		buttons.add(button2);

		Button button3 = gui.roundedButton("Add alarm", 300, 60);
		buttons.add(button3);

		Button button4 = gui.roundedButton("Remove alarm", 300, 60);
		buttons.add(button4);

		Button button5 = gui.roundedButton("Add medicine", 300, 60);
		buttons.add(button5);

		Button button6 = gui.roundedButton("Remove medicine", 300, 60);
		buttons.add(button6);

		return buttons;

	}

	public void createGUI() {
		this.gui = new GUI(this);

		if (user_list.size() == 0) {
			// Ask for user name
		} else {
			gui.createGUI();
			gui.createLeftPanel(user_list.get(id).getUserName());
			gui.createCenterPanel();
		}
	}

	public void viewAlarm() {
		ArrayList<StackPane> alarmList = new ArrayList<StackPane>();
		for (Map.Entry<String, ArrayList<Alarm>> entry : user_list.get(Controller.id).getMedTime().entrySet()) {
			for (Alarm alarm : entry.getValue()) {
				String alarmTimeName = alarm.getHour() + ":" + alarm.getMinute() + ": " + entry.getKey();
				String alarmNote = "You need to take " + alarm.getVal() + " " + " of " + entry.getKey();
				alarmList.add(gui.alarmView(alarmTimeName, alarmNote, alarm));
			}
		}
		gui.getBorderPane().setCenter(gui.centerPanel(alarmList, "What to do today?"));
	}

	public void viewMedicine() {
		ArrayList<StackPane> alarmList = new ArrayList<StackPane>();
		for (String medName : user_list.get(Controller.id).getMedTime().keySet()) {
			alarmList.add(gui.medView(medName));
		}
		gui.getBorderPane().setCenter(gui.centerPanel(alarmList, "Your list of alarm"));
	}

	public void addAlarm() {   

	}

}
