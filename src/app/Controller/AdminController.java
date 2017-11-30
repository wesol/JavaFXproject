package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tooltip;

public class AdminController {


	@FXML
	Button btn_dodpyt;

	@FXML
	Button btn_wyjdz;

	@FXML
	Button btn_listakurs;

	@FXML Tooltip tt_wyjd;

	@FXML Button btn_statKursantow;

	@FXML Button btn_statEdycji;


	@FXML
	void przejdzBaza(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/DodPytView.fxml", "Dodanie pytania", event);

	}

	@FXML
	void przejdzKursanci(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/StatKursanciView.fxml", "Statystyki Kursantów", event);

	}

	@FXML
	void przejdzEdycje(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/StatEdycjeView.fxml", "Statystyki Edycji", event);
	}
	
	@FXML
	void wyjdz(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/LogView.fxml", "Logowanie", event);
	}



}
