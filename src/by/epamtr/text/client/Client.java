package by.epamtr.text.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import by.epamtr.text.client.exception.ClientException;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Text;
import by.epamtr.text.entity.Word;

public class Client {
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	private Text text; 
	private ObjectInputStream inputObject;
	private ObjectOutputStream outputObject;
	

	public Client() throws ClientException {
		try {
			socket = new Socket("localhost", 4004);
			 outputObject=new ObjectOutputStream(socket.getOutputStream());
			 inputObject=new ObjectInputStream(socket.getInputStream());
		} catch (IOException e1) {
			throw new ClientException("Error while trying to create Socket or getting stream", e1);
		}
	}

	public Text getText() throws ClientException {
		
		try {
			//outputObject.writeObject(new Word("defef"));
			inputObject = new ObjectInputStream(socket.getInputStream());
			return (Text)inputObject.readObject();
		} catch (IOException|ClassNotFoundException e) {
			throw new ClientException("Error while trying to cast to (Text) or sending object on server or getting stream", e);
		}
	}

	public Text changeFirstAndLastWordInSentances(Text text) {
		
		return null;
		
	}
	
	public String deliteMaxSubstring(Text text, char begin, char end) {
		return null;
		
	}
	
	public String changeWordWithSubstring(Sentance sentance, int length, String substring) {
		return substring;
		
	}
	
	public String assembleTextFromParts(Text text) {
		return null;
		
	}
	
}
