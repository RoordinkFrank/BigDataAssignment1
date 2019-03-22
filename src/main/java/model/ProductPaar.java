package model;

public class ProductPaar {

	public String firstProductKey;
	public String otherProductKey;
	public int aantal;
	
	public ProductPaar(){
		
	}
	
	public ProductPaar(String firstProductKey, String otherProductKey,
			int aantal) {
		super();
		this.firstProductKey = firstProductKey;
		this.otherProductKey = otherProductKey;
		this.aantal = aantal;
	}

	@Override
	public String toString() {
		return "ProductPaar [firstProductKey=" + firstProductKey
				+ ", otherProductKey=" + otherProductKey + ", aantal=" + aantal
				+ "]";
	}
}