package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TestChoiceController {

	@FXML
	private Button bt_logout;

	@FXML
	private CheckBox cb_bd;

	@FXML
	private CheckBox cb_git;

	@FXML
	private CheckBox cd_Python;

	@FXML
	private CheckBox cd_fe;

	@FXML
	private CheckBox cd_java;

	@FXML
	private CheckBox cd_spring;

	@FXML
	private TextField text_field1;

	static int quantinyOfQuestionsTC;

	static boolean bd;
	static boolean git;
	static boolean python;
	static boolean fe;
	static boolean java;
	static boolean spring;
	static int m;
	public DBConnector db;

	@FXML
	void ButtonTestStart(MouseEvent event) throws IOException, SQLException {
		boolean flaga = false;
		m = 0;

		if ((cb_bd.isSelected()) || (cb_git.isSelected()) || (cd_Python.isSelected()) || 
																						
				(cd_fe.isSelected()) || (cd_java.isSelected()) || (cd_spring.isSelected())) {

			try {
				quantinyOfQuestionsTC = new Integer(text_field1.getText());
															
			} catch (NumberFormatException e1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("B³¹d");
				alert.setContentText("Wprowadz dodatni¹ liczbê ca³kowit¹");
				alert.showAndWait();
				flaga = true;
			}

			if (flaga == false) {
				python = cd_Python.isSelected();
				java = cd_java.isSelected();
				bd = cb_bd.isSelected();
				git = cb_git.isSelected();
				fe = cd_fe.isSelected();
				spring = cd_spring.isSelected();

				ArrayList<String> lista_pyt = new ArrayList<String>();

				if (python == true) {
					lista_pyt.add("python");
				}
				if (java == true) {
					lista_pyt.add("java");
				}
				if (bd == true) {
					lista_pyt.add("bd");
				}
				if (git == true) {
					lista_pyt.add("git");
				}
				if (fe == true) {
					lista_pyt.add("fe");
				}
				if (spring == true) {
					lista_pyt.add("spring");
				}

				String warunek = "'";

				for (int i = 0; i < lista_pyt.size(); i++) {
					if (i != lista_pyt.size() - 1)
						warunek = warunek.concat(lista_pyt.get(i) + "', '");
					else
						warunek = warunek.concat(lista_pyt.get(i) + "'");

				}

				try {

					Connection conn = db.Connection();

					Statement stmt = conn.createStatement();
					ResultSet pomocnicza_tresc = stmt
							.executeQuery("select count(*) from Pytania where zakres in (" + warunek + ")");

					if (pomocnicza_tresc.next())
						m = pomocnicza_tresc.getInt(1);

					conn.close();

				} catch (Exception e) {
					System.out.println("b³¹d!!!!!!!!!!!! ");
					e.printStackTrace();
				}

				if (quantinyOfQuestionsTC <= m) {
					Stage stage = new Stage();
					Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.setTitle("Test");
					stage.show();
					((Node) (event.getSource())).getScene().getWindow().hide();
				} else {
					Alert statement1 = new Alert(AlertType.INFORMATION);
					statement1.setTitle("B³¹d");
					statement1.setHeaderText("Podano zbyt du¿¹ liczbê pytañ.");
					statement1.setContentText("Maksymalna liczba pytañ dla wybranego zakresu to: " + m + ".");
					statement1.showAndWait();
				}
			}

		} else {
			Alert statement = new Alert(AlertType.INFORMATION);
			statement.setTitle("B³¹d");
			statement.setHeaderText("Nie zaznaczono zakresu testu.");
			statement.setContentText("Wybierz co najmniej jeden zakres testu.");
			statement.showAndWait();
		}
	}

	@FXML
	void logout(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LogView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Logowanie");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
	}

}
