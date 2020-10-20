package controleur;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleurAjoutProduit implements Initializable{

	@FXML
	private TextField textfieldNom;
	@FXML
	private TextField textfieldTarif;
	@FXML
	private TextArea textAreaDescription;
	@FXML
	private Label LabelAffichage;
	@FXML
	private ChoiceBox<String> choiceboxCategorie;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceboxCategorie.getItems().add("pull");
		choiceboxCategorie.getItems().add("Ipad");
		choiceboxCategorie.getItems().add("T-shirt");
	}
	
	public void creerModele() {
		
		
		if(!error()) {
		LabelAffichage.setText(textfieldNom.getText() +"("+choiceboxCategorie.getValue()+") , " + textfieldTarif.getText() + " euros");
	}
	}

	
	public   boolean error() {
		String erreur="";
		if(textfieldNom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le Nom \n";

		}
		if(textfieldTarif.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le Tarif \n";
		}
		if(choiceboxCategorie.getValue()==null){
			erreur+=" Veuillez saisir le Catégorie \n";
		}

		if(textAreaDescription.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir la description \n";
		}
		
		
		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}

}
