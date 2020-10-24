package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;


public class VueProduit extends Application {
	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Fenetreproduit.fxml"));
			Scene scene = new Scene((VBox) root, 600, 600);		
			primaryStage.setScene(scene);
			primaryStage.setTitle("Produit");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	
}
