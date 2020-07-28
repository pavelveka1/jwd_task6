package by.epamtr.text.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.epamtr.text.dao.PartTextDAOFactory;
import by.epamtr.text.dao.TextParserDAO;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Word;

public class TextParserImpl implements TextParserDAO {
	private static final String SENTANCE_PATTERN;
	private static final String WORD_PATTERN;
	private static final String BLOCK_CODE_PATTERN;
	private static final String PARAGRAPH_PATTERN;
	private Pattern sentancePattern;
	private Pattern paragraphPattern;
	private PartTextDAOFactory factory;
	BufferedReader reader;
	private Matcher matcher;

	static {
		FileInputStream fis;
		Properties property = new Properties();

		try {
			fis = new FileInputStream("resources/regular.properties");
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SENTANCE_PATTERN = property.getProperty("SENTANCE_PATTER");
		WORD_PATTERN = property.getProperty("WORD_PATTERN ");
		BLOCK_CODE_PATTERN = property.getProperty("BLOCK_CODE_PATTERN");
		PARAGRAPH_PATTERN = property.getProperty("PARAGRAPH_PATTERN");
	}

	public TextParserImpl() {
		factory = PartTextDAOFactory.getInstance();
	}

	@Override
	public PartText parse(String filePath) {
		PartText text = null;
		List<PartText> listPartText = new ArrayList<PartText>();
		File file;
		FileReader fileReader;
		String line = "";
		try {
			file = new File(filePath);
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			while ((line = reader.readLine()) != null) {
				if (line.contains("{")) {
					listPartText.add(parseCode(line, reader));
				} else {
					listPartText.add(parseParagraph(line, reader));
				}
			}
			text = factory.getTextDAO().doPartText(listPartText);
			return text;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return text;
	}

	private PartText parseCode(String line, BufferedReader reader) throws IOException {
		StringBuilder partText = new StringBuilder();
		PartText codeBlock;
		int countOpenedHooks = numberOpenedHooks(line);
		int countClosedHooks = numberClosedHooks(line);
		partText.append(line);
		if (countOpenedHooks != countClosedHooks) {
			while ((line = reader.readLine()) != null) {
				countOpenedHooks += numberOpenedHooks(line);
				countClosedHooks += numberClosedHooks(line);
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

	private int numberOpenedHooks(String line) {
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '{') {
				count++;
			}
		}
		return count;
	}

	private int numberClosedHooks(String line) {
		int count = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '}') {
				count++;
			}
		}
		return count;
	}

	private PartText parseParagraph(String line, BufferedReader reader) throws IOException {
		PartText paragraph = null;
		StringBuilder partTextParagraph = new StringBuilder();
		partTextParagraph.append(line);
		if (paragraphPattern.matches(PARAGRAPH_PATTERN, line)) {
			paragraph = factory.getParagraphDAO().doPartText(parseSentance(line));
		} else {
			while ((line = reader.readLine()) != null) {
				partTextParagraph.append(line);
				if (paragraphPattern.matches(PARAGRAPH_PATTERN, line)) {
					paragraph = factory.getParagraphDAO().doPartText(parseSentance(partTextParagraph.toString()));
				}
			}
		}
		return paragraph;
	}

	private List<PartText> parseSentance(String paragraph) {
		List<PartText> sentances = new ArrayList<PartText>();
		matcher = sentancePattern.matcher(paragraph);
		while (matcher.find()) {
			sentances.add(makeSentance(matcher.group()));
		}
		return sentances;
	}

	private Sentance makeSentance(String sentance) {
		List<Word> words = new ArrayList<Word>();
		Pattern wordPattern = Pattern.compile(WORD_PATTERN);
		Matcher matcher = wordPattern.matcher(sentance);
		while (matcher.find()) {
			words.add(new Word(matcher.group()));
		}
		return new Sentance(words);
	}
}
