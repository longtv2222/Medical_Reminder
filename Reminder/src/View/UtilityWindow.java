package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface UtilityWindow {

	// To add an alarm, we need existing medicine that you have

	/*
	 * Promt user to enter the following information: - Alarm name - Time:
	 * hour,minute - Occurence or not - If yes what's the tendency - Unit of
	 * medicine (Must be letter) - How much you need to drink, medicine name
	 */

	default void promtAddAlarm(Stage stage, Button cancel, Button confirm, ComboBox<String> combo_box,
			ComboBox<Integer> combo_box_hour, ComboBox<Integer> combo_box_minute, TextField alarmField,
			TextField amountField, TextField unitField) {

		Label alarmName = new Label("Alarm name:");
		Label medName = new Label("Medicine name:");
		Label hour = new Label("Hour:");
		Label minute = new Label("Minute:");
		Label amount = new Label("Amount:");
		Label unit = new Label("Unit:");

		HBox row1 = new HBox(30);
		row1.setAlignment(Pos.CENTER);
		row1.getChildren().addAll(alarmName, alarmField, medName, combo_box);

		HBox row2 = new HBox(20);
		row2.setAlignment(Pos.CENTER);
		row2.getChildren().addAll(hour, combo_box_hour, minute, combo_box_minute, amount, amountField, unit, unitField);

		HBox row3 = new HBox();
		row3.setAlignment(Pos.CENTER);
		row3.getChildren().addAll(cancel, confirm);

		VBox vbox = new VBox(4);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(row1, row2, row3);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();
	}

	public default void removeAlarmWindow(Stage stage, MenuButton menu, HBox hbox) {
		menu.setPrefSize(50, 50);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPrefSize(500, 500);
		hbox.getChildren().addAll(menu);
		Scene scene = new Scene(hbox);
		stage.setScene(scene);
		stage.show();

	}

}
