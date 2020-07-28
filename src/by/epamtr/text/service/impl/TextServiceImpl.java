package by.epamtr.text.service.impl;

import by.epamtr.text.dao.TextParserDAO;
import by.epamtr.text.dao.TextParserDAOFactory;
import by.epamtr.text.entity.Text;
import by.epamtr.text.service.TextService;

public class TextServiceImpl implements TextService {

	@Override
	public Text parseText(String filePath) {
		TextParserDAOFactory parserFactory=TextParserDAOFactory.getInstance();
		TextParserDAO textParser=parserFactory.getTextParserDAO();
		Text text=(Text)textParser.parse(filePath);
		return text;
	}

}
