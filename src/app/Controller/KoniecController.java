package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KoniecController {

	@FXML
	Label lbl_il_poprawnych;
	@FXML
	Label lbl_proc_poprawnych;
	
	@FXML
	private Button bt_powrot;

	DBConnector db;
	public void initialize() throws SQLException, IOException {
		String uzytkownik = String.valueOf(LogController.login);
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
	
	@FXML
    void buttonClickPowrot(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LogView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");	
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
		
		}
   
}
