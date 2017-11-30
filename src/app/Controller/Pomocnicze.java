package app.Controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Pomocnicze {

	public void okno(String sciezka, String naglowek) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource(sciezka));
		Scene scene = new Scene(parent);
		// scene.getStylesheets().add(getClass().getResource("").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(naglowek);
		stage.show();
	}
		
	public void okno(String sciezka, String naglowek, InputEvent event) throws IOException {
		this.okno(sciezka, naglowek);
		
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	

}
