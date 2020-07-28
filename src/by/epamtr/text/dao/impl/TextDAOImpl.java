package by.epamtr.text.dao.impl;

import java.util.ArrayList;
import java.util.List;
import by.epamtr.text.dao.PartTextDAO;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Text;
import by.epamtr.text.entity.Word;

public class TextDAOImpl implements PartTextDAO {

	@Override
	public PartText doPartText(List<PartText> listPartText) {
		List<PartText> partsOfText = new ArrayList<PartText>();
		for (PartText partText : listPartText) {
			partsOfText.add(partText);
		}
		PartText text=(PartText)new Text(partsOfText);
		return text;
	}

	@Override
	public PartText doPartText(String partText) {
		// TODO Auto-generated method stub
		return null;
	}

}
