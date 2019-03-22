package testMysqlCRUD;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.ProductPaar;
import mysqlCRUD.ProductCRUD;

import org.junit.BeforeClass;
import org.junit.Test;

import testUtil.DataBase;
import testUtil.MockData;
import util.Util;

public class TestProductCRUD {
	static MockData mockData;
	static Connection conn;
	
	
	@BeforeClass
	public static void setup(){
		mockData = new MockData();
		conn = Util.getMysqlDBConn();
		try {
			DataBase.resetDatabase(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBase.insertMockDataToDB(mockData, conn);
	}
	
	@Test
	public void testSelectMostBoughtProductPairs(){
		List<ProductPaar> productParen = ProductCRUD.selectMostBoughtProductPairs(conn);
		//in Huidige testData zijn er drie antwoorden met aantal 5
		//product0_eend, schaap_eend, eend_schaap
		//Alle drie zijn correct voor de opdracht.
		
		System.out.println(productParen);
		assertEquals(productParen.get(0).firstProductKey, "kaas");
		assertEquals(productParen.get(0).otherProductKey, "eend");
		assertEquals(productParen.get(0).aantal, 5);
	}
}
