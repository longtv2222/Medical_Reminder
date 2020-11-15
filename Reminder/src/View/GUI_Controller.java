package View;

import controller.Controller;

public class GUI_Controller implements ModButton, UtilityWindow {
	private GUI gui;
	private Controller controller;

	public GUI_Controller(Controller controller) {
		gui = new GUI();
		gui.createGUI();
	}
}
