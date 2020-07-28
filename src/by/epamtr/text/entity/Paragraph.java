package by.epamtr.text.entity;

import java.util.List;

public class Paragraph implements PartText {
	private List<Sentance> sentances;

	public Paragraph(List<Sentance> sentances) {
		this.sentances = sentances;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sentances == null) ? 0 : sentances.hashCode());
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
		Paragraph other = (Paragraph) obj;
		if (sentances == null) {
			if (other.sentances != null)
				return false;
		} else if (!sentances.equals(other.sentances))
			return false;
		return true;
	}

	public List<Sentance> getSentances() {
		return sentances;
	}

	public void setSentances(List<Sentance> sentances) {
		this.sentances = sentances;
	}

	@Override
	public String toString() {
		return "Paragraph [" + getSentances() + "]";
	}

}
