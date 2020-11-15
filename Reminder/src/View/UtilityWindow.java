package View;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.GroupLayout.Alignment;

import Model.Alarm;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public interface UtilityWindow {

	// To add an alarm, we need existing medicine that you have

	/*
	 * Promt user to enter the following information: - Alarm name - Time:
	 * hour,minute - Occurence or not - If yes what's the tendency - Unit of
	 * medicine (Must be letter) - How much you need to drink, medicine name
	 */
	default void promtAddAlarm(Button cancel, Button back, Button next, Button confirm, ArrayList<String> arrayList) {
		Label alarmName = new Label("Alarm name:");
		Label medName = new Label("Medicine name:");
		Label hour = new Label("Hour:");
		Label minute = new Label("Minute:");
		Label amount = new Label("Amount:");
		Label unit = new Label("Unit:");

		TextField alarmField = new TextField();
		alarmField.setPrefWidth(150);
		TextField medField = new TextField();
		medField.setPrefWidth(150);

		ComboBox<String> combo_box = new ComboBox<String>(FXCollections.observableArrayList(arrayList));

		ArrayList<Integer> hourBox = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			hourBox.add(i);
		}
		
		ArrayList<Integer> minuteBox = new ArrayList<Integer>();
		for (int i = 0; i < 60; i++) {
			minuteBox.add(i);
		}
		
		ComboBox<Integer> combo_box_hour = new ComboBox<Integer>(FXCollections.observableArrayList(hourBox));
		ComboBox<Integer> combo_box_minute = new ComboBox<Integer>(FXCollections.observableArrayList(minuteBox));
		

		TextField amountField = new TextField();
		amountField.setPrefWidth(60);
		TextField unitField = new TextField();
		unitField.setPrefWidth(60);

		HBox row1 = new HBox(30);
		row1.setAlignment(Pos.CENTER);
		row1.getChildren().addAll(alarmName, alarmField, medName, combo_box);

		HBox row2 = new HBox(20);
		row2.setAlignment(Pos.CENTER);
		row2.getChildren().addAll(hour, combo_box_hour, minute, combo_box_minute, amount, amountField, unit, unitField);

		HBox row3 = new HBox();
		row3.setAlignment(Pos.CENTER);
		row3.getChildren().addAll(cancel, next);

		VBox vbox = new VBox(4);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(row1, row2, row3);

		Stage stage = new Stage();
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

}
