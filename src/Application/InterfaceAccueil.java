package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class InterfaceAccueil extends Application {

	@Override
	public void start(Stage primaryStage){
		try {
		
			Parent root = FXMLLoader.load(getClass().getResource("FenetreAccueil.fxml"));
			Scene scene = new Scene((VBox) root, 600, 400);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("Accueil");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
