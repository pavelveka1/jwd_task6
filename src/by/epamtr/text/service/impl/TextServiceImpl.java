package by.epamtr.text.service.impl;

import by.epamtr.text.dao.TextReaderDAO;
import by.epamtr.text.dao.TextReaderDAOFactory;
import by.epamtr.text.dao.exception.DAOException;
import by.epamtr.text.entity.Text;
import by.epamtr.text.service.TextService;
import by.epamtr.text.service.exception.ServiceException;
import by.epamtr.text.service.validator.Validator;

public class TextServiceImpl implements TextService {

	@Override
	public Text parseText(String filePath) throws ServiceException  {
		if(!Validator.validete(filePath)) {
			throw new ServiceException("Path to file is not valid!");
		}
		TextReaderDAOFactory readerFactory=TextReaderDAOFactory.getInstance();
		TextReaderDAO textReader=readerFactory.getTextFileReader();
		Text text;
		try {
			text = (Text)textReader.readText(filePath);
		} catch (DAOException e) {
			throw new ServiceException("Error while processing file", e);
		}
		return text;
	}

}
