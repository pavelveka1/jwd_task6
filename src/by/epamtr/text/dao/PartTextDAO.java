package by.epamtr.text.dao;

import java.util.List;

import by.epamtr.text.entity.PartText;

public interface PartTextDAO {
    
	PartText doPartText(List<PartText> listPartText);
	PartText doPartText(String partText);
}
