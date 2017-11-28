package app.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.Database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
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
    private RadioButton rb1;
    
    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;
    
  
    @FXML
    private Button bt_zatwierdz;
    
    int max_i = Integer.valueOf(WyborController.l_pyt); 
    
    boolean wybor_python = WyborController.python ;
	boolean wybor_java = WyborController.java ;
	boolean wybor_bd = WyborController.bd ;
	boolean wybor_git = WyborController.git ;
	boolean wybor_fe = WyborController.fe ;
	boolean wybor_spring = WyborController.spring ;
  
    int i = 2;
    int a = 1;
    @FXML
    void buttonZatwierdz(MouseEvent event) throws SQLException {
    	
    	ArrayList<String> lista_pyt = new ArrayList<String>();
    	
		if (wybor_python == true) {
    		lista_pyt.add("python");
    	}
		if (wybor_java == true) {
    		lista_pyt.add("java");
    	}
		if (wybor_bd == true) {
    		lista_pyt.add("bd");
    	}
		if (wybor_git == true) {
    		lista_pyt.add("git");
    	}
		if (wybor_fe == true) {
    		lista_pyt.add("fe");
    	}
		if (wybor_spring == true) {
    		lista_pyt.add("spring");
    	}
    	
    	if(i <= max_i && lista_pyt.size() != 0) {
		lb_nrPytania.setText("Pytanie nr"+ i);

    	lista = FXCollections.observableArrayList();
		Connection conn1 = db.Connection();
		Statement stmt = conn1.createStatement();
		PreparedStatement ps = null;
	
		String warunek = "";
		for( int l = 0; l <lista_pyt.size(); l++) {
			if (l == lista_pyt.size()-1) {
				warunek += "'"+lista_pyt.get(l).toString()+"'";
			}else {
			warunek += "'"+lista_pyt.get(l).toString()+"',";
			}
			ResultSet rs_tresc = stmt.executeQuery("select id_p, pytanie from baza_pytan where zakres in ("+warunek+") "
					+ "and id_p not in (select id_p from pytania_wylosowane) order by rand() asc limit 1");
			rs_tresc.next();
			lb_trescPytania.setText(rs_tresc.getString(2));
			ps = conn1.prepareStatement("insert into pytania_wylosowane (id_p) values("+rs_tresc.getInt(1)+");");
			ps.executeUpdate();	
    	if (i == max_i) {
		 ps = conn1.prepareStatement("truncate pytania_wylosowane;");
			ps.executeUpdate();
    	}
 
    	}
    	}
		 i ++;
    }
 
    public void initialize() {
		db = new DBConnector();
		
		lb_nrPytania.setText("Pytanie nr"+ a);
		lb_trescPytania.setText("Jakis przykald");
		
		}
   
    }

