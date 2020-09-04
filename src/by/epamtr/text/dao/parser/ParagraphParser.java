package by.epamtr.text.dao.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import by.epamtr.text.dao.PartTextParserDAO;
import by.epamtr.text.dao.TextParserDAOFactory;
import by.epamtr.text.entity.Paragraph;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;

public class ParagraphParser implements PartTextParserDAO {

	private static Matcher matcher;
	private static SentanceParser sentanceParser;

	@Override
	public PartText parse(String paragraph) {
		List<Sentance> sentances = new ArrayList<Sentance>();
		sentanceParser=TextParserDAOFactory.getInstance().getSentanceParserDAO();
		matcher = SENTANCE_PATTERN.matcher(paragraph);
		while (matcher.find()) {
			sentances.add((Sentance)sentanceParser.parse(matcher.group()));
		}
		return new Paragraph(sentances);
	}

}
