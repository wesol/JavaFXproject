package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import app.Model.Stat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class StudentsStatsController {

	DBConnector db;
	public ObservableList<Stat> data;
	public ObservableList<Stat> data_help;

	@FXML
	private TableView<Stat> table_Stat;

	@FXML
	private TableColumn<Stat, String> col_name;

	@FXML
	private TableColumn<Stat, String> col_last;

	@FXML
	private TableColumn<Stat, String> col_group;

	@FXML
	private TableColumn<Stat, Integer> col_testNumber;

	@FXML
	private TableColumn<Stat, Double> col_avg;

	@FXML
	private Button btn_exit;

	@FXML
	private Button btn_back;

	@FXML
	private TextField tf_filter;
	@FXML
	TableView<Stat> table_StatHelp;
	@FXML
	TableColumn<Stat, String> col_nameHelp;
	@FXML
	TableColumn<Stat, String> col_lastHelp;
	@FXML
	TableColumn<Stat, Double> col_result;
	@FXML
	Button btn_help;
	@FXML
	BarChart<String, Double> bar_statHelp;

	@FXML
	public void filter(KeyEvent event) throws ClassNotFoundException, SQLException {
		table_StatHelp.setVisible(false);
		bar_statHelp.setVisible(false);

		Connection conn = db.Connection();
		data = FXCollections.observableArrayList();
		String search = tf_filter.getText();
		if (!search.equals("")) {
			ResultSet rs = conn.createStatement().executeQuery(
					"select kursant.imie, kursant.nazwisko, kursant.edycja, count(statystyki.id), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki join kursant on kursant.login = statystyki.login where locate('"
							+ search + "',kursant.imie)!=0 or locate('" + search + "',kursant.nazwisko)!=0 group by kursant.imie, kursant.nazwisko, kursant.edycja");

			while (rs.next()) {
				data.add(new Stat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));

				col_name.setCellValueFactory(new PropertyValueFactory<Stat, String>("name"));
				col_last.setCellValueFactory(new PropertyValueFactory<Stat, String>("last"));
				col_group.setCellValueFactory(new PropertyValueFactory<Stat, String>("group"));
				col_testNumber.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("ilosc"));
				col_avg.setCellValueFactory(new PropertyValueFactory<Stat, Double>("poprawne"));
				table_Stat.setItems(null);
				table_Stat.setItems(data);
			}
		} else
			initialize();

	}

	@FXML
	public void showAllStudents(ActionEvent event) throws SQLException {
		table_StatHelp.setVisible(false);
		bar_statHelp.setVisible(false);

		Connection con = db.Connection();
		Statement stmt = con.createStatement();
		data = FXCollections.observableArrayList();
		ResultSet rs = stmt.executeQuery(
				"select kursant.imie, kursant.nazwisko, kursant.edycja, count(statystyki.id), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki \n"
						+ "join kursant on kursant.login = statystyki.login\n"
						+ "group by kursant.imie, kursant.nazwisko, kursant.edycja;");
		while (rs.next()) {
			data.add(new Stat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
		}
		col_name.setCellValueFactory(new PropertyValueFactory<Stat, String>("name"));
		col_last.setCellValueFactory(new PropertyValueFactory<Stat, String>("last"));
		col_group.setCellValueFactory(new PropertyValueFactory<Stat, String>("group"));
		col_testNumber.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("ilosc"));
		col_avg.setCellValueFactory(new PropertyValueFactory<Stat, Double>("poprawne"));
		table_Stat.setItems(null);
		table_Stat.setItems(data);

		con.close();
	}

	@FXML
	public void showHelp(MouseEvent event) throws SQLException {
		table_StatHelp.setVisible(true);
		bar_statHelp.setVisible(true);
		Connection conn = db.Connection();
		data_help = FXCollections.observableArrayList();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		String search_help = tf_filter.getText();
		ResultSet rs_help = conn.createStatement()
				.executeQuery("select kursant.imie, kursant.nazwisko, statystyki.procent_poprawnych \n"
						+ "from statystyki join kursant on kursant.login = statystyki.login where locate('" + search_help
						+ "',kursant.imie)!=0 or locate('" + search_help + "',kursant.nazwisko)!=0;");

		while (rs_help.next()) {
			data_help.add(new Stat(rs_help.getString(1), rs_help.getString(2), rs_help.getDouble(3)));
			System.out.println();

		}
		col_nameHelp.setCellValueFactory(new PropertyValueFactory<Stat, String>("name"));
		col_lastHelp.setCellValueFactory(new PropertyValueFactory<Stat, String>("last"));
		col_result.setCellValueFactory(new PropertyValueFactory<Stat, Double>("poprawne"));
		table_StatHelp.setItems(null);
		table_StatHelp.setItems(data_help);

		ResultSet rs_chart = conn.createStatement().executeQuery(
				"select kursant.imie, kursant.nazwisko, kursant.edycja, count(statystyki.id), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki join kursant on kursant.login = statystyki.login where locate('"
						+ search_help + "',kursant.imie)!=0 or locate('" + search_help
						+ "',kursant.nazwisko)!=0 group by kursant.imie, kursant.nazwisko, kursant.edycja");

		while (rs_chart.next()) {
			series.getData().add(new XYChart.Data<>(rs_chart.getString(1), rs_chart.getDouble(2)));
			System.out.println(rs_chart.getString(1));
		}

		ResultSet average = conn.createStatement().executeQuery(
				"select 'œrednie wszystkich uczestników', round(sum(procent_poprawnych)/count(id)) from statystyki;");
		while (average.next()) {
			series.getData().add(new XYChart.Data<>(average.getString(1), average.getDouble(2)));
		}

		bar_statHelp.getData().clear();
		bar_statHelp.getData().add(series);

		conn.close();
	}

	@FXML
	public void buttonExit(MouseEvent event) throws IOException {
		ComponentClass goTo = new ComponentClass();
		goTo.okno("/app/View/AdminView.fxml", "Panel Egzaminatora", event);
	}

	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
		ActionEvent event = null;
		showAllStudents(event);

	}
}