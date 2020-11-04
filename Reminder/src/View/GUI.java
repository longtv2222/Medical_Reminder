package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application {

	public static void display(String title, String message, String buttonMsg) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);

		Label label = new Label(message);
		Button button = new Button(buttonMsg);
		button.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, button);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		alarmNotification("Wake up","11","36");
	}

	public static void alarmNotification(String alarmName,String hour,String minute) {
		Button stop = new Button("Stop");
		Button snooze = new Button("Snooze");
		Label name = new Label(alarmName);

		Stage popUp = new Stage();
		popUp.initModality(Modality.APPLICATION_MODAL); // Block all input event to other window, person has to shut off
														// the alarm before doing anything else.
		popUp.setAlwaysOnTop(true);		
		popUp.setTitle("Alarm");
		popUp.setResizable(false);
		popUp.setWidth(600);
		popUp.setHeight(600);
		
		
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(stop,snooze);
		
		
		Scene scene = new Scene(vbox);
	
		
		
		popUp.setScene(scene);
		popUp.showAndWait();
	}

}
