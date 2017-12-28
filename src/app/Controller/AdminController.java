package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Tooltip;

public class AdminController {


	@FXML
	Button btn_addQuestion;

	@FXML
	Button btn_exit;

	@FXML
	Button btn_courseList;

	@FXML Tooltip tt_exit;

	@FXML Button btn_studentsStats;

	@FXML Button btn_editionStats;


	@FXML
	void goToAddQuestion(MouseEvent event) throws IOException {
		ComponentClass przejdz = new ComponentClass();
		przejdz.okno("/app/View/AddQuestionView.fxml", "Dodanie pytania", event);

	}

	@FXML
	void goToStudents(MouseEvent event) throws IOException {
		ComponentClass przejdz = new ComponentClass();
		przejdz.okno("/app/View/StudentsStatsView.fxml", "Statystyki Kursantów", event);

	}

	@FXML
	void goToEditions(MouseEvent event) throws IOException {
		ComponentClass przejdz = new ComponentClass();
		przejdz.okno("/app/View/EditionsStatsView.fxml", "Statystyki Edycji", event);
	}
	
	@FXML
	void exit(MouseEvent event) throws IOException {
		ComponentClass przejdz = new ComponentClass();
		przejdz.okno("/app/View/LogView.fxml", "Logowanie", event);
	}



}
