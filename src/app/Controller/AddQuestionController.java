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

public class AddQuestionController {

	@FXML
	private TextArea ta_question;

	@FXML
	private TextArea ta_answer1;

	@FXML
	private TextArea ta_answer2;

	@FXML
	private TextArea ta_answer3;

	@FXML
	private TextArea ta_answer4;

	@FXML
	private Button btn_confirm;

	@FXML
	private ToggleGroup ra_domain;

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

	static String question;
	static int correct_answer_number;
	static String answer1;
	static String answer2;
	static String answer3;
	static String answer4;
	static String domain;

	@FXML
	Button btn_back;

	@FXML
	void clearAll(MouseEvent event) {
		ta_question.clear();
		ta_answer1.clear();
		ta_answer2.clear();
		ta_answer3.clear();
		ta_answer4.clear();
		ra_nr_odpowiedzi.selectToggle(null);
		ra_domain.selectToggle(null);

	}

	@FXML
	void backAdminView(MouseEvent event) throws IOException {
		ComponentClass przejdz = new ComponentClass();
		przejdz.okno("/app/View/AdminView.fxml", "Panel Egzaminatora", event);

	}

	@FXML
	void confirm(MouseEvent event) throws IOException {

		question = ta_question.getText();
		answer1 = ta_answer1.getText();
		answer2 = ta_answer2.getText();
		answer3 = ta_answer3.getText();
		answer4 = ta_answer4.getText();

		if (question.isEmpty() || answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty() || answer4.isEmpty()
				|| ra_domain.getSelectedToggle() == null || ra_nr_odpowiedzi.getSelectedToggle() == null) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Nie zosta³y wype³nione wszystkie wymagane pola");
			alert.setHeaderText("Wype³nij wszystkie pola");
			alert.setTitle("Ostrze¿enie!");
			alert.showAndWait();
		} else {
			domain = ((RadioButton) ra_domain.getSelectedToggle()).getText();

			if (ra_nr1.isSelected())
				correct_answer_number = 1;
			else if (ra_nr2.isSelected())
				correct_answer_number = 2;
			else if (ra_nr3.isSelected())
				correct_answer_number = 3;
			else
				correct_answer_number = 4;

			ComponentClass przejdz = new ComponentClass();
			przejdz.okno("/app/View/ConfirmQuestionsView.fxml", "Zatwierdzenie pytania");

		}

	}

}
