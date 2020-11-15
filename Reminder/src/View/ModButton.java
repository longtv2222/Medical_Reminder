package View;

import Model.Alarm;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public interface ModButton {

	public default Button roundedButton(String buttonName, int width, int height) {
		Button button = new Button(buttonName);
		button.setPrefHeight(height);
		button.setPrefWidth(width);
		button.setId("one_rounded_button");

		button.setOnMouseEntered(e -> button.setId("hover_button"));
		button.setOnMouseExited(e -> button.setId("one_rounded_button"));
		button.setOnMousePressed(e -> button.setId("pressed_button"));
		button.setOnMouseReleased(e -> button.setId("hover_button"));
		return button;
	}

	public default ToggleButton toggleButton(Alarm alarm2) {
		ToggleButton button = new ToggleButton();
		return button;
	}
}
