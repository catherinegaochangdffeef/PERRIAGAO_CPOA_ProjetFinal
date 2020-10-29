package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;


public class CommandeController implements Initializable {
	
	@FXML
	private TextField textFieldIdCommande;
	@FXML
	private TextField textFieldIdProduit;
	@FXML
	private TextField textFieldIdClient;
	@FXML
	private TextField textFieldDateCommande;
	@FXML
	private TextField textFieldQuantite;
	@FXML
	private TextField textFieldTarifUnitaire;
	@FXML
	private ChoiceBox<String> choiceBoxCommande;
	@FXML
	private Button buttonCreer;
	@FXML
	private Button buttonModifier;
	@FXML
	private Button buttonSupprimer;
	@FXML
	private Label labelAffichage;
	


	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBoxCommande.getItems().add("produit");
		choiceBoxCommande.getItems().add("client");
		
	}
	
	
	public void CreerCommande() {
		if(!error()) {
			labelAffichage.setText("id commande:"+textFieldIdCommande.getText()+", id produit:"+textFieldIdProduit.getText());
		}
	}
	
	
	public boolean error() {
		String erreur="";
		if(textFieldIdCommande.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le id_commande \n";
		}
		if(textFieldIdProduit.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le id_produit \n";
		}
		if(textFieldIdClient.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le id_client \n";
		}
		if(textFieldDateCommande.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le date de commande \n";
		}
		if(textFieldQuantite.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le quantite \n";
		}
		if(textFieldTarifUnitaire.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le tarif unitaire \n";
		}
		
		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	
	
	
	
	
}
