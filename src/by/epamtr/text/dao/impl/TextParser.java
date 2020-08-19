package by.epamtr.text.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.epamtr.text.dao.PartTextDAOFactory;
import by.epamtr.text.dao.PartTextParserDAO;
import by.epamtr.text.dao.TextParserDAOFactory;
import by.epamtr.text.dao.exception.DAOException;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Word;

public class TextParser implements PartTextParserDAO {
	private PartTextDAOFactory factory;
	private TextParserDAOFactory parserFactory;
	static BufferedReader reader;
	private Matcher matcher;
	Iterator<String> iterator;
	private static String line="";

	public TextParser() {
		factory = PartTextDAOFactory.getInstance();

	}

	@Override
	public PartText parse(String textFromFile) throws DAOException {
		PartText text = null;
		List<String> listOfString = devideTextOnString(textFromFile);
		List<PartText> listPartText = new ArrayList<PartText>();
		String line = "";

		iterator = listOfString.iterator();
		while (iterator.hasNext()) {
			line = (String) iterator.next();

			try {
				if (line.contains("{")) {
					listPartText.add(parseCode(line, iterator));
				} else {
					listPartText.add(parseParagraph(line, iterator));
					if (this.line.contains("{")) {
						listPartText.add(parseCode(this.line, iterator));
					}
				}
			} catch (IOException e) {
				throw new DAOException("Error while parsing", e);
			}
		}
		text = factory.getTextDAO().doPartText(listPartText);
		return text;
	}

	private PartText parseCode(String line, Iterator<String> iterator) throws IOException {
		StringBuilder partText = new StringBuilder();
		PartText codeBlock;
		int countOpenedHooks = countOpenedHooks(line);
		int countClosedHooks = countClosedHooks(line);
		partText.append(line);
		if (countOpenedHooks != countClosedHooks) {
			while ((iterator.hasNext())) {
				line = iterator.next();
				countOpenedHooks += countOpenedHooks(line);
				countClosedHooks += countClosedHooks(line);
				partText.append(line);
				if (countOpenedHooks == countClosedHooks) {
					codeBlock = factory.getCodeBlockDAO().doPartText(partText.toString());
					return codeBlock;
				}
			}
		}
		codeBlock = factory.getCodeBlockDAO().doPartText(partText.toString());
		return codeBlock;
	}

	private int countOpenedHooks(String line) {
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '{') {
				count++;
			}
		}
		return count;
	}

	private int countClosedHooks(String line) {
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '}') {
				count++;
			}
		}
		return count;
	}

	private PartText parseParagraph(String line, Iterator<String> iterator) throws IOException {
		PartText paragraph = null;
		StringBuilder partTextParagraph = new StringBuilder();
		partTextParagraph.append(line);
		if (PARAGRAPH_PATTERN.matcher(line).matches()) {
			paragraph = factory.getParagraphDAO().doPartText(parseSentance(line));
		} else {
			while (iterator.hasNext()) {
				line = iterator.next();
				if (line.contains("{")) {
					this.line=line;
					paragraph = factory.getParagraphDAO().doPartText(parseSentance(partTextParagraph.toString()));
					return paragraph;
				}
				partTextParagraph.append(line);
				if (PARAGRAPH_PATTERN.matcher(partTextParagraph.toString()).matches()) {
					paragraph = factory.getParagraphDAO().doPartText(parseSentance(partTextParagraph.toString()));
					return paragraph;
				}
			}
		}
		return paragraph;
	}

	private List<PartText> parseSentance(String paragraph) {
		List<PartText> sentances = new ArrayList<PartText>();
		matcher = SENTANCE_PATTERN.matcher(paragraph);
		while (matcher.find()) {
			sentances.add(makeSentance(matcher.group()));
		}
		return sentances;
	}

	private Sentance makeSentance(String sentance) {
		List<Word> words = new ArrayList<Word>();
		Matcher matcher = WORD_PATTERN.matcher(sentance);
		while (matcher.find()) {
			words.add(new Word(matcher.group()));
		}
		return new Sentance(words);
	}

	private List<String> devideTextOnString(String wholeText) {
		List<String> listOfString = Arrays.asList(wholeText.split("\n"));
		for (int i = 0; i < listOfString.size(); i++) {
			listOfString.set(i, listOfString.get(i) + "\\n");
		}
		return listOfString;
	}
}
