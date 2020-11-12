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
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Doctor appointment also
public class GUI extends Application {
	private String css = getClass().getResource("Style.css").toString();

	public static void main(String[] args) {
		launch(args);
	}

	private Button oneSideRounded(String buttonName, int width, int height) {
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

	private VBox leftPanel() {
		VBox vbox = new VBox();

		vbox.setId("left_panel");
		VBox subVbox = new VBox();
		subVbox.setId("subVbox");

		subVbox.getChildren().addAll(oneSideRounded("View alarm", 300, 60), oneSideRounded("View alarm", 300, 60),
				oneSideRounded("View alarm", 300, 60), oneSideRounded("View alarm", 300, 60),
				oneSideRounded("View alarm", 300, 60), oneSideRounded("View alarm", 300, 60),
				oneSideRounded("View alarm", 300, 60), oneSideRounded("View alarm", 300, 60),
				oneSideRounded("View alarm", 300, 60));
		ScrollPane sp = new ScrollPane();
		sp.setId("scroll_bar_left_panel");
		sp.setContent(subVbox);

		Text welcome = new Text("Hi,Viet Long!\n");
		welcome.setId("welcome_text");
		vbox.getChildren().addAll(welcome, sp, subVbox);
		return vbox;
	}

	private StackPane alarmNoti() {
		StackPane sp = new StackPane();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		Text alarm = new Text("WELCOME TO THE CITY OF DANVILLE");
		Text noti = new Text("You need to take 30 mg of Pills");
		grid.add(alarm, 4, 2);
		grid.add(noti, 4, 3);
		ToggleButton button = new ToggleButton("On");
		grid.add(button, 10, 2);
		Rectangle rect = new Rectangle(350, 100);
		rect.setId("center_rectangle");
		sp.getChildren().addAll(rect, grid);
		return sp;
	}

	private VBox centerPanel() {
		VBox vbox = new VBox();
		vbox.setId("center_panel");

		VBox subvbox = new VBox(30);

		subvbox.setId("subVbox");
		subvbox.getChildren().addAll(alarmNoti(), alarmNoti(), alarmNoti(), alarmNoti(), alarmNoti(), alarmNoti());

		ScrollPane sp = new ScrollPane();
		sp.setId("scroll_bar_center_panel");
		sp.setContent(subvbox);

		Text text = new Text("What to do today?\n");
		text.setId("center_text");
		vbox.getChildren().addAll(text, subvbox, sp);

		return vbox;
	}

	private VBox rightPanel() {
		VBox vbox = new VBox();
		vbox.setId("right_panel");
		return vbox;
	}

	@Override
	public void start(Stage arg0) throws Exception {
		BorderPane borderPane = new BorderPane();
		borderPane.setId("border_pane");

		borderPane.setLeft(leftPanel());
		borderPane.setCenter(centerPanel());
		borderPane.setRight(rightPanel());

		Scene homeScene = new Scene(borderPane);
		homeScene.getStylesheets().add(css);

		Stage home = new Stage();
		home.setWidth(1280);
		home.setHeight(720);
		home.setScene(homeScene);
		home.show();
	}

}
