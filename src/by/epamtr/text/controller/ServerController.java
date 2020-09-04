package by.epamtr.text.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import by.epamtr.text.controller.exception.ServerControllerException;

public class ServerController {
	private ServerSocket server;

	public void start() throws ServerControllerException {
		try {
			server = new ServerSocket(4004);

			while (true) {
				System.out.println("Ожидаем подключения...");
				Socket socket = server.accept();
			
				Thread newClient = new Thread(new ClientManager(socket));
				newClient.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
	
	public void stop() throws ServerControllerException {
		try {
			server.close();
		} catch (IOException e) {
			throw new ServerControllerException("Error while closing server", e);
		}
	}
}
