package model;

import java.util.Date;


public class Aankoop {
	public String key;
	public int aantal;
    public Date datum;
    public Klant klant;
    public Product product;
    public Filiaal filiaal;
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aankoop other = (Aankoop) obj;
		if (aantal != other.aantal)
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (filiaal == null) {
			if (other.filiaal != null)
				return false;
		} else if (!filiaal.equals(other.filiaal))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
