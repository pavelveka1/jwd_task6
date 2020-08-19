package by.epamtr.text.dao;

import by.epamtr.text.dao.reader.TextFileReader;

public class TextReaderDAOFactory {
	private static final TextReaderDAOFactory instance = new TextReaderDAOFactory();

	private final TextReaderDAO textFileReader = new TextFileReader();

	
	private TextReaderDAOFactory() {
	}

	public static TextReaderDAOFactory getInstance() {
		return instance;
	}

	public TextReaderDAO getTextFileReader() {
		return textFileReader;
	}

}
