package by.epamtr.text.dao.impl;

import java.util.ArrayList;
import java.util.List;
import by.epamtr.text.dao.PartTextDAO;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Word;

public class SentanceDAOImpl implements PartTextDAO {

	@Override
	public PartText doPartText(List<PartText> listPartText) {
		List<Word> words = new ArrayList<Word>();
		for (PartText partText : listPartText) {
			words.add((Word)partText);
		}
		PartText sentance = new Sentance(words);
		return sentance;
	}

	@Override
	public PartText doPartText(String partText) {
		// TODO Auto-generated method stub
		return null;
	}

}
