package by.epamtr.text.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import by.epamtr.text.dao.TextParserDAOFactory;
import by.epamtr.text.entity.CodeBlock;
import by.epamtr.text.entity.Paragraph;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Text;
import by.epamtr.text.entity.Word;
import by.epamtr.text.service.TextProcessorService;

public class TextProcessorServiceImpl implements TextProcessorService {
	private static final String PARAGRAPH="Paragraph";

	@Override
	public Text changeFirstAndLastWordInSentance(Text text) {

		for (PartText partText : text.getTextElements()) {
			if (partText.getClass().getSimpleName().equalsIgnoreCase(PARAGRAPH)) {
				for (PartText sentance : ((Paragraph) partText).getSentances()) {
					changeFirstAndLastWord((Sentance) sentance);
				}
			}
		}
		return text;
	}

	private void changeFirstAndLastWord(Sentance sentance) {
		List<Word> words = sentance.getWords();
		Word word = words.get(0);
		words.set(0, words.get(words.size() - 1));
		words.set(words.size() - 1, word);
	}

	@Override
	public String deliteMaxSubstring(Text text, String begin, String end) {
		int maxSubstring = 0;
		for (PartText partText : text.getTextElements()) {
			if (partText.getClass().getSimpleName().equalsIgnoreCase(PARAGRAPH)) {
				for (PartText sentance : ((Paragraph) partText).getSentances()) {
					String unitedWords = doSentanceFromWords((Sentance) sentance);
					int beginIndex = unitedWords.indexOf( begin);
					int endIndex = unitedWords.lastIndexOf(end);
					if (beginIndex < 0 || endIndex < 0) {
						continue;
					}
					if (maxSubstring < (endIndex - beginIndex)) {
						maxSubstring = endIndex - beginIndex;
					}
				}
			}
		}
		return deliteSubstring(text, maxSubstring, begin, end);
	}

	private String doSentanceFromWords(Sentance sentance) {
		List<Word> words = sentance.getWords();
		StringBuilder stringBuilder = new StringBuilder();
		for (Word word : words) {
			stringBuilder.append(word.getWord());
		}
		return stringBuilder.toString();
	}

	private String deliteSubstring(Text text, int maxSubstring, String begin, String end) {
		for (PartText partText : text.getTextElements()) {
			if (partText.getClass().getSimpleName().equalsIgnoreCase(PARAGRAPH)) {
				for (PartText sentance : ((Paragraph) partText).getSentances()) {
					String unitedWords = doSentanceFromWords((Sentance) sentance);
					int beginIndex = unitedWords.indexOf( begin);
					int endIndex = unitedWords.lastIndexOf( end);
					if (beginIndex < 0 || endIndex < 0) {
						continue;
					}
					if (maxSubstring == (endIndex - beginIndex)) {
						String substring = unitedWords.substring(beginIndex, endIndex);
						unitedWords = unitedWords.replace(substring, "");
						Sentance newSentance = (Sentance) TextParserDAOFactory.getInstance().getSentanceParserDAO()
								.parse(unitedWords);
						((Sentance) sentance).setWords(newSentance.getWords());
						return substring;
					} else {
						continue;
					}
				}
			}
		}
		return "";
	}

	@Override
	public String changeWordWithSubstring(Sentance sentance, int length, String substring) {
		for (Word word : sentance.getWords()) {
			if (word.getWord().length() == length) {
				word.setWord(substring);
			}
		}
		return null;
	}

	@Override
	public String assembleTextFromParts(Text text) {
		for (PartText partText : text.getTextElements()) {
			if (partText.getClass().getSimpleName() == Paragraph.class.getSimpleName()) {
				for (Sentance sentance : ((Paragraph) partText).getSentances()) {
					for (Word word : sentance.getWords()) {
						System.out.print(word.getWord() + " ");
					}
				}
			} else {
				Pattern pattern = Pattern.compile("\\n");
				List<String> codeLines = Arrays.asList(pattern.split(((CodeBlock) partText).getCode()));
				for (String codeLine : codeLines) {
					System.out.println(codeLine);
				}
			}
		}
		return null;
	}

}
