package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Doctor appointment also
public class GUI extends Application {
	private String css = getClass().getResource("Style.css").toString();

	public static void main(String[] args) {
		launch(args);
	}

	Button oneSideRounded(String buttonName, int width, int height) {
		Button button = new Button(buttonName);
		button.setPrefHeight(height);
		button.setPrefWidth(width);
		button.setId("one_rounded_button");
		
		button.setOnMouseEntered(e->button.setId("hover_button"));
		button.setOnMouseExited(e->button.setId("one_rounded_button"));
		button.setOnMousePressed(e->button.setId("pressed_button"));
		button.setOnMouseReleased(e->button.setId("hover_button"));
		return button;
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Button a = oneSideRounded("View alarm", 200, 80);
		BorderPane borderPane = new BorderPane();
		borderPane.setId("border_pane");
		
		
		VBox vbox = new VBox(30);
		vbox.getChildren().addAll(a,oneSideRounded("View alarm", 200, 80),oneSideRounded("View alarm", 200, 80));
		vbox.setId("left_panel");
		borderPane.setLeft(vbox);

		Scene homeScene = new Scene(borderPane);
		homeScene.getStylesheets().add(css);
		
		Stage home = new Stage();
		home.setWidth(1280);
		home.setHeight(720);
		home.setScene(homeScene);
		home.show();
	}

}
