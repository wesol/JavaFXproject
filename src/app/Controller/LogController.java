package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LogController {

	@FXML
	private Button bt_logowanie;

	@FXML
	private TextField tf_login;

	@FXML
	private PasswordField pf_password;

	DBConnector db;
	public static String role;
	public static String login;

	@FXML
	void buttonLogin(MouseEvent event) throws SQLException, IOException {
		login = tf_login.getText();
		Connection conn1 = db.Connection();
		Statement stmt = conn1.createStatement();

		ResultSet rs = stmt.executeQuery(
				"SELECT rola FROM logowanie WHERE login='" + login + "' and haslo='" + pf_password.getText() + "'");

		if (rs.next()) {
			role = rs.getString("rola");
			if (role.equals("kursant")) {
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestChoiceView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Wyb�r zakresu testu");
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			} else if (role.equals("egzaminator")) {
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/AdminView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Panel egzaminatora");
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			}
		} else {
			Alert statement = new Alert(AlertType.INFORMATION);
			statement.setHeaderText("B��d logowania");
			statement.setContentText("B��dny login lub has�o.");
			statement.setTitle("Podaj poprawne dane logowania");
			statement.showAndWait();
		}

	}

	public void initialize() {
		db = new DBConnector();
	}
}
