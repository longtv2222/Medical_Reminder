package View;

import Model.Alarm;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ModButton {
	private String css;
	
	public ModButton() {
		css = getClass().getResource("Style.css").toString();
	}
	
	public Button roundedButton(String buttonName, int width, int height) {
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

	public ToggleButton toggleButton(Alarm alarm2) {
		ToggleButton button = new ToggleButton();
		button.setId("toggle_button_clicked_action");
		return button;
	}
}
