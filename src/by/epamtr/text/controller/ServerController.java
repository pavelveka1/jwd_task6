package by.epamtr.text.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import by.epamtr.text.controller.exception.ServerControllerException;
import by.epamtr.text.entity.Text;
import by.epamtr.text.service.ServiceFactory;
import by.epamtr.text.service.TextService;
import by.epamtr.text.service.exception.ServiceException;

public class ServerController {
	private volatile static ServerController serverController;
	private Socket clientSocket;
	private ServerSocket serverSocket;
	private ObjectInputStream inputObject;
	private ObjectOutputStream outputObject;

	private ServerController() {

	}

	public static ServerController getInstance() {
		if (serverController == null) {
			synchronized (ServerController.class) {
				if (serverController == null) {
					serverController = new ServerController();
				}
			}
		}
		return serverController;
	}

	public void start() throws ServerControllerException {
		try {
			serverSocket = new ServerSocket(4004);
			System.out.println("Сервер запущен!");
			clientSocket = serverSocket.accept();
			outputObject = new ObjectOutputStream(clientSocket.getOutputStream());
			inputObject = new ObjectInputStream(clientSocket.getInputStream());
		//	String request = (String) inputObject.readObject();
		//	if(request.equalsIgnoreCase("Get Text")) {
				//обращаемся к сервису и получаем от него обьект, а далее передаем его слиенту
				ServiceFactory factory=ServiceFactory.getInstance();
				TextService textService=factory.getTextService();
				Text text;
				try {
					text = textService.parseText("c:\\Users\\HP\\Desktop\\textprocessor.txt");
				} catch (ServiceException e) {
					throw new ServerControllerException("Error while parse text", e);
				}
				outputObject.writeObject(text);
		//	}
		//
		} catch (IOException e) {
				throw new ServerControllerException("Error while start server", e);
			}
	}

}
