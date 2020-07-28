package by.epamtr.text.entity;

import java.util.List;

public class Text {
	private List<PartText> textElements;

	public Text(List<PartText> textElements) {
		super();
		this.textElements = textElements;
	}

	public List<PartText> getTextElements() {
		return textElements;
	}

	public void setTextElements(List<PartText> textElements) {
		this.textElements = textElements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((textElements == null) ? 0 : textElements.hashCode());
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
		Text other = (Text) obj;
		if (textElements == null) {
			if (other.textElements != null)
				return false;
		} else if (!textElements.equals(other.textElements))
			return false;
		return true;
	}

}
