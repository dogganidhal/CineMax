package fr.insta.cinemax.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private String url;
	private String username;
	private String password;

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
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url, this.username, this.password);
	}

}
