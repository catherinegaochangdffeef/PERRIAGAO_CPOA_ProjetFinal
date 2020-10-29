package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CategorieController {

	@FXML
	private TextField textFieldTitre;

	@FXML
	private TextField textFieldVisuel;

	@FXML
	private Button buttonCreer;
	@FXML
	private Button buttonModifier;
	@FXML
	private Button buttonSupprimer;
	@FXML
	private Label labelAffichage;
	
	public void creerCategorie() {
		if(!error()) {
			labelAffichage.setText(textFieldTitre.getText()+","+textFieldVisuel.getText());
		}
	}
	
	
	
	public boolean error() {
		String erreur="";
		if(textFieldTitre.getText().trim().isEmpty()) {
			erreur+="Veuillez saisir le titre \n";
		}
		if(textFieldVisuel.getText().trim().isEmpty()) {
			erreur+="Veuiiez saisir le visuel \n";
		}

		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}
}
