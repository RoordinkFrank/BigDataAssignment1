package testUtil;

import java.util.ArrayList;
import java.util.List;

import model.Aankoop;
import model.Filiaal;
import model.Klant;
import model.Product;

public class MockData {
	public List<Filiaal> mockFilialen = new ArrayList<Filiaal>();
	public List<Klant> mockKlanten = new ArrayList<Klant>();
	public List<Product> mockProducten = new ArrayList<Product>();
	public List<Aankoop> mockAankopen = new ArrayList<Aankoop>();
	
	public MockData(){
		mockFilialen = CreateMockData.CreateMockFilialen();
		mockKlanten = CreateMockData.CreateMockKlanten();
		mockProducten = CreateMockData.CreateMockProducts();
		mockAankopen = CreateMockData.CreateMockAankopen(mockKlanten, mockFilialen, mockProducten);
	}
}
