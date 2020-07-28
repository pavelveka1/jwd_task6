package by.epamtr.text.dao.impl;

import java.util.List;
import by.epamtr.text.dao.PartTextDAO;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Word;

public class WordDAOImpl implements PartTextDAO {

	@Override
	public PartText doPartText(List<PartText> listPartText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartText doPartText(String partText) {
		PartText word=new Word(partText);
		return word;
	}

}
