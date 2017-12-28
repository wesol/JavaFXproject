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

public class EndController {

	@FXML
	Label lbl_quantityOfCorrect;
	@FXML
	Label lbl_percentOfCorrect;
	
	@FXML
	private Button btn_back;

	DBConnector db;
	public void initialize() throws SQLException, IOException {
		String uzytkownik = String.valueOf(LogController.login);
		//show stats
		lbl_quantityOfCorrect.setText("" + TestController.quantityOfCorrect);
		double procent = (double) (((double) TestController.quantityOfCorrect / (double) TestController.quantityOfQuestions)
				* 100);
		lbl_percentOfCorrect.setText(procent + "%");
		
		//add result to database
		db = new DBConnector();
		Connection conn = db.Connection();
		PreparedStatement ps = conn.prepareStatement(
				"insert into statystyki(login, procent_poprawnych) values ("+"'"+uzytkownik+"'"+", "+ ((int) procent) + ");");
		ps.executeUpdate();

	}
	
	@FXML
    void back(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LogView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Logowanie");	
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
		
		}
   
}
