package mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcTools {
	
	private static ComboPooledDataSource cpds = null;
	
	public static Connection getConnectionFromPool() throws Exception {
		if(cpds==null)
			cpds = new ComboPooledDataSource("com.dgut.javaweb");
		return cpds.getConnection();
	}
	
	public static Connection getConnection() throws Exception {
		String driverClass = "com.mysql.cj.jdbc.Driver";
		String jdcbUrl = "jdbc:mysql://localhost:3306/ldusedcar?serverTimezone=Hongkong";
		String user = "root";
		String password = "8721398omg";
		Class.forName(driverClass);
		
		Connection connection = DriverManager.getConnection(jdcbUrl, user, password);
		return connection;
	}
	
	public static void releaseResource(Statement statement, Connection connection) {
		try {
			if(statement!=null)
				statement.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void releaseResource(ResultSet rs, Statement statement, Connection connection) {
		try {
			if(rs!=null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			if(statement!=null)
				statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
