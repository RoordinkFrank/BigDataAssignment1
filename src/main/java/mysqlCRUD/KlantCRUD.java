package mysqlCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Klant;
import model.KlantPaar;

public class KlantCRUD {
	public static List<Klant> selectKlanten(Connection conn){
		 List<Klant> klanten = new ArrayList<Klant>();
		try
	    {
	      String query = "SELECT * FROM klanten";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);	      	     
	      while (rs.next())
	      {
	        String key = rs.getString("klant_key");
	        Klant klant = new Klant (key);
	        klanten.add(klant);        
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return klanten;
	}
	
	/*Vind alle klantparen die 4 of meer dezelfde producten hebben gekocht
	 * (optioneel per aankoop, optioneel per filiaal, optioneel het aantal
	 * overeenkomstige produceten).
	*/
	public static List<KlantPaar> selectKlantparenMetZelfdeProducten(Connection conn, int aantalZelfdeProducten){
  
		String query = "SELECT DISTINCT LEAST(aankopen.klant_key, other.klant_key) as firstKlant, GREATEST(aankopen.klant_key, other.klant_key) as otherKlant, SUM(aankopen.aantal) as aantal from aankopen LEFT OUTER JOIN aankopen AS other ON aankopen.product_key = other.product_key WHERE aankopen.klant_key <> other.klant_key GROUP BY aankopen.klant_key, other.klant_key";
		
		List<KlantPaar> klantParen = new ArrayList<KlantPaar>();
		try{
		Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      while (rs.next())
	      {
	    	  KlantPaar klantPaar = new KlantPaar(new Klant(rs.getString("firstKlant")), new Klant(rs.getString("otherKlant")), rs.getInt("aantal"));
	    	  //System.out.println("klantPaar"+klantPaar);
	    	  if (klantPaar.overeenkomstigeProducten >= aantalZelfdeProducten){
	    		  klantParen.add(klantPaar);
	    	  }
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		System.out.println("selectKlantparnMetZelfdeProducten: "+klantParen.size());
		return klantParen;
	}
	
	public static void insertKlant(Connection conn, Klant klant){
		String sql = "INSERT INTO klanten (klant_key) VALUES (?)";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, klant.key);

			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new "+klant.getClass().getSimpleName()+" was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(Connection conn, String klantKey){
		try
	    {
	      String query = "delete from klanten where klant_key = ?";
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString(1, klantKey);
	      preparedStmt.execute();	      
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	}
}
