package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TestController {
	public DBConnector db;

	@FXML
	private Label lb_questionNumber;

	@FXML
	private Label lb_questionContents;

	@FXML
	private RadioButton rb_1;

	@FXML
	private ToggleGroup t1;

	@FXML
	private RadioButton rb_3;

	@FXML
	private RadioButton rb_2;

	@FXML
	private RadioButton rb_4;

	@FXML
	private Button btn_confirm;

	public static int quantityOfCorrect;
	public static int correctAswer;

	public static ArrayList<Integer> listOfShowingQuestion = new ArrayList<Integer>();

	public static int quantityOfQuestions = Integer.valueOf(TestChoiceController.quantinyOfQuestionsTC);

	boolean choice_python = TestChoiceController.python;
	boolean choice_java = TestChoiceController.java;
	boolean choice_bd = TestChoiceController.bd;
	boolean choice_git = TestChoiceController.git;
	boolean choice_fe = TestChoiceController.fe;
	boolean choice_spring = TestChoiceController.spring;

	int i = 1;

	private MouseEvent event;

	@FXML
	void confirm(MouseEvent event) throws SQLException, IOException {

		ArrayList<String> lista_pyt = new ArrayList<String>();

		if (choice_python == true) {
			lista_pyt.add("python");
		}
		if (choice_java == true) {
			lista_pyt.add("java");
		}
		if (choice_bd == true) {
			lista_pyt.add("bd");
		}
		if (choice_git == true) {
			lista_pyt.add("git");
		}
		if (choice_fe == true) {
			lista_pyt.add("fe");
		}
		if (choice_spring == true) {
			lista_pyt.add("spring");
		}

		if (i <= quantityOfQuestions && lista_pyt.size() != 0) {

			lb_questionNumber.setText("Pytanie nr " + i);

			Connection conn1 = db.Connection();
			Statement stmt = conn1.createStatement();
			PreparedStatement ps = null;

			String warunek = "";
			for (int l = 0; l < lista_pyt.size(); l++) {
				if (l == lista_pyt.size() - 1) {
					warunek += "'" + lista_pyt.get(l).toString() + "'";
				} else {
					warunek += "'" + lista_pyt.get(l).toString() + "',";
				}
			}
			ResultSet rs_tresc = stmt.executeQuery("select id, pytanie from Pytania where zakres in (" + warunek
					+ ") and id not in (select id from pytania_wylosowane) order by rand() asc limit 1");
			rs_tresc.next();
			lb_questionContents.setText(rs_tresc.getString(2));
			ps = conn1.prepareStatement("insert into pytania_wylosowane (id) values(" + rs_tresc.getInt(1) + ");");
			ps.executeUpdate();

			ResultSet rs_odp = stmt.executeQuery("select id,odp_1,odp_2,odp_3,odp_4,odp_poprawna from Pytania where "
					+ rs_tresc.getInt(1) + " = id ");
			rs_odp.next();
			rb_1.setText(rs_odp.getString(2));
			rb_2.setText(rs_odp.getString(3));
			rb_3.setText(rs_odp.getString(4));
			rb_4.setText(rs_odp.getString(5));
			correctAswer = rs_odp.getInt(6);

			if (i == quantityOfQuestions) {
				ps = conn1.prepareStatement("truncate pytania_wylosowane;");
				ps.executeUpdate();
			}
		}
		if (i > quantityOfQuestions) {

			Stage stage = new Stage();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/EndView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Koniec testu");
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();

		}
		i++;
	}

	public void initialize() throws SQLException, IOException {

		db = new DBConnector();
		confirm(event);

	}

	@FXML
	void radioButton_1(MouseEvent event) {

		if (correctAswer == 1) {
			quantityOfCorrect++;
		}
	}

	@FXML
	void radioButton_2(MouseEvent event) {
		if (correctAswer == 2) {
			quantityOfCorrect++;
		}
	}

	@FXML
	void radioButton_3(MouseEvent event) {
		if (correctAswer == 3) {
			quantityOfCorrect++;
		}

	}

	@FXML
	void radioButton_4(MouseEvent event) {
		if (correctAswer == 4) {
			quantityOfCorrect++;
		}

	}

}
