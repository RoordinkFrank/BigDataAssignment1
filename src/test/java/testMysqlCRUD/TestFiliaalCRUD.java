package testMysqlCRUD;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.KlantPaar;
import mysqlCRUD.FiliaalCRUD;
import mysqlCRUD.KlantCRUD;

import org.junit.BeforeClass;
import org.junit.Test;

import testUtil.DataBase;
import testUtil.MockData;
import util.Util;

public class TestFiliaalCRUD {
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
	public void testFindFiliaalDistance(){
		int filiaalDistance = FiliaalCRUD.findFiliaalDistance(conn, mockData.mockKlanten.get(2), mockData.mockKlanten.get(3));		
		assertEquals(filiaalDistance, 0);
	}
	
	@Test
	public void testFindFiliaalDistance2(){
		int filiaalDistance = FiliaalCRUD.findFiliaalDistance(conn, mockData.mockKlanten.get(3), mockData.mockKlanten.get(5));		
		assertEquals(filiaalDistance, 1);
	}
}
