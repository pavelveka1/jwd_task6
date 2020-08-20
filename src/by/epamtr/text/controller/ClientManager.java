package by.epamtr.text.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import by.epamtr.text.controller.exception.ServerControllerException;
import by.epamtr.text.entity.Text;
import by.epamtr.text.service.ServiceFactory;
import by.epamtr.text.service.TextProcessorService;
import by.epamtr.text.service.TextService;
import by.epamtr.text.service.exception.ServiceException;

public class ClientManager implements Runnable {
	private static final String GET_TEXT = "Get Text";
	private static final String CHANGE_WORD_IN_SENTANCES = "change First And Last Word In Sentances";

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ClientManager(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String request = null;
		try {

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			try {
				request = (String) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				doAction(request);
			} catch (ServerControllerException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket!=null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doAction(String action) throws ServerControllerException {
		Text text = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		TextService textService = factory.getTextService();
		try {
			text = textService.parseText("c:\\Users\\HP\\Desktop\\textprocessor.txt");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (action.equalsIgnoreCase(GET_TEXT)) {
			try {
				out.writeObject(text);
			} catch (IOException e) {
				throw new ServerControllerException("Error while sending object to client", e);
			}
		} else if (action.equalsIgnoreCase(CHANGE_WORD_IN_SENTANCES)) {
			TextProcessorService textProcessor=factory.getTextProcessorService();
			text=textProcessor.changeFirstAndLastWordInSentance(text);
			try {
				out.writeObject(text);
			} catch (IOException e) {
				throw new ServerControllerException("Error while sending object to client", e);
			}
		}
	}
}
