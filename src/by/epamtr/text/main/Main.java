package by.epamtr.text.main;

import by.epamtr.text.client.Client;
import by.epamtr.text.client.exception.ClientException;
import by.epamtr.text.controller.ServerController;
import by.epamtr.text.controller.exception.ServerControllerException;
import by.epamtr.text.entity.Text;

public class Main {
	public static void main(String[] args) {
		
		Thread t1 = new Thread() {

			@Override
			public void run() {
				ServerController server = ServerController.getInstance();
				try {
					server.start();
				} catch (ServerControllerException e1) {
					e1.printStackTrace();
				}
			}
		};

		
		
		Thread t2 = new Thread() {

			@Override
			public void run() {
				super.run();
				Text text = null;
				Client client1;
				try {
					client1 = new Client();
					text = client1.getText();
				} catch (ClientException e) {
					e.printStackTrace();
				}
				System.out.println(text);
			}
		};
		
		t1.start();
		t2.start();
/*
		ServiceFactory factory = ServiceFactory.getInstance();
		TextService textService = factory.getTextService();
		Text text = null;
		try {
			text = textService.parseText("c:\\Users\\HP\\Desktop\\textprocessor.txt");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(text);

		TextProcessorService textProcessor = factory.getTextProcessorService();
		textProcessor.assembleTextFromParts(text);
		*/
	}
	
}
