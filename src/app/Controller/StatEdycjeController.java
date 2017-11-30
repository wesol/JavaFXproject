package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;
import app.Model.Edycja;
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

public class StatEdycjeController {

	@FXML
	private TableView<Edycja> table_stat;

	@FXML
	private TableColumn<Edycja, String> col_edycja;

	@FXML
	private TableColumn<Edycja, Integer> col_procent;

	@FXML
	private Button btn_wyborEdycji;

	@FXML
	private TextField tf_wyborEdycji;

	@FXML
	BarChart<String, Integer> bar_chart;

	@FXML
	CategoryAxis bar_X;

	@FXML
	NumberAxis bar_Y;

	@FXML
	ComboBox<String> cb_wybierzEdycje;

	@FXML
	Button btn_wszystkieEdycje;

	@FXML
	Button btn_wybierzEdycje;

	DBConnector db;
	public ObservableList<String> edycje;
	public ObservableList<Edycja> data;
	private MouseEvent event;

	@FXML
	Label lbl_wybierzEdycje;

	@FXML Button btn_exit;

	@FXML
	public void wszystkieEdycje(MouseEvent event) throws SQLException {
		lbl_wybierzEdycje.setVisible(false);
		Connection conn = db.Connection();
		data = FXCollections.observableArrayList();

		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		ResultSet rs = conn.createStatement().executeQuery(
				"select kursant.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki\r\n"
						+ "join kursant on kursant.login = statystyki.login\r\n" + "group by kursant.edycja\r\n"
						+ "order by edycja;");

		while (rs.next()) {
			data.add(new Edycja(rs.getString(1), rs.getInt(2)));
			series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
		}

		ResultSet srednie = conn.createStatement()
				.executeQuery("select 'œrednia', round(sum(procent_poprawnych)/count(id)) from statystyki;");
		while (srednie.next()) {
			series.getData().add(new XYChart.Data<>(srednie.getString(1), srednie.getInt(2)));
		}

		col_edycja.setCellValueFactory(new PropertyValueFactory<Edycja, String>("edycja"));
		col_procent.setCellValueFactory(new PropertyValueFactory<Edycja, Integer>("procent"));
		table_stat.setItems(null);
		table_stat.setItems(data);

		bar_chart.getData().clear();
		bar_chart.getData().add(series);

		conn.close();
	}

	@FXML
	public void zaladujEdycje(MouseEvent event) throws SQLException {
		lbl_wybierzEdycje.setVisible(false);
		Connection conn = db.Connection();

		edycje = FXCollections.observableArrayList();

		ResultSet rs_edycje = conn.createStatement().executeQuery("select distinct edycja from kursant;");
		while (rs_edycje.next()) {
			edycje.add(rs_edycje.getString(1));
		}
		cb_wybierzEdycje.setItems(edycje);
		conn.close();
	}

	@FXML
	public void wyswietlEdycjeWTabeli(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		data = FXCollections.observableArrayList();

		if (!(cb_wybierzEdycje.getValue() == null)) {
			lbl_wybierzEdycje.setVisible(false);

			ResultSet rs_wybranaEdycja = conn.createStatement().executeQuery(
					"select kursant.edycja, round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki\r\n"
							+ "join kursant on kursant.login = statystyki.login where kursant.edycja = '"
							+ cb_wybierzEdycje.getValue().toString() + "';");

			while (rs_wybranaEdycja.next()) {
				data.add(new Edycja(rs_wybranaEdycja.getString(1), rs_wybranaEdycja.getInt(2)));
				series.getData().add(new XYChart.Data<>(rs_wybranaEdycja.getString(1), rs_wybranaEdycja.getInt(2)));
			}
			col_edycja.setCellValueFactory(new PropertyValueFactory<Edycja, String>("edycja"));
			col_procent.setCellValueFactory(new PropertyValueFactory<Edycja, Integer>("procent"));
			table_stat.setItems(null);
			table_stat.setItems(data);

			// WYKRES: dodanie s³upka œredniej wyników do wykresu
			ResultSet srednie = conn.createStatement()
					.executeQuery("select 'œrednia', round(sum(procent_poprawnych)/count(id)) from statystyki;");
			while (srednie.next()) {
				series.getData().add(new XYChart.Data<>(srednie.getString(1), srednie.getInt(2)));
			}
			bar_chart.getData().clear();
			bar_chart.getData().add(series);

			conn.close();
		} else {
			lbl_wybierzEdycje.setVisible(true);
		}
	}


	@FXML public void buttonExit(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/AdminView.fxml", "Panel Egzaminatora", event);
	}
	
	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
		wszystkieEdycje(event);
		zaladujEdycje(event);
	}

}
