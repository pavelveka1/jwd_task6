package by.epamtr.text.dao;

import by.epamtr.text.dao.impl.ParagraphParser;
import by.epamtr.text.dao.impl.SentanceParser;
import by.epamtr.text.dao.impl.TextParser;

public class TextParserDAOFactory {
	private static final TextParserDAOFactory instance = new TextParserDAOFactory();

	private final PartTextParserDAO textParserDAO = new TextParser();
	private final PartTextParserDAO sentanceParserDAO=new SentanceParser();
	private final PartTextParserDAO paragraphParserDAO=new ParagraphParser();
	
	private TextParserDAOFactory() {
	}

	public static TextParserDAOFactory getInstance() {
		return instance;
	}

	public TextParser getTextParserDAO() {
		return (TextParser)textParserDAO;
	}
	
	public SentanceParser getSentanceParserDAO() {
		return (SentanceParser)sentanceParserDAO;
	}

	public ParagraphParser getParagraphParserDAO() {
		return (ParagraphParser)paragraphParserDAO;
	}
}
