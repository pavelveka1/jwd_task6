package by.epamtr.text.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

import by.epamtr.text.dao.exception.DAOException;
import by.epamtr.text.entity.PartText;

public interface PartTextParserDAO {

	Pattern SENTANCE_PATTERN = Initializar.getSentancePattern();
	Pattern WORD_PATTERN = Initializar.getWordPattern();
	Pattern BLOCK_CODE_PATTERN = Initializar.getBlockCodePattern();
	Pattern PARAGRAPH_PATTERN = Initializar.getParagraphPattern();
	
	PartText parse(String partText) throws DAOException;
	
	class Initializar {
		private static Properties property;
		static {
			FileInputStream fis;
			property = new Properties();

			try {
				fis = new FileInputStream("resources/regular.properties");
				property.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		static Pattern getWordPattern() {
			return Pattern.compile(property.getProperty("WORD_PATTERN"));
		}

		static Pattern getSentancePattern() {
			return Pattern.compile(property.getProperty("SENTANCE_PATTERN"));
		}

		static Pattern getParagraphPattern() {
			return Pattern.compile(property.getProperty("PARAGRAPH_PATTERN"));
		}

		static Pattern getBlockCodePattern() {
			return Pattern.compile(property.getProperty("BLOCK_CODE_PATTERN"));
		}
	}
}
