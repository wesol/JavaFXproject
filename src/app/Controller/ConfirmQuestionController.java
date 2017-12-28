package app.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ConfirmQuestionController {
	public DBConnector db;

	@FXML
	private Label lbl_e1;

	@FXML
	private Label lbl_e2;

	@FXML
	private Label lbl_e3;

	@FXML
	private Label lbl_e4;

	@FXML
	private Label lbl_question;

	@FXML
	private Label lbl_answer1;

	@FXML
	private Label lbl_answer2;

	@FXML
	private Label lbl_answer3;

	@FXML
	private Label lbl_answer4;

	@FXML
	private Label lbl_domain;

	@FXML
	private Button btn_confirm;

	@FXML
	private Button btn_cancel;

	@FXML
	void anuluj(MouseEvent event) {

		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void zatwierdz(MouseEvent event) throws SQLException {
		Connection con = db.Connection();
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate("Insert into Pytania (zakres, pytanie, odp_1, odp_2, odp_3, odp_4, odp_poprawna) values ('"
				+ AddQuestionController.domain + "', '" + AddQuestionController.question + "', '" + AddQuestionController.answer1 + "', '"
				+ AddQuestionController.answer2 + "', '" + AddQuestionController.answer3 + "', '" + AddQuestionController.answer4 + "', "
				+ AddQuestionController.correct_answer_number + ")");
		con.close();
		
		Alert ostrz = new Alert(AlertType.INFORMATION);
		ostrz.setHeaderText(
				"Do bazy zosta≥o dodane pytanie odpowiadajπce mu odpowiedzi.\nMoøesz przejúc do wprowadzania kolejnego");
		ostrz.setTitle("Dodanie pytania");
		ostrz.showAndWait();

		((Node) (event.getSource())).getScene().getWindow().hide();

	}
		

	public void initialize() {
		db = new DBConnector();
		
		lbl_question.setText(AddQuestionController.question);
		lbl_answer1.setText(AddQuestionController.answer1);
		lbl_answer2.setText(AddQuestionController.answer2);
		lbl_answer3.setText(AddQuestionController.answer3);
		lbl_answer4.setText(AddQuestionController.answer4);
		lbl_domain.setText(AddQuestionController.domain);

		switch (AddQuestionController.correct_answer_number) {
		case 1:
			lbl_e1.setText("Poprawna odpowiedü");
			lbl_answer1.setUnderline(true);
			break;

		case 2:
			lbl_e2.setText("Poprawna odpowiedü");
			lbl_answer2.setUnderline(true);
			break;
		case 3:
			lbl_e3.setText("Poprawna odpowiedü");
			lbl_answer3.setUnderline(true);
			break;
		case 4:
			lbl_e4.setText("Poprawna odpowiedü");
			lbl_answer4.setUnderline(true);
			break;
		}
	}

}
