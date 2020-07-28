package by.epamtr.text.dao;

import by.epamtr.text.entity.PartText;

public interface TextParserDAO {
	PartText parse(String filePath);
}
