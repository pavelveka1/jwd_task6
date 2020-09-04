package by.epamtr.text.controller;

import by.epamtr.text.controller.exception.ServerControllerException;

public class MainServer {
	public static void main(String[] args) {
		ServerController server = new ServerController();
		try {
			server.start();
		} catch (ServerControllerException e) {
			e.printStackTrace();
		}
	}
}
