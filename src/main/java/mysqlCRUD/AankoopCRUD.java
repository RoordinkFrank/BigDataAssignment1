package mysqlCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Aankoop;
import util.Util;

public class AankoopCRUD {
	
	public static void insertAankoop(Connection conn, Aankoop aankoop){
		String sql = "INSERT INTO aankopen (aankoop_key, datum, aantal, product_key, filiaal_key, klant_key) VALUES (?, ?, ?, ?, ?, ?)";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, aankoop.key);
			statement.setString(2, Util.dateToSQLDateTime(aankoop.datum));
			statement.setLong(3, aankoop.aantal);
			statement.setString(4, aankoop.product.key);
			statement.setString(5, aankoop.filiaal.key);
			statement.setString(6, aankoop.klant.key);
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new "+aankoop.getClass().getSimpleName()+" was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(Connection conn, String aankoopKey){
		try
	    {
	      String query = "delete from aankopen where aankoop_key = ?";
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString(1, aankoopKey);
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
