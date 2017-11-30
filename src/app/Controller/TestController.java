package app.Controller;


import java.io.IOException;
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
	
	ObservableList lista;

    @FXML
    private Label lb_nrPytania;
    
    
    @FXML
    private Label lb_trescPytania;
    	
		
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
    private Button bt_zatwierdz;
    
    public static int il_odp_poprawnych;
    public static int odp_poprawna;

	public static ArrayList<Integer> lista_wyswietlonych_pytan = new ArrayList<Integer>();
    
    public static int il_pytan = Integer.valueOf(WyborController.l_pyt); 
    
    boolean wybor_python = WyborController.python ; //pobranie z klasy wyborController CheckBox-a przypisanej do zmiennej 
	boolean wybor_java = WyborController.java ;
	boolean wybor_bd = WyborController.bd ;
	boolean wybor_git = WyborController.git ;
	boolean wybor_fe = WyborController.fe ;
	boolean wybor_spring = WyborController.spring ;
  
    int i = 1;
   
    
	private MouseEvent event;


    @FXML
    void buttonZatwierdz(MouseEvent event) throws SQLException, IOException {
    	
    	ArrayList<String> lista_pyt = new ArrayList<String>(); //wrzucamy do listy stringi ( java, pyhon..) , poniewaz zmienna by³a zapisana jako boolen a potrzebowalismy stringi.
    	
    	
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
    	
    	if(i <= il_pytan && lista_pyt.size() != 0) {// zadawanie pytania do momentu ktory zosta³ okreslony przez uzytkownika
    		
		lb_nrPytania.setText("Pytanie nr"+ i);

    	lista = FXCollections.observableArrayList();
		Connection conn1 = db.Connection();
		Statement stmt = conn1.createStatement();
		PreparedStatement ps = null;
		
		String warunek = ""; 
		for( int l = 0; l <lista_pyt.size(); l++) { 
			if (l == lista_pyt.size()-1) { // sprawdzanie ostatniego elemntu z listy
				warunek += "'"+lista_pyt.get(l).toString()+"'"; // jezeli jest ostatnim element list to dodajemy do "warunku" bez przecinka na koncu
			}else {
			warunek += "'"+lista_pyt.get(l).toString()+"',";// jezeli nie jest to dodajemy przecinek
			}
		}
			ResultSet rs_tresc = stmt.executeQuery("select id, pytanie from Pytania where zakres in ("+warunek+") and id not in (select id from pytania_wylosowane) order by rand() asc limit 1");
			rs_tresc.next();
			lb_trescPytania.setText(rs_tresc.getString(2));
			ps = conn1.prepareStatement("insert into pytania_wylosowane (id) values("+rs_tresc.getInt(1)+");");
			ps.executeUpdate();
			
			ResultSet rs_odp = stmt.executeQuery("select id,odp_1,odp_2,odp_3,odp_4,odp_poprawna from Pytania where "+rs_tresc.getInt(1)+" = id ");
    		rs_odp.next();
    		rb_1.setText(rs_odp.getString(2));
    		rb_2.setText(rs_odp.getString(3));
    		rb_3.setText(rs_odp.getString(4));
    		rb_4.setText(rs_odp.getString(5));
    		odp_poprawna = rs_odp.getInt(6);
    		
    	if (i == il_pytan) {
		 ps = conn1.prepareStatement("truncate pytania_wylosowane;");
			ps.executeUpdate();
    		}
    	}
    	if(i > il_pytan) {
    		
    	 	Stage stage = new Stage();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/Koniec.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Witaj");	
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
    		
	    	
    	}
		 i ++;
    }
 
    public void initialize() throws SQLException, IOException {

		db = new DBConnector();
    	buttonZatwierdz(event);
		
    }
    @FXML
    void radioButton_1(MouseEvent event) {
    	
    	if (odp_poprawna == 1) {
			il_odp_poprawnych++;
    	}
    }

    @FXML
    void radioButton_2(MouseEvent event) {
    	if (odp_poprawna == 2) {
			il_odp_poprawnych++;
    	}
    }
    @FXML
    void radioButton_3(MouseEvent event) {
    	if (odp_poprawna == 3) {
			il_odp_poprawnych++;
    	}

    }

    @FXML
    void radioButton_4(MouseEvent event) {
    	if (odp_poprawna == 4) {
			il_odp_poprawnych++;
    	}

    }

}
		
		
		
   
    

