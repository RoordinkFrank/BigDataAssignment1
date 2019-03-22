package testUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Aankoop;
import model.Filiaal;
import model.Klant;
import model.Product;

public class CreateMockData {

	public static Product CreateMockProduct() {
		Product product = new Product();
		product.key = "product0";
		product.aantal = 1;
		product.omschrijving = "dit is een testproduct";
		return product;
	}

	public static List<Product> CreateMockProducts() {
		List<Product> products = new ArrayList<Product>();

		Product product = new Product();
		product.key = "eend";
		product.aantal = 100;
		product.omschrijving = "zeer eendig";
		products.add(product);

		product = new Product();
		product.key = "schaap";
		product.aantal = 10;
		product.omschrijving = "zeer schapig";
		products.add(product);

		product = new Product();
		product.key = "kaas";
		product.aantal = 10;
		product.omschrijving = "zeer kazig";
		products.add(product);

		int productCount = 0;
		for (int i = 0; i < 10; i++) {
			product = new Product();
			product.key = "product" + productCount;
			product.aantal = 10;
			product.omschrijving = "dit is een mockproduct";
			products.add(product);
			productCount++;
		}

		return products;
	}

	public static Klant CreateMockKlant() {
		Klant klant = new Klant();
		klant.key = "Gijs";
		return klant;
	}

	public static List<Klant> CreateMockKlanten() {
		List<Klant> klanten = new ArrayList<Klant>();

		Klant klant = new Klant();
		klant.key = "Gijs";
		klanten.add(klant);

		klant = new Klant();
		klant.key = "Mark";
		klanten.add(klant);

		klant = new Klant();
		klant.key = "Kees";
		klanten.add(klant);

		klant = new Klant();
		klant.key = "Ineke";
		klanten.add(klant);

		klant = new Klant();
		klant.key = "Daan";
		klanten.add(klant);

		klant = new Klant();
		klant.key = "Banaan";
		klanten.add(klant);

		klant = new Klant();
		klant.key = "Barbaar";
		klanten.add(klant);
		return klanten;
	}

	public static Filiaal CreateMockFiliaal() {
		Filiaal filiaal = new Filiaal();
		filiaal.key = "Maarssen";
		;
		return filiaal;
	}

	public static List<Filiaal> CreateMockFilialen() {
		List<Filiaal> filialen = new ArrayList<Filiaal>();

		Filiaal filiaal = new Filiaal();
		filiaal.key = "Maarssen";
		filialen.add(filiaal);

		filiaal = new Filiaal();
		filiaal.key = "Utrecht";
		filialen.add(filiaal);

		filiaal = new Filiaal();
		filiaal.key = "Amsterdam";
		filialen.add(filiaal);

		filiaal = new Filiaal();
		filiaal.key = "Groningen";
		filialen.add(filiaal);
		return filialen;
	}

	public static Aankoop CreateMockAankoop(Filiaal mockFiliaal,
			Klant mockKlant, Product mockProduct) {
		Aankoop aankoop = new Aankoop();
		aankoop.key = "nummer 1";
		aankoop.aantal = 2;
		aankoop.datum = new Date();
		aankoop.filiaal = mockFiliaal;
		aankoop.klant = mockKlant;
		aankoop.product = mockProduct;
		return aankoop;
	}

	public static List<Aankoop> CreateMockAankopen(List<Klant> mockKlanten,
			List<Filiaal> mockFilialen, List<Product> mockProducten) {
		List<Aankoop> aankopen = new ArrayList<Aankoop>();
		int aankoopNummer = 1;
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

		
		List<Date> data = new ArrayList<Date>();
		try {
			data.add(dateformat.parse("02-04-2013 11:35:42"));
			data.add(dateformat.parse("02-04-2014 11:35:43"));
			data.add(dateformat.parse("02-04-2015 11:35:44"));
			data.add(dateformat.parse("02-04-2016 11:35:45"));
			data.add(dateformat.parse("02-04-2017 11:35:46"));
			data.add(dateformat.parse("02-04-2018 11:35:47"));
			data.add(dateformat.parse("02-04-2018 10:35:47"));
			data.add(dateformat.parse("02-04-2018 19:35:47"));
			data.add(dateformat.parse("02-04-2018 6:35:47"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// dezelfde productenparen
		for (int i = 0; i < 4; i++) {
			Date date = data.get(i);
			for (int j = 0; j < 4; j++) {
				Aankoop aankoop = new Aankoop();
				aankoop.key = "nummer" + aankoopNummer;
				aankoop.aantal = 1;
				aankoop.datum = date;	
				aankoop.klant = mockKlanten.get(i);
				aankoop.product = mockProducten.get(j);
				aankoop.filiaal = mockFilialen.get(0);
				aankoopNummer++;
				aankopen.add(aankoop);
			}
		}
		
		//Kees koopt 2 eenden, er was al een gekocht
		Aankoop aankoop = new Aankoop();
		aankoop.key = "nummer" + aankoopNummer;
		aankoop.aantal = 1;
		aankoop.datum = data.get(2); //zelfde als klant
		aankoop.klant = mockKlanten.get(2);
		aankoop.product = mockProducten.get(0);
		aankoop.filiaal = mockFilialen.get(1);
		aankoopNummer++;
		aankopen.add(aankoop);

		// 4 dezelfde producten paar
		aankoop = new Aankoop();
		aankoop.key = "nummer" + aankoopNummer;
		aankoop.aantal = 4;
		aankoop.datum = data.get(5);
		aankoop.klant = mockKlanten.get(5);
		aankoop.product = mockProducten.get(6);
		aankoop.filiaal = mockFilialen.get(1);
		aankoopNummer++;
		aankopen.add(aankoop);

		// nog een keer om te testen of aankopen met zelfde klant en product
		// eerst worden samengevoegt.
		aankoop = new Aankoop();
		aankoop.key = "nummer" + aankoopNummer;
		aankoop.aantal = 4;
		aankoop.datum = data.get(6);
		aankoop.klant = mockKlanten.get(5);
		aankoop.product = mockProducten.get(6);
		aankoop.filiaal = mockFilialen.get(1);
		aankoopNummer++;
		aankopen.add(aankoop);

		aankoop = new Aankoop();
		aankoop.key = "nummer" + aankoopNummer;
		aankoop.aantal = 4;
		aankoop.datum = data.get(7);
		aankoop.klant = mockKlanten.get(6);
		aankoop.product = mockProducten.get(6);
		aankoop.filiaal = mockFilialen.get(2);
		aankoopNummer++;
		aankopen.add(aankoop);

		// niet dezelfde producten
		aankoop = new Aankoop();
		aankoop.key = "nummer" + aankoopNummer;
		aankoop.aantal = 1;
		aankoop.datum = data.get(8);
		aankoop.klant = mockKlanten.get(4);
		aankoop.product = mockProducten.get(3);
		aankoop.filiaal = mockFilialen.get(2);
		aankoopNummer++;
		aankopen.add(aankoop);
		
		//extra filiaal
		aankoop = new Aankoop();
		aankoop.key = "nummer" + aankoopNummer;
		aankoop.aantal = 1;
		aankoop.datum = data.get(8);
		aankoop.klant = mockKlanten.get(0);
		aankoop.product = mockProducten.get(6);
		aankoop.filiaal = mockFilialen.get(3);
		aankoopNummer++;
		aankopen.add(aankoop);
		return aankopen;
	}
}
