package by.epamtr.text.client;

import by.epamtr.text.client.exception.ClientException;
import by.epamtr.text.entity.Text;

public class MainClient {
	public static void main(String[] args) {
		Client client = new Client("localhost", 4004);
		try {
			client.start();
			Text text=client.getText();
			System.out.println(text);
			client.disconnect();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
