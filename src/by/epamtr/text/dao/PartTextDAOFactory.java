package by.epamtr.text.dao;

import by.epamtr.text.dao.impl.CodeBlockDAOImpl;
import by.epamtr.text.dao.impl.SentanceDAOImpl;
import by.epamtr.text.dao.impl.TextDAOImpl;
import by.epamtr.text.dao.impl.ParagraphDAOImpl;
import by.epamtr.text.dao.impl.WordDAOImpl;

public class PartTextDAOFactory {
	private static final PartTextDAOFactory instance = new PartTextDAOFactory();

	private final PartTextDAO sentanseDAO = new SentanceDAOImpl();
	private final PartTextDAO wordDAO = new WordDAOImpl();
	private final PartTextDAO codeBlockDAO = new CodeBlockDAOImpl();
	private final PartTextDAO paragraphDAO = new ParagraphDAOImpl();
	private final PartTextDAO textDAO = new TextDAOImpl();

	private PartTextDAOFactory() {
	}

	public PartTextDAO getTextDAO() {
		return textDAO;
	}

	public PartTextDAO getSentanceDAO() {
		return sentanseDAO;
	}

	public PartTextDAO getWordDAO() {
		return wordDAO;
	}

	public PartTextDAO getCodeBlockDAO() {
		return codeBlockDAO;
	}

	public PartTextDAO getParagraphDAO() {
		return paragraphDAO;
	}

	public static PartTextDAOFactory getInstance() {
		return instance;
	}

}
