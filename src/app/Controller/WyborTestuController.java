package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.CheckBox;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WyborTestuController {

	@FXML
	private Button bt_wyloguj;

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

	static int l_pyt;

	static boolean bd;
	static boolean git;
	static boolean python;
	static boolean fe;
	static boolean java;
	static boolean spring;
	static int m;
	public DBConnector db;

	@FXML
	void ButtonPrzejdzDoTestu(MouseEvent event) throws IOException, SQLException {
		boolean flaga = false;
		m = 0;

		if ((cb_bd.isSelected()) || (cb_git.isSelected()) || (cd_Python.isSelected()) || // sprawdzanie czy przycisk
																							// jest w³¹czony
				(cd_fe.isSelected()) || (cd_java.isSelected()) || (cd_spring.isSelected())) {

			try {
				l_pyt = new Integer(text_field1.getText());// do zmiennej l_pyt pobieram wynik wpisany przez uzytkownika
															// do textarea
			} catch (NumberFormatException e1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("B³¹d");
				alert.setContentText("Wprowadz dodatni¹ liczbê ca³kowit¹");
				alert.showAndWait();
				flaga = true;
			}

			if (flaga == false) {
				python = cd_Python.isSelected(); // przypisanie zmiennej, czy przyscisk jest w³aczony
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
					System.out.println("b³ad!!!!!!!!!!!! ");
					e.printStackTrace();
				}

				if (l_pyt <= m) {
					Stage stage = new Stage();
					Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.setTitle("Witaj");
					stage.show();
					((Node) (event.getSource())).getScene().getWindow().hide();
				} else {
					Alert statement1 = new Alert(AlertType.INFORMATION);
					statement1.setHeaderText("B³¹d");
					statement1.setContentText("Podano zbyt du¿¹ liczbê pytañ (maks " + m + ")");
					statement1.showAndWait();
				}
			}

		} else {
			Alert statement = new Alert(AlertType.INFORMATION);
			statement.setHeaderText("B³¹d");
			statement.setContentText("Nie zaznaczono zakresu");
			statement.showAndWait();
		}
	}

	@FXML
	void buttonWyloguj(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LogView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
	}

}
