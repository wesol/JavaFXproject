package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/AdminView.fxml"));
			Scene scene = new Scene(parent);
			//scene.getStylesheets().add(getClass().getResource("").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Logowanie");
			primaryStage.show();

		} catch (Exception e) {
			System.out.println("blad");
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
