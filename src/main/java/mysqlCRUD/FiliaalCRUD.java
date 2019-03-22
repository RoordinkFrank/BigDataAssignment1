package mysqlCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Filiaal;
import model.Klant;

public class FiliaalCRUD {
	public static void insertFiliaal(Connection conn, Filiaal filiaal){
		String sql = "INSERT INTO filialen(filiaal_key) VALUES (?)";
		 
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, filiaal.key);
			 
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new "+filiaal.getClass().getSimpleName()+" was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Als klant A filiaal 1 bezoekt, en klant B bezoekt filiaal 1 en 2,
	 * en klant C bezoekt filiaal 2, dan kun je stellen dat klant A en B 0 filialen
	 * van elkaar verwijderd zijn, en klant A en C 1 filiaal.
	 * Vind hoeveel filialen twee willekeurige klanten van elkaar verwijderd zijn.
	 */
	public static int findFiliaalDistance(Connection conn, Klant A, Klant B){
		//selecteer alle filiaalaankopen voor 2 klanten.
		int distance = 0;
		List<String> nextComparing = new ArrayList<String>();
		List<String> nowComparing = new ArrayList<String>();
		List<String> notKlantB = new ArrayList<String>();//Zodat hij niet naar boven in het tree zoeken gaat. Zou ook een loop veroorzaken.
		notKlantB.add(A.key);
		nowComparing.add(A.key);
		
		boolean klantFound = false;
		while(!klantFound && nowComparing.size() != 0){ //zonder size check kan het eeuwig doorgaan. Ik probeer while te ontwijken voor die reden maar ik zag geen andere oplossing.
			for (String compareKey : nowComparing){
				System.out.println("findFiliaalDistance: now comparing for "+compareKey);
				List<String> distance0KlantKeys = findDistance0Klanten(conn, compareKey);
				for (String klant_key : distance0KlantKeys){
					if (!notKlantB.contains(klant_key)){
						System.out.println("findFiliaalDistance: comparing "+klant_key+" to "+B.key);
						if (klant_key.equals(B.key)){
							klantFound = true;
							break;
						}
						else {
							notKlantB.add(klant_key);
							nextComparing.add(klant_key);
						}
					}
				}
			}
			if (!klantFound){
				distance++;
				nowComparing = nextComparing;
				nextComparing = new ArrayList<String>();
			}
		}
		return distance;
	}

	private static List<String> findDistance0Klanten(Connection conn, String klantKey) {
		ArrayList<String> klantKeys = new ArrayList<String>();
		try
	    {
		  //String findFiliaalIfDistanceIs0 = "SELECT DISTINCT aankopen.filiaal_key FROM aankopen INNER JOIN (SELECT DISTINCT filiaal_key FROM aankopen WHERE klant_key = '"+A.key+"') AS otherKlant ON aankopen.filiaal_key = otherKlant.filiaal_key WHERE klant_key = '"+B.key+"'";
		  
			
		  String findDistance0Klanten = "SELECT DISTINCT aankopen.klant_key FROM aankopen INNER JOIN (SELECT DISTINCT filiaal_key FROM aankopen WHERE klant_key = '"+klantKey+"') AS otherKlant ON aankopen.filiaal_key = otherKlant.filiaal_key";
		  
		  Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(findDistance0Klanten);	      	     
	      while (rs.next())
	      {
	        String klant_key = rs.getString("klant_key");
	        System.out.println("findDistance0Klanten: "+klant_key);
	        klantKeys.add(klant_key);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return klantKeys;
	}
	
}
