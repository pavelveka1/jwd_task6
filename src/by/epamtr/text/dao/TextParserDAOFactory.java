package by.epamtr.text.dao;

import by.epamtr.text.dao.impl.TextParserImpl;

public class TextParserDAOFactory {
	private static final TextParserDAOFactory instance = new TextParserDAOFactory();

	private final TextParserDAO textParserDAO = new TextParserImpl();
	
	private TextParserDAOFactory() {
	}

	public static TextParserDAOFactory getInstance() {
		return instance;
	}

	public TextParserDAO getTextParserDAO() {
		return textParserDAO;
	}
	
	

}
