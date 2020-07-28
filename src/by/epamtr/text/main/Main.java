package by.epamtr.text.main;

import by.epamtr.text.entity.Text;
import by.epamtr.text.service.ServiceFactory;
import by.epamtr.text.service.TextService;

public class Main {
	public static void main(String[] args) {
		ServiceFactory factory=ServiceFactory.getInstance();
		TextService textService=factory.getTextService();
		Text text=textService.parseText("c:\\Users\\HP\\Desktop\\textprocessor.txt");
		System.out.println(text);
	}

}
