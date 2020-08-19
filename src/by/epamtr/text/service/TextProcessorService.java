package by.epamtr.text.service;

import by.epamtr.text.entity.Sentance;
import by.epamtr.text.entity.Text;

public interface TextProcessorService {
	
	public Text changeFirstAndLastWordInSentance(Text text);
	public String deliteMaxSubstring(Text text, char begin, char end);
	public String changeWordWithSubstring(Sentance sentance, int length, String substring);
	public String assembleTextFromParts(Text text);

}
