package controller;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import Model.Alarm;
import Model.DBManager;
import Model.User;
import View.GUI;

public class Controller {
	private GUI gui;
	private DBManager db;
	private ArrayList<User> user_list;

	public static int id = 0;

	public Controller(DBManager db, ArrayList<User> user_list) {
		this.db = db;
		this.user_list = user_list;
		this.createGUI();
	}

	public void createGUI() {
		this.gui = new GUI(this);

		if (user_list.size() == 0) {
			// Ask for user name
		} else {
			gui.createGUI();
			gui.createLeftPanel(user_list.get(id).getUserName());
			gui.createCenterPanel();
		}
	}

	public ConcurrentHashMap<String, ArrayList<Alarm>> getAlarmList(int id) {
		return user_list.get(id).getMedTime();
	}
}
