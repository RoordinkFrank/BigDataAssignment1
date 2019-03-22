package model;

public class KlantPaar {
	public Klant firstKlant;
	public Klant otherKlant;
	public int overeenkomstigeProducten;
	
	public KlantPaar(){
		
	}
	
	public KlantPaar(Klant firstKlant, Klant otherKlant, int overeenkomstigeProducten){
		this.firstKlant = firstKlant;
		this.otherKlant = otherKlant;
		this.overeenkomstigeProducten = overeenkomstigeProducten;
	}

	@Override
	public String toString() {
		return "KlantPaar [firstKlant=" + firstKlant + ", otherKlant="
				+ otherKlant + ", overeenkomstigeProducten="
				+ overeenkomstigeProducten + "]";
	}
}