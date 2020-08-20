package by.epamtr.text.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import by.epamtr.text.client.exception.ClientException;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Text;

public class Client {
	private Text text;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String ip;
	private int port;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start() throws ClientException {
		try {
			socket = new Socket(ip, port);

			System.out.println("Клиент: соединение установлено.");

			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			throw new ClientException("Error while getting stream from socket", e);
		}

	}

	public Text getText() throws ClientException {

		try {
			out.writeObject(new String("Get Text"));
		} catch (IOException e) {
			throw new ClientException("Error while sending object to server", e);
		}
		try {
			text = (Text) in.readObject();
		} catch (ClassNotFoundException e1) {
			throw new ClientException("Error while cast object to Text", e1);
		} catch (IOException e1) {
			throw new ClientException("Error while reading object from server", e1);
		}
		return text;
	}

	public Text changeFirstAndLastWordInSentances() throws ClientException {
		try {
			out.writeObject(new String("change First And Last Word In Sentances"));
		} catch (IOException e) {
			throw new ClientException("Error while sending object to server", e);
		}
		try {
			text = (Text) in.readObject();
		} catch (ClassNotFoundException e1) {
			throw new ClientException("Error while cast object to Text", e1);
		} catch (IOException e1) {
			throw new ClientException("Error while reading object from server", e1);
		}
		return text;
	}

	public String deliteMaxSubstring(char begin, char end) {
		return null;

	}

	public String changeWordWithSubstring(Sentance sentance, int length, String substring) {
		return substring;

	}

	public String assembleTextFromParts(Text text) {
		return null;

	}

	public void disconnect() throws ClientException {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				throw new ClientException("Error while trying close socket", e);
			}
		}
	}
}
