package mysqlCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.ProductPaar;

public class ProductCRUD {
	public static List<Product> selectproducten(Connection conn) {
		List<Product> producten = new ArrayList<Product>();
		try {
			String query = "SELECT * FROM producten";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String key = rs.getString("product_key");
				String omschrijving = rs.getString("omschrijving");
				int aantal = rs.getInt("aantal");
				Product product = new Product(key, omschrijving, aantal);
				producten.add(product);
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return producten;
	}

	public static void insertProduct(Connection conn, Product product) {
		String sql = "INSERT INTO producten (product_key, omschrijving, aantal) VALUES (?, ?, ?)";

		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, product.key);
			statement.setString(2, product.omschrijving);
			statement.setInt(3, product.aantal);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new "
						+ product.getClass().getSimpleName()
						+ " was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Vind welke productparen het vaakst tegelijk gekocht worden (optioneel per
	// filiaal).
	public static List<ProductPaar> selectMostBoughtProductPairs(Connection conn) {
		String mostBoughtProductPairs = "SELECT aankopen.product_key as firstProductKey, other.product_key as otherProductKey, count(*) as hoeVaak from aankopen INNER JOIN aankopen AS other ON aankopen.datum = other.datum WHERE aankopen.product_key <> other.product_key GROUP BY aankopen.product_key, other.product_key ORDER BY hoeVaak DESC";

		List<ProductPaar> productParen = new ArrayList<ProductPaar>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(mostBoughtProductPairs);
			while (rs.next()) {
				String firstProductKey = rs.getString("firstProductKey");
				String otherProductKey = rs.getString("otherProductKey");
				int hoeVaak = rs.getInt("hoeVaak");
				ProductPaar productPaar = new ProductPaar(firstProductKey, otherProductKey, hoeVaak);
				productParen.add(productPaar);
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return productParen;

	}
}
