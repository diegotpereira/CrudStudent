package br.com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DB {

	public Connection getDBConnection() throws ClassNotFoundException, SQLException {
		// 1. Load Driver
		Class.forName("org.postgresql.Driver");

		// 2. Connection
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sample", "postgres", "1234");

		return conn;
	}
}
