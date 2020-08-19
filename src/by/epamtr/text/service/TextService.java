package by.epamtr.text.service;

import by.epamtr.text.entity.Text;
import by.epamtr.text.service.exception.ServiceException;

public interface TextService {
	Text parseText(String filePath) throws ServiceException;

}
