package by.epamtr.text.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.epamtr.text.dao.PartTextDAO;
import by.epamtr.text.entity.Paragraph;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Sentance;

public class ParagraphDAOImpl implements PartTextDAO {

	@Override
	public PartText doPartText(List<PartText> listPartText) {
		List<Sentance> sentances = new ArrayList<Sentance>();
		for (PartText partText : listPartText) {
			sentances.add((Sentance) partText);
		}
		PartText paragraph = new Paragraph(sentances);
		return paragraph;
	}

	@Override
	public PartText doPartText(String partText) {
		// TODO Auto-generated method stub
		return null;
	}

}
