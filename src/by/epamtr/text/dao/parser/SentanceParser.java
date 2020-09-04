package by.epamtr.text.dao.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import by.epamtr.text.dao.PartTextDAO;
import by.epamtr.text.dao.PartTextDAOFactory;
import by.epamtr.text.dao.PartTextParserDAO;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Word;

public class SentanceParser implements PartTextParserDAO {
	private static Matcher matcher;
	private static PartTextDAO wordDAO=PartTextDAOFactory.getInstance().getWordDAO();

	@Override
	public PartText parse(String sentance) {
		List<Word> words = new ArrayList<Word>();
		matcher = WORD_PATTERN.matcher(sentance);
		while (matcher.find()) {
			words.add((Word)wordDAO.doPartText((matcher.group())));
		}
		return new Sentance(words);
	}
}
