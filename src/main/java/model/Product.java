package model;

public class Product {
	
	public String key;
    public String omschrijving;
    public int aantal;
    
    public Product(){
    	
    } 
    
	public Product(String key, String omschrijving, int aantal) {
		this.key = key;
		this.omschrijving = omschrijving;
		this.aantal = aantal;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (aantal != other.aantal)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (omschrijving == null) {
			if (other.omschrijving != null)
				return false;
		} else if (!omschrijving.equals(other.omschrijving))
			return false;
		return true;
	}
}
    