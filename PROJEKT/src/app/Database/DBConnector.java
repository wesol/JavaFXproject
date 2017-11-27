package app.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	public Connection Connection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/projektPWN?verifyServerCertificate=false&useSSL=true", "root",
				"MarianPetarda1");
	}

}
