package controller;

import java.util.ArrayList;
import java.util.Map;
import Model.Alarm;
import Model.Model_Controller;
import View.GUI;
import View.ModButton;
import View.UtilityWindow;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
		button4.setOnMouseClicked(e -> this.removeAlarm());
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
				String alarmTimeName = alarm.getHour() + ":" + alarm.getMinute() + ": " + alarm.getAlarmName();
				String alarmNote = "You need to take " + alarm.getVal() + " " + alarm.getUnit() + " of "
						+ entry.getKey();

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

	public ComboBox<Integer> hour_combo_box() {
		ArrayList<Integer> hourBox = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			hourBox.add(i);
		}
		ComboBox<Integer> combo_box_hour = new ComboBox<Integer>(FXCollections.observableArrayList(hourBox));
		return combo_box_hour;
	}

	public ComboBox<Integer> minute_combo_box() {
		ArrayList<Integer> minuteBox = new ArrayList<Integer>();
		for (int i = 0; i < 60; i++) {
			minuteBox.add(i);
		}
		ComboBox<Integer> combo_box_minute = new ComboBox<Integer>(FXCollections.observableArrayList(minuteBox));
		return combo_box_minute;
	}

	// Add alarm button with existing med name
	public void addAlarm() {
		Button cancel = this.roundedButton("Cancel", 300, 60);
		Button confirm = this.roundedButton("Confirm", 300, 60);

		TextField alarmField = new TextField();
		alarmField.setPrefWidth(150);
		TextField amountField = new TextField();
		amountField.setPrefWidth(60);
		TextField unitField = new TextField();
		unitField.setPrefWidth(60);

		ComboBox<String> combo_box_name = new ComboBox<String>(
				FXCollections.observableArrayList(model.getAllMedicine()));
		ComboBox<Integer> combo_box_hour = this.hour_combo_box();
		ComboBox<Integer> combo_box_minute = this.minute_combo_box();

		Stage stage = new Stage();
		this.promtAddAlarm(stage, cancel, confirm, combo_box_name, combo_box_hour, combo_box_minute, alarmField,
				amountField, unitField);

		cancel.setOnMouseClicked(e -> stage.close());

		confirm.setOnMouseClicked(e -> {
			try {
				double amount = Double.parseDouble(amountField.getText());
				String unit = unitField.getText();
				Alarm alarm = new Alarm(combo_box_hour.getValue(), combo_box_minute.getValue(), amount, unit,
						alarmField.getText());
				String medName = combo_box_name.getValue();
				model.addAlarm(medName, alarm);
				stage.close();
			} catch (NumberFormatException e1) {
				stage.close();
				alarmField.clear();
				amountField.clear();
				unitField.clear();
				stage.show();
			} catch (NullPointerException e2) {
				stage.close();
				alarmField.clear();
				amountField.clear();
				unitField.clear();
				stage.show();
			}
		});
	}

	public void removeAlarm() {
		Button buttonBack = this.roundedButton("Back", 100, 100);
		Button buttonConfirm = this.roundedButton("Confirm", 100, 100);
		MenuButton menu = new MenuButton();
		ArrayList<MenuItem> item = new ArrayList<MenuItem>();

		GridPane gp = new GridPane();
		ComboBox<String> combo_box_name = new ComboBox<String>(FXCollections.observableArrayList());

		gp.setVgap(10);
		gp.setHgap(10);

		for (String medName : model.getAllMedicine()) {
			MenuItem menuItem = new MenuItem(medName);
			item.add(menuItem);
			menuItem.setOnAction(e -> {
				this.updateComboBoxAlarmName(combo_box_name, medName);
			});
		}
		menu.getItems().addAll(item);

		Stage stage = new Stage();

		this.removeAlarmWindow(stage, menu, gp, buttonBack, buttonConfirm, combo_box_name);

		buttonBack.setOnMouseClicked(e -> stage.close());
		buttonConfirm.setOnMouseClicked(e -> {
			stage.close();
		});
	}

	// Clear combo box and update it with new value.
	public void updateComboBoxAlarmName(ComboBox<String> combo_box_name, String medName) {
		combo_box_name.getItems().clear();
		combo_box_name.getItems().addAll(model.getAlarmNameListOfMed(medName));
	}

}
