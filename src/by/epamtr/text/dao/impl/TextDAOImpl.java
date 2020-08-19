package by.epamtr.text.dao.impl;

import java.util.List;
import by.epamtr.text.dao.PartTextDAO;
import by.epamtr.text.entity.PartText;
import by.epamtr.text.entity.Text;

public class TextDAOImpl implements PartTextDAO {

	@Override
	public PartText doPartText(List<PartText> listPartText) {
		PartText text=new Text(listPartText);
		return text;
	}

	@Override
	public PartText doPartText(String partText) {
		// TODO Auto-generated method stub
		return null;
	}

}
