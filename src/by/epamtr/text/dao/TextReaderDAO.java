package by.epamtr.text.dao;

import by.epamtr.text.dao.exception.DAOException;
import by.epamtr.text.entity.Text;

public interface TextReaderDAO {

	Text readText(String filePath) throws DAOException;
	
}
