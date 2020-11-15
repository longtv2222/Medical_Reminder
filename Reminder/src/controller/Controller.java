package controller;

import java.util.ArrayList;
import java.util.Map;
import Model.Alarm;
import Model.Model_Controller;
import View.GUI;
import View.ModButton;
import View.UtilityWindow;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;

public class Controller implements ModButton, UtilityWindow {
	private GUI gui;
	private Model_Controller model;

	public Controller(Model_Controller model) {
		this.model = model;
	}

	public void createGUI() {
		this.gui = new GUI(this);

		if (model.get_UserList().size() == 0) {
			// Ask for user name
		} else {
			gui.createGUI();
			gui.createLeftPanel(model.getUserName());
			gui.createCenterPanel();
		}
	}

	// Set action for all buttons on left panel
	public ArrayList<Button> leftPanelButtons() {
		ArrayList<Button> buttons = new ArrayList<Button>(5);

		Button button = this.roundedButton("View alarm", 300, 60);
		button.setOnMouseClicked(e -> this.viewAlarm());
		buttons.add(button);

		Button button2 = this.roundedButton("View medicine", 300, 60);
		button2.setOnMouseClicked(e -> this.viewMedicine());
		buttons.add(button2);

		Button button3 = this.roundedButton("Add alarm", 300, 60);
		button3.setOnMouseClicked(e -> this.addAlarm());
		buttons.add(button3);

		Button button4 = this.roundedButton("Remove alarm", 300, 60);
		buttons.add(button4);

		Button button5 = this.roundedButton("Add medicine", 300, 60);
		buttons.add(button5);

		Button button6 = this.roundedButton("Remove medicine", 300, 60);
		buttons.add(button6);

		Button button7 = this.roundedButton("More", 300, 60);
		buttons.add(button7);

		return buttons;
	}

	// Enable action for toggle button
	public void toggleButtonAction(ToggleButton button, Alarm alarm) {
		if (alarm.getStatus() == true) {
			button.setId("toggle_button_clicked_action");
			button.setOnMouseClicked(e -> {
				if (button.isSelected()) {
					button.setId("toggle_button");
					alarm.setStatus(false);
				} else {
					button.setId("toggle_button_clicked_action");
					alarm.setStatus(true);
				}
			});
		} else {
			button.setId("toggle_button");
			button.setOnMouseClicked(e -> {
				if (button.isSelected()) {
					button.setId("toggle_button_clicked_action");
					alarm.setStatus(false);
				} else {
					button.setId("toggle_button");
					alarm.setStatus(true);
				}
			});
		}
	}

	// View alarm button
	public void viewAlarm() {
		ArrayList<StackPane> alarmList = new ArrayList<StackPane>();
		for (Map.Entry<String, ArrayList<Alarm>> entry : model.getMedTime().entrySet()) {
			for (Alarm alarm : entry.getValue()) {
				String alarmTimeName = alarm.getHour() + ":" + alarm.getMinute() + ": " + entry.getKey();
				String alarmNote = "You need to take " + alarm.getVal() + " " + " of " + entry.getKey();

				ToggleButton button = this.toggleButton(alarm);
				toggleButtonAction(button, alarm);
				alarmList.add(gui.alarmView(alarmTimeName, alarmNote, button));
			}
		}
		gui.getBorderPane().setCenter(gui.centerPanel(alarmList, "What to do today?"));
	}

	// View medicine button
	public void viewMedicine() {
		ArrayList<StackPane> alarmList = new ArrayList<StackPane>();
		for (String medName : model.getMedTime().keySet()) {
			alarmList.add(gui.medView(medName));
		}
		gui.getBorderPane().setCenter(gui.centerPanel(alarmList, "Your list of alarm"));
	}

	// Add alarm button with existing med name
	public void addAlarm() {
		Button cancel = this.roundedButton("Cancel", 300, 60);
		Button back = this.roundedButton("Back", 300, 60);
		Button next = this.roundedButton("Next", 300, 60);
		Button confirm = this.roundedButton("Confirm", 300, 60);

		this.promtAddAlarm(cancel, back, next, confirm, model.getAllMedicine());
	}
}
