package fr.insta.cinemax.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private String url;
	private String username;
	private String password;

	private Connection connection = null;
	private SQLException exception = null;

	private static ConnectionManager instance;

	public static ConnectionManager getInstance() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (ConnectionManager.instance == null)
			ConnectionManager.instance = new ConnectionManager(
				"jdbc:mysql://localhost:8889/cinemax?useSSL=false",
				"root",
				"root"
			);

		return ConnectionManager.instance;

	}

	private ConnectionManager(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		try {
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (SQLException e) {
			this.exception = e;
		}
	}

	public Connection getConnection() throws SQLException {
		if (this.exception != null)
			throw this.exception;
		return this.connection;
	}

}
