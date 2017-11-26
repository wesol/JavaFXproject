package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WyborController {
	
	 @FXML
	    private CheckBox cb_bd;

	    @FXML
	    private CheckBox cb_git;

	    @FXML
	    private CheckBox cd_Python;

	    @FXML
	    private CheckBox cd_fe;

	    @FXML
	    private CheckBox cd_java;

	    @FXML
	    private CheckBox cd_spring;


    @FXML
    void ButtonPrzejdzDoTestu(MouseEvent event) throws IOException {
    	
    	Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");	
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
    	

    }

}
