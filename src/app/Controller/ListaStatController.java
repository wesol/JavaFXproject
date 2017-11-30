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

public class ListaStatController {

	DBConnector db;
	public ObservableList<Stat> data;
	public ObservableList<Stat> data_pom;

	@FXML
	private TableView<Stat> table_Stat;

	@FXML
	private TableColumn<Stat, String> col_imie;

	@FXML
	private TableColumn<Stat, String> col_nazwisko;

	@FXML
	private TableColumn<Stat, String> col_grupa;

	@FXML
	private TableColumn<Stat, Integer> col_liczbaTest;

	@FXML
	private TableColumn<Stat, Double> col_sredni;

	@FXML
	private Button btn_wyjdz;

	@FXML
	private Button btn_wstecz;

	@FXML
	private TextField tf_filter;
	@FXML
	TableView<Stat> table_StatPom;
	@FXML
	TableColumn<Stat, String> col_imiePom;
	@FXML
	TableColumn<Stat, String> col_nazwiskoPom;
	@FXML
	TableColumn<Stat, Double> col_wynik;
	@FXML
	Button btn_pom;
	@FXML
	BarChart<String, Double> bar_statPom;

	@FXML
	public void filter(KeyEvent event) throws ClassNotFoundException, SQLException {
		table_StatPom.setVisible(false);
		bar_statPom.setVisible(false);

		Connection conn = db.Connection();
		data = FXCollections.observableArrayList();
		String szukaj = tf_filter.getText();
		ResultSet rs = conn.createStatement().executeQuery(
				"select kursant.imie, kursant.nazwisko, kursant.edycja, count(statystyki.id), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) from statystyki join kursant on kursant.login = statystyki.login where locate('"
						+ szukaj + "',kursant.imie)!=0 or locate('" + szukaj + "',kursant.nazwisko)!=0");

		while (rs.next()) {
			data.add(new Stat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
		}

		col_imie.setCellValueFactory(new PropertyValueFactory<Stat, String>("name"));
		col_nazwisko.setCellValueFactory(new PropertyValueFactory<Stat, String>("last"));
		col_grupa.setCellValueFactory(new PropertyValueFactory<Stat, String>("group"));
		col_liczbaTest.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("ilosc"));
		col_sredni.setCellValueFactory(new PropertyValueFactory<Stat, Double>("poprawne"));
		table_Stat.setItems(null);
		table_Stat.setItems(data);

	}

	@FXML
	public void wyswietlWszystkichKursantow(ActionEvent event) throws SQLException {
		table_StatPom.setVisible(false);
		bar_statPom.setVisible(false);

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
		col_imie.setCellValueFactory(new PropertyValueFactory<Stat, String>("name"));
		col_nazwisko.setCellValueFactory(new PropertyValueFactory<Stat, String>("last"));
		col_grupa.setCellValueFactory(new PropertyValueFactory<Stat, String>("group"));
		col_liczbaTest.setCellValueFactory(new PropertyValueFactory<Stat, Integer>("ilosc"));
		col_sredni.setCellValueFactory(new PropertyValueFactory<Stat, Double>("poprawne"));
		table_Stat.setItems(null);
		table_Stat.setItems(data);

		con.close();
	}

	@FXML
	public void wyswietlPom(MouseEvent event) throws SQLException {
		table_StatPom.setVisible(true);
		bar_statPom.setVisible(true);
		Connection conn = db.Connection();
		data_pom = FXCollections.observableArrayList();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		String szukaj_pom = tf_filter.getText();
		ResultSet rs_pom = conn.createStatement()
				.executeQuery("select kursant.imie, kursant.nazwisko, statystyki.procent_poprawnych \n"
						+ "from statystyki join kursant on kursant.login = statystyki.login where locate('" + szukaj_pom
						+ "',kursant.imie)!=0 or locate('" + szukaj_pom + "',kursant.nazwisko)!=0;");

		while (rs_pom.next()) {
			data_pom.add(new Stat(rs_pom.getString(1), rs_pom.getString(2), rs_pom.getDouble(3)));
			System.out.println();

		}
		col_imiePom.setCellValueFactory(new PropertyValueFactory<Stat, String>("name"));
		col_nazwiskoPom.setCellValueFactory(new PropertyValueFactory<Stat, String>("last"));
		col_wynik.setCellValueFactory(new PropertyValueFactory<Stat, Double>("poprawne"));
		table_StatPom.setItems(null);
		table_StatPom.setItems(data_pom);

		ResultSet rs_pomWykres = conn.createStatement().executeQuery(
				"select concat(kursant.imie, ' ', kursant.nazwisko), round(sum(statystyki.procent_poprawnych)/count(statystyki.id)) \n" + 
				"from statystyki join kursant on kursant.login = statystyki.login where locate('" + szukaj_pom + "',kursant.imie)!=0 or locate('" + szukaj_pom + "',kursant.nazwisko)!=0;");

		while (rs_pomWykres.next()) {
			series.getData().add(new XYChart.Data<>(rs_pomWykres.getString(1), rs_pomWykres.getDouble(2)));
			System.out.println(rs_pomWykres.getString(1));
		}
		
		ResultSet srednie = conn.createStatement().executeQuery(
				"select 'œrednie wszystkich uczestników', round(sum(procent_poprawnych)/count(id)) from statystyki;");
		while (srednie.next()) {
			series.getData().add(new XYChart.Data<>(srednie.getString(1), srednie.getDouble(2)));
		}
		
		bar_statPom.getData().clear();
		bar_statPom.getData().add(series);

		conn.close();
	}
	
	@FXML
	public void buttonExit(MouseEvent event) throws IOException {
		Pomocnicze przejdz = new Pomocnicze();
		przejdz.okno("/app/View/AdminView.fxml", "Panel Egzaminatora", event);
	}
	
	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
		ActionEvent event = null;
		wyswietlWszystkichKursantow(event);

	}
}