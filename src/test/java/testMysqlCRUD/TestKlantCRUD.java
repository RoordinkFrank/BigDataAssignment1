package testMysqlCRUD;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.KlantPaar;
import mysqlCRUD.KlantCRUD;

import org.junit.BeforeClass;
import org.junit.Test;

import testUtil.DataBase;
import testUtil.MockData;
import util.Util;

public class TestKlantCRUD {
	
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
	public void testSelectKlantparenMetZelfdeProducten(){
		List<KlantPaar> klantParen = KlantCRUD.selectKlantparenMetZelfdeProducten(conn, 1);
		assertEquals(klantParen.size(), 15);
	}
	
	@Test
	public void testSelectKlantparenMetVierZelfdeProducten(){
		List<KlantPaar> klantParen = KlantCRUD.selectKlantparenMetZelfdeProducten(conn, 4);
		assertEquals(klantParen.size(), 9);
	}
}