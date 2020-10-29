package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ClientController implements Initializable {

	@FXML
	private TextField textFieldNom;
	@FXML
	private TextField textFieldPrenom;
	@FXML
	private TextField textFieldIdentifiant;
	@FXML
	private TextField textFieldMotDePasse;
	@FXML
	private TextField textFieldNumeroAdresse;
	@FXML
	private TextField textFieldVoieAdresse;
	@FXML
	private TextField textFieldCodePostal;
	@FXML
	private TextField textFieldVille;
	@FXML
	private TextField textFieldPays;
	@FXML
	private ChoiceBox<String> choiceBoxTriage;
	@FXML
	private Label labelAffichage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBoxTriage.getItems().add("nom");
		choiceBoxTriage.getItems().add("ville et nom");
		
	}	
	
	public void creerClient() {
		if(!error()) {
			labelAffichage.setText(textFieldNom.getText()+" "+textFieldPrenom.getText());
		}
	}
	
	public boolean error() {
		String erreur="";
		if(textFieldNom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le nom \n";
		}
		if(textFieldPrenom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le prenom \n";
		}
		if(textFieldIdentifiant.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le identifiant \n";
		}
		if(textFieldMotDePasse.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le mot de passe \n";
		}
		if(textFieldNom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le Nom \n";
		}
		if(textFieldNumeroAdresse.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le Numéro d'adresse \n";
		}
		if(textFieldVoieAdresse.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le voie d'addresse \n";
		}
		if(textFieldCodePostal.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le code postal \n";
		}
		if(textFieldVille.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le ville \n";
		}
		if(textFieldPays.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le pays \n";
		}
		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	
}
