package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class DodPytController {

	@FXML
	private TextArea ta_pytanie;

	@FXML
	private TextArea ta_odp1;

	@FXML
	private TextArea ta_odp2;

	@FXML
	private TextArea ta_odp3;

	@FXML
	private TextArea ta_odp4;

	@FXML
	private Button btn_zatwierdz;

	@FXML
	private Button btn_wyjdz;

	@FXML
	private ToggleGroup ra_zakres;

	@FXML
	private Button btn_clear;

	@FXML
	private ToggleGroup ra_nr_odpowiedzi;

	@FXML
	private RadioButton ra_nr1;

	@FXML
	private RadioButton ra_nr2;

	@FXML
	private RadioButton ra_nr3;

	@FXML
	private RadioButton ra_nr4;

	static String pyt;
	static int poprawna;
	static String odp1;
	static String odp2;
	static String odp3;
	static String odp4;
	static String zakres;

	@FXML Button btn_exit;

	
	@FXML
	void clearAll(MouseEvent event) {
		ta_pytanie.clear();
		ta_odp1.clear();
		ta_odp2.clear();
		ta_odp3.clear();
		ta_odp4.clear();
		ra_nr_odpowiedzi.selectToggle(null);
		ra_zakres.selectToggle(null);

	}

	@FXML
	void wsteczAdminView(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/AdminView.fxml", "Panel Egzaminatora", event);

	}

	@FXML
	void zatwierdz(MouseEvent event) throws IOException {

		pyt = ta_pytanie.getText();
		odp1 = ta_odp1.getText();
		odp2 = ta_odp2.getText();
		odp3 = ta_odp3.getText();
		odp4 = ta_odp4.getText();

		if (pyt.isEmpty() || odp1.isEmpty() || odp2.isEmpty() || odp3.isEmpty() || odp4.isEmpty()
				|| ra_zakres.getSelectedToggle() == null || ra_nr_odpowiedzi.getSelectedToggle() == null) {

			Alert ostrz = new Alert(AlertType.WARNING);
			ostrz.setContentText("Nie zosta³y wype³nione wszystkie wymagane pola");
			ostrz.setHeaderText("Wype³nij wszystkie pola");
			ostrz.setTitle("Ostrze¿enie!");
			ostrz.showAndWait();
		} else {
			zakres = ((RadioButton) ra_zakres.getSelectedToggle()).getText();
			
			if (ra_nr1.isSelected())
				poprawna = 1;
			else if (ra_nr2.isSelected())
				poprawna = 2;
			else if (ra_nr3.isSelected())
				poprawna = 3;
			else
				poprawna = 4;
			
			Pomocnicze przejdz = new Pomocnicze();
			przejdz.okno("/app/View/PotwPytanieView.fxml", "Zatwierdzenie pytania");

		}

	}

}
