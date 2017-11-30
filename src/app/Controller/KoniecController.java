package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KoniecController {

	@FXML
	Label lbl_il_poprawnych;
	@FXML
	Label lbl_proc_poprawnych;
	
	
	DBConnector db;
	public void initialize() throws SQLException, IOException {
		String uzytkownik = String.valueOf(LoginController.login);
		//wyœwietlenie statystyk na widoku
		lbl_il_poprawnych.setText("" + TestController.il_odp_poprawnych);
		double procent = (double) (((double) TestController.il_odp_poprawnych / (double) TestController.il_pytan)
				* 100);
		lbl_proc_poprawnych.setText(procent + "%");
		
		//dodanie wyniku do tabeli statystyki
		db = new DBConnector();
		Connection conn = db.Connection();
		PreparedStatement ps = conn.prepareStatement(
				"insert into statystyki(login, procent_poprawnych) values ("+"'"+uzytkownik+"'"+", "+ ((int) procent) + ");");
		ps.executeUpdate();

	}

}
