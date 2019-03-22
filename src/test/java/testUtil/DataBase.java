package testUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Aankoop;
import model.Filiaal;
import model.Klant;
import model.Product;
import mysqlCRUD.AankoopCRUD;
import mysqlCRUD.FiliaalCRUD;
import mysqlCRUD.KlantCRUD;
import mysqlCRUD.ProductCRUD;

public class DataBase {
	public static void insertMockDataToDB(MockData mockData, Connection conn){
		for (Filiaal filiaal : mockData.mockFilialen){
			FiliaalCRUD.insertFiliaal(conn, filiaal);
		}
		for (Klant klant : mockData.mockKlanten){
			KlantCRUD.insertKlant(conn, klant);
		}
		for (Product product : mockData.mockProducten){
			ProductCRUD.insertProduct(conn, product);
		}
		for (Aankoop aankoop : mockData.mockAankopen){
			AankoopCRUD.insertAankoop(conn, aankoop);
		}
	}

	//https://coderanch.com/t/306966/databases/Execute-sql-file-java
	public static void resetDatabase(Connection conn) throws SQLException
    {
        String s            = new String();
        StringBuffer sb = new StringBuffer();
 
        try
        {
        	System.out.println(new File(".").getAbsolutePath());
            FileReader fr = new FileReader(new File("src/test/java/testUtil/mysqlDB.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character
 
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();
 
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");

            Statement st = conn.createStatement();
 
            for(int i = 0; i<inst.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals(""))
                {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
   
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
    }
}
