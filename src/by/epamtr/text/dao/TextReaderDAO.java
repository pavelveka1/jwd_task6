package by.epamtr.text.dao;

import by.epamtr.text.dao.exception.DAOException;

public interface TextReaderDAO {
	
	String readAllText(String filePath) throws DAOException;

}
