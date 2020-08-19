package by.epamtr.text.dao.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import by.epamtr.text.dao.PartTextParserDAO;
import by.epamtr.text.dao.TextParserDAOFactory;
import by.epamtr.text.dao.TextReaderDAO;
import by.epamtr.text.dao.exception.DAOException;
import by.epamtr.text.entity.Text;

public class TextFileReader implements TextReaderDAO {
	private BufferedReader reader;
	private final int SIZE_PAGE = 10_240_000;
	private String result = "";

	@Override
	public Text readText(String filePath) throws DAOException {
		File file;
		Text text = null;
		FileReader fileReader = null;
		file = new File(filePath);
		try {
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			if (file.length() < SIZE_PAGE) {
				result = readAll();
				text = doText(result);
				return text;
			} else {
				// add code when you have time
			}
		} catch (FileNotFoundException e1) {
			throw new DAOException("File is not found", e1);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new DAOException("Error while closing stream", e);
			}
		}
		return null;
	}

	private String readAll() throws DAOException {
		StringBuilder allText = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				allText.append(line+"\n");
			}
		} catch (IOException e) {
			throw new DAOException("Error while reading file", e);
		}
		return allText.toString();
	}

	private Text doText(String receivedText) throws DAOException {
		TextParserDAOFactory factory = TextParserDAOFactory.getInstance();
		PartTextParserDAO textParserDAO = factory.getTextParserDAO();
		return (Text) textParserDAO.parse(receivedText);
	}

	private String readPage() {
		return null;

	}
}
