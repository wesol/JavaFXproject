package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController {

	@FXML
	private Button btn_dodpyt;

	@FXML
	private Button btn_wyjdz;

	@FXML
	private Button btn_listakurs;

	@FXML
	void przejdzBaza(MouseEvent event) throws IOException {

		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/DodPytView.fxml"));
		Scene scene = new Scene(parent);
		// scene.getStylesheets().add(getClass().getResource("").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Logowanie");
		stage.show();

	}

	@FXML
	void przejdzKursanci(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/ListaKursView.fxml"));
		Scene scene = new Scene(parent);
		// scene.getStylesheets().add(getClass().getResource("").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Logowanie");
		stage.show();

	}

	@FXML
	void wyjdz(MouseEvent event) {
		System.exit(0);
	}

}
