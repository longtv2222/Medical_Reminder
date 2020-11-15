package View;

import javafx.stage.Modality;
import javafx.stage.Stage;

public interface UtilityWindow {

	// To add an alarm, we need existing medicine that you have

	/*
	 * Promt user to enter the following information: - Alarm name - Time:
	 * hour,minute - Occurence or not - If yes what's the tendency - Unit of
	 * medicine (Must be letter) - How much you need to drink
	 */
	default void promtAddAlarm() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
	}

}
