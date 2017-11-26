package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import app.Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

public class ListakursView {

	@FXML
	TableView<Users> table_view;

	// obiekt połączenia z db
	public DBConnector db;

	// obiekt do przechowywania listy
	public ObservableList<Users> data;

	@FXML Button btn_select;

	public void initialize() throws IOException, SQLException {
		db = new DBConnector();
	}

	@FXML
	void btnSelectAction(ActionEvent event) throws IOException, SQLException {

		data = FXCollections.observableArrayList();

		Connection conn1 = db.Connection();
		Statement stmt = conn1.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ankieta;");

		while (rs.next()) {
			// data.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
			// rs.getDouble(4)));
		}
		conn1.close();
		System.out.println(data);

		table_view.setItems(null);
		table_view.setItems(data);

	}
}