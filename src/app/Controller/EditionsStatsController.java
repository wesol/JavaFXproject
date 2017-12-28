package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;
import app.Model.Editing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EditionsStatsController {

	@FXML
	private TableView<Editing> table_stat;

	@FXML
	private TableColumn<Editing, String> col_edition;

	@FXML
	private TableColumn<Editing, Integer> col_percent;

	@FXML
	private Button btn_chooseEdition;

	@FXML
	private TextField tf_chooseEdition;

	@FXML
	Label lbl_chooseEdition;

	@FXML
	Button btn_exit;

	@FXML
	BarChart<String, Integer> bar_chart;

	@FXML
	CategoryAxis bar_X;

	@FXML
	NumberAxis bar_Y;

	@FXML
	ComboBox<String> cb_chooseEdition;

	@FXML
	Button btn_allEdition;

	DBConnector db;
	public ObservableList<String> edycje;
	public ObservableList<Editing> data;
	private MouseEvent event;

	@FXML
	public void allEditions(MouseEvent event) throws SQLException {
		lbl_chooseEdition.setVisible(false);
		Connection conn = db.Connection();
		data = FXCollections.observableArrayList();

		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		ResultSet rs = conn.createStatement().executeQuery(
				"select kursant.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki\r\n"
						+ "join kursant on kursant.login = statystyki.login\r\n" + "group by kursant.edycja\r\n"
						+ "order by edycja;");

		while (rs.next()) {
			data.add(new Editing(rs.getString(1), rs.getInt(2)));
			series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
		}

		ResultSet srednie = conn.createStatement()
				.executeQuery("select 'œrednia', round(sum(procent_poprawnych)/count(id)) from statystyki;");
		while (srednie.next()) {
			series.getData().add(new XYChart.Data<>(srednie.getString(1), srednie.getInt(2)));
		}

		col_edition.setCellValueFactory(new PropertyValueFactory<Editing, String>("edycja"));
		col_percent.setCellValueFactory(new PropertyValueFactory<Editing, Integer>("procent"));
		table_stat.setItems(null);
		table_stat.setItems(data);

		bar_chart.getData().clear();
		bar_chart.getData().add(series);

		conn.close();
	}

	@FXML
	public void editionLoad(MouseEvent event) throws SQLException {
		lbl_chooseEdition.setVisible(false);
		Connection conn = db.Connection();

		edycje = FXCollections.observableArrayList();

		ResultSet rs_edycje = conn.createStatement().executeQuery("select distinct edycja from kursant;");
		while (rs_edycje.next()) {
			edycje.add(rs_edycje.getString(1));
		}
		cb_chooseEdition.setItems(edycje);
		conn.close();
	}

	@FXML
	public void showEditionsInTable(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		data = FXCollections.observableArrayList();

		if (!(cb_chooseEdition.getValue() == null)) {
			lbl_chooseEdition.setVisible(false);

			ResultSet rs_wybranaEdycja = conn.createStatement().executeQuery(
					"select kursant.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki\r\n"
							+ "join kursant on kursant.login = statystyki.login where kursant.edycja = '"
							+ cb_chooseEdition.getValue().toString() + "';");

			while (rs_wybranaEdycja.next()) {
				data.add(new Editing(rs_wybranaEdycja.getString(1), rs_wybranaEdycja.getInt(2)));
				series.getData().add(new XYChart.Data<>(rs_wybranaEdycja.getString(1), rs_wybranaEdycja.getInt(2)));
			}
			col_edition.setCellValueFactory(new PropertyValueFactory<Editing, String>("edycja"));
			col_percent.setCellValueFactory(new PropertyValueFactory<Editing, Integer>("procent"));
			table_stat.setItems(null);
			table_stat.setItems(data);

			// Chart: adding average column to chart
			ResultSet srednie = conn.createStatement()
					.executeQuery("select 'œrednia', round(sum(procent_poprawnych)/count(id)) from statystyki;");
			while (srednie.next()) {
				series.getData().add(new XYChart.Data<>(srednie.getString(1), srednie.getInt(2)));
			}
			bar_chart.getData().clear();
			bar_chart.getData().add(series);

			conn.close();
		} else {
			lbl_chooseEdition.setVisible(true);
		}
	}

	@FXML
	public void buttonExit(MouseEvent event) throws IOException {
		ComponentClass goTo = new ComponentClass();
		goTo.okno("/app/View/AdminView.fxml", "Panel Egzaminatora", event);
	}

	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
		allEditions(event);
		editionLoad(event);
	}

}
