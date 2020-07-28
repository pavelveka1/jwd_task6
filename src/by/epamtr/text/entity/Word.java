package by.epamtr.text.entity;

public class Word implements PartText {
private String word;

public Word(String line) {
	this.word = line;
}

public String getWord() {
	return word;
}

public void setWord(String line) {
	this.word = line;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((word == null) ? 0 : word.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Word other = (Word) obj;
	if (word == null) {
		if (other.word != null)
			return false;
	} else if (!word.equals(other.word))
		return false;
	return true;
}

@Override
public String toString() {
	return "Word [" + word + "]";
}

}
