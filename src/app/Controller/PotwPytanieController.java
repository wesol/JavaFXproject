package app.Controller;

import app.Database.DBConnectorMW;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class PotwPytanieController {


	@FXML
	private Label lbl_e1;

	@FXML
	private Label lbl_e2;

	@FXML
	private Label lbl_e3;

	@FXML
	private Label lbl_e4;

	@FXML
	private Label lbl_pyt;

	@FXML
	private Label lbl_odp1;

	@FXML
	private Label lbl_odp2;

	@FXML
	private Label lbl_odp3;

	@FXML
	private Label lbl_odp4;

	@FXML
	private Label lbl_zakres;

	@FXML
	private Button btn_zatwierdz;

	@FXML
	private Button btn_anuluj;

	@FXML
	void anuluj(MouseEvent event) {

		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void zatwierdz(MouseEvent event) {
		DBConnectorMW baza = new DBConnectorMW();
		baza.insert("Insert into Pytania (zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values ('"
				+ DodPytController.zakres + "', '" + DodPytController.pyt + "', '" + DodPytController.odp1 + "', '"
				+ DodPytController.odp2 + "', '" + DodPytController.odp3 + "', '" + DodPytController.odp4 + "', "
				+ DodPytController.poprawna + ")");

		Alert ostrz = new Alert(AlertType.INFORMATION);
		ostrz.setHeaderText(
				"Do bazy zosta≥o dodane pytanie odpowiadajπce mu odpowiedzi.\nMoøesz przejúc do wprowadzania kolejnego");
		ostrz.setTitle("Dodanie pytania");
		ostrz.showAndWait();

		((Node) (event.getSource())).getScene().getWindow().hide();

	}
		

	public void initialize() {
		lbl_pyt.setText(DodPytController.pyt);
		lbl_odp1.setText(DodPytController.odp1);
		lbl_odp2.setText(DodPytController.odp2);
		lbl_odp3.setText(DodPytController.odp3);
		lbl_odp4.setText(DodPytController.odp4);
		lbl_zakres.setText(DodPytController.zakres);

		switch (DodPytController.poprawna) {
		case 1:
			lbl_e1.setText("Poprawna odpowiedü");
			lbl_odp1.setUnderline(true);
			break;

		case 2:
			lbl_e2.setText("Poprawna odpowiedü");
			lbl_odp2.setUnderline(true);
			break;
		case 3:
			lbl_e3.setText("Poprawna odpowiedü");
			lbl_odp3.setUnderline(true);
			break;
		case 4:
			lbl_e4.setText("Poprawna odpowiedü");
			lbl_odp4.setUnderline(true);
			break;
		}
	}

}
