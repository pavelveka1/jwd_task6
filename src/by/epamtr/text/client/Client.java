package by.epamtr.text.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import by.epamtr.text.client.exception.ClientException;
import by.epamtr.text.entity.Request;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Text;

public class Client {
	private Text text;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String ip;
	private int port;
	private static final String GET_TEXT = "Get Text";
	private static final String CHANGE_WORD_IN_SENTANCES = "change First And Last Word In Sentances";
	private static final String DELITE_MAX_SUBSTRING="delite Max Substring";
	private static final String CHANGE_WORD_WITH_SUBSTRING="change Word With Substring";
	private static final String ASSEMBLE_TEXT_FROM_PARTS="assemble Text From Parts";

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
			out.writeObject(new Request(GET_TEXT));
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
			out.writeObject(new Request(CHANGE_WORD_IN_SENTANCES));
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

	public String deliteMaxSubstring(String begin, String end) throws ClientException {
		String delitedString=null;
		try {
			out.writeObject(new Request(DELITE_MAX_SUBSTRING, begin, end));
		} catch (IOException e) {
			throw new ClientException("Error while sending object to server", e);
		}
		try {
			 delitedString= (String) in.readObject();
		} catch (ClassNotFoundException e1) {
			throw new ClientException("Error while cast object to Text", e1);
		} catch (IOException e1) {
			throw new ClientException("Error while reading object from server", e1);
		}
		return delitedString;
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
