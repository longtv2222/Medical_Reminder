package View;

import javafx.scene.shape.Rectangle; 


import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
		alarmNotification("Wake up", "11", "36");
	}

	public static void alarmNotification(String alarmName, String hour, String minute) {
		Rectangle roundedButton = new Rectangle();
		roundedButton.setWidth(222);
		roundedButton.setHeight(100);
		roundedButton.setArcHeight(60);
		roundedButton.setArcWidth(60);
		
		Button turnOff = new Button("Turn off");
		turnOff.setPrefSize(222, 100);
		turnOff.setShape(roundedButton);
		
		Button snooze = new Button("Snooze");
		snooze.setPrefSize(222, 100);
		snooze.setShape(roundedButton);


		HBox hbox = new HBox(48);
		hbox.setPadding(new Insets(0, 57, 79, 57));
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().addAll(turnOff, snooze); // Add 2 buttons to Hbox

		Text t = new Text("11:24\n2020-11-05");
		t.setTextAlignment(TextAlignment.CENTER);

		
		BorderPane borderpane = new BorderPane();
		borderpane.setCenter(t);
		borderpane.setBottom(hbox);

		Scene add = new Scene(borderpane);

		Stage popup = new Stage();
		popup.setHeight(600);
		popup.setWidth(600);
		popup.setResizable(false);
		popup.setScene(add);
		popup.showAndWait();
	}

}
