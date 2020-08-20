package by.epamtr.text.client;

import by.epamtr.text.client.exception.ClientException;

public class MainClient {
	public static void main(String[] args) {
		Client client = new Client("localhost", 4004);
		try {
			client.start();
			System.out.println(client.getText());
			System.out.println(client.changeFirstAndLastWordInSentances());
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
