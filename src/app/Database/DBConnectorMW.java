package app.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

public class DBConnectorMW {
	private ResultSet rs;
	private static Connection con;
	private static Statement stmt;


	private static void connect() {
		try {
			// uwierzytelnianie sterownika
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projektquiz?useSSL=false", "root",
					"123MWreaktor");
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println("\n!!!B³¹d po³aczenia z baza danych:\n" + e);
		}
	}

	

	// ===========================================================================================
	// wstawianie nowego rekordu/update
	public void insert(String query) {
		connect();
		try {
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na insert:\n" + e);

		}
	}

	// ===========================================================================================
	// zapisywanie id-kow do zbioru
	public HashSet<String> zbior(String query) {
		HashSet<String> zbior_id = new HashSet<>();
		connect();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				zbior_id.add(rs.getString(1));
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d przy tworzenie zbioru id\n" + e);
		}
		return zbior_id;
	}

	public void update(Scanner rl, String changedValueName, String id, String databaseTabelName,
			String databaseColumnNameId, String databaseUpdatedColumnName) {
		while (true) {
			System.out.println("WprowadŸ " + changedValueName + ":");
			String temp = rl.nextLine();
			String temp_confirmed = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();
			System.out.println("Wprowadzono  " + changedValueName + ": '" + temp_confirmed
					+ "'.\n('Y'- Zatwierdzenie || dowolny przycisk aby poprawiæ || 'Q'- poprzedniego menu bez zapisywania zmian,dowolny przycisk aby poprawiæ.");
			String choice_wew = rl.nextLine().toUpperCase();
			if (choice_wew.equals("Y")) {
				connect();
				try {
					stmt.executeUpdate("Update " + databaseTabelName + " set " + databaseUpdatedColumnName + " = '"
							+ temp_confirmed + "' where " + databaseColumnNameId + " = " + id);
					con.close();
				} catch (SQLException e) {
					System.out.println("\n!!!B³¹d na insert:\n" + e);
				}
				System.out.println("Zaktualizowano rekord");
				break;

			} else if (choice_wew.equals("Q"))
				break;
		}
	}
}
