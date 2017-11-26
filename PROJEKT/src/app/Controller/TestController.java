package app.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import app.Database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class TestController {
	public DBConnector db;
	
	ObservableList lista;

    @FXML
    private Label lb_nrPytania;

    
    @FXML
    private Label lb_trescPytania;
    	
		
    @FXML
    private ToggleGroup t1;

    @FXML
    private Button bt_zatwierdz;

    @FXML
    void buttonZatwierdz(MouseEvent event) {
    	lista = FXCollections.observableArrayList();

		Connection conn1 = db.Connection();
		Statement stmt = conn1.createStatement();
		ResultSet rs_tresc = stmt.executeQuery(select);
    	

    }
    public void initialize() {
		db = new DBConnector();
    }
}
