package by.epamtr.text.dao.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import by.epamtr.text.dao.TextReaderDAO;
import by.epamtr.text.dao.exception.DAOException;

public class TextFileReader implements TextReaderDAO {
	private BufferedReader reader;

	@Override
	public String readAllText(String filePath) throws DAOException {
		File file;
		FileReader fileReader;
		String allText;
		try {
			file = new File(filePath);
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			allText = readAll(reader);
			return allText;
		} catch (FileNotFoundException e) {
			throw new DAOException("resource file is not found", e);
		} catch (IOException e) {
			throw new DAOException("input output error", e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new DAOException("Error while closing stream", e);
			}
		}
	}

	private String readAll(BufferedReader reader) throws IOException {
		StringBuilder text = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			text.append(line);
		}
		return text.toString();
	}
}
