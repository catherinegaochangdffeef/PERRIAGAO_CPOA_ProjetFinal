package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Metier.CMClient;
import dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vue.VueAccueil;
import javafx.scene.control.Button;

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
	private TextField txtfieldRechercher;

	@FXML
	private Button buttonModifier;
	@FXML
	private Button buttonCreer;
	@FXML
	private Button buttonSupprimer;
	@FXML
	private Button buttonRetourMenu;

	@FXML
	private TableView<CMClient> tableViewClient;
	@FXML
	private TableColumn<CMClient,Integer> tableColumnIdClient;
	@FXML
	private TableColumn<CMClient,String> tableColumnNom;
	@FXML
	private TableColumn<CMClient,String> tableColumnPrenom;
	@FXML
	private TableColumn<CMClient,String> tableColumnIdentifiant;
	@FXML
	private TableColumn<CMClient,String> tableColumnMotDePasse;
	@FXML
	private TableColumn<CMClient,String> tableColumnNumeroAdresse;
	@FXML
	private TableColumn<CMClient,String> tableColumnVoie;
	@FXML
	private TableColumn<CMClient,String> tableColumnCodePostal;
	@FXML
	private TableColumn<CMClient,String> tableColumnVille;
	@FXML
	private TableColumn<CMClient,String> tableColumnPays;
	
	@FXML
	private Label labelAffichage;
	
	private ObservableList<CMClient> teamMembers;
	
	private ArrayList<CMClient> listeClient;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<CMClient,Integer>("idClient"));
		tableColumnNom.setCellValueFactory(new PropertyValueFactory<CMClient,String>("nom"));
		tableColumnPrenom.setCellValueFactory(new PropertyValueFactory<CMClient,String>("prenom"));
		tableColumnIdentifiant.setCellValueFactory(new PropertyValueFactory<CMClient,String>("identifiant"));
		tableColumnMotDePasse.setCellValueFactory(new PropertyValueFactory<CMClient,String>("motDePasse"));
		tableColumnNumeroAdresse.setCellValueFactory(new PropertyValueFactory<CMClient,String>("adrNumero"));
		tableColumnVoie.setCellValueFactory(new PropertyValueFactory<CMClient,String>("adrVoie"));
		tableColumnCodePostal.setCellValueFactory(new PropertyValueFactory<CMClient,String>("adrCodePostal"));
		tableColumnVille.setCellValueFactory(new PropertyValueFactory<CMClient,String>("ville"));
		tableColumnPays.setCellValueFactory(new PropertyValueFactory<CMClient,String>("pays"));
		this.tableViewClient.getSortOrder().add(tableColumnIdClient);
		
		buttonCreer.setDisable(true);
		buttonModifier.setDisable(true);
		buttonSupprimer.setDisable(true);
		
		if (txtfieldRechercher.getText().trim().isEmpty()) {	
		try {
			teamMembers=FXCollections.observableList(DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tableViewClient.setItems(teamMembers);
		}
		else {
			try {
				for (CMClient lc: DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().findAll()) {
					if (String.valueOf(lc.getIdClient()).toLowerCase().contains(txtfieldRechercher.getText())) {
						listeClient.add(lc);
						teamMembers=FXCollections.observableList(listeClient);
						this.tableViewClient.setItems(teamMembers);
					}}
			} catch (Exception e) {}
		}} 
		
		
		
	
	public void versAccueil() {
		new VueAccueil().start(new Stage());
		this.quitter();

	}
	public void quitter() {
	Stage stage = (Stage) buttonRetourMenu.getScene().getWindow();
	stage.close();
	}
	
	public void creerClient() {
		if(!error()) {
			labelAffichage.setText(textFieldNom.getText()+" "+textFieldPrenom.getText());
			try {
				CMClient cmt=new CMClient(textFieldNom.getText(),textFieldPrenom.getText(),textFieldIdentifiant.getText(),textFieldMotDePasse.getText(),textFieldNumeroAdresse.getText(),
						textFieldVoieAdresse.getText(),textFieldCodePostal.getText(),textFieldVille.getText(),textFieldPays.getText());
				
				DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().create(cmt);
				textFieldNom.setText("");
				textFieldPrenom.setText("");
				textFieldIdentifiant.setText("");
				textFieldMotDePasse.setText("");
				textFieldNumeroAdresse.setText("");
				textFieldVoieAdresse.setText("");
				textFieldCodePostal.setText("");
				textFieldVille.setText("");
				textFieldPays.setText("");
				this.teamMembers.add(cmt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void modifierClient() {
		if(!error()) {
			labelAffichage.setText(textFieldNom.getText()+" "+textFieldPrenom.getText());
		try {
			int idclient=this.tableViewClient.getSelectionModel().getSelectedItem().getIdClient();
			int index=this.tableViewClient.getSelectionModel().getFocusedIndex();
			CMClient cmclient=new CMClient(idclient,textFieldNom.getText(),textFieldPrenom.getText(),textFieldIdentifiant.getText(),textFieldMotDePasse.getText(),textFieldNumeroAdresse.getText(),
						textFieldVoieAdresse.getText(),textFieldCodePostal.getText(),textFieldVille.getText(),textFieldPays.getText());
			DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().update(cmclient);
			textFieldNom.setText("");
			textFieldPrenom.setText("");
			textFieldIdentifiant.setText("");
			textFieldMotDePasse.setText("");
			textFieldNumeroAdresse.setText("");
			textFieldVoieAdresse.setText("");
			textFieldCodePostal.setText("");
			textFieldVille.setText("");
			textFieldPays.setText("");
			this.teamMembers.set(index, cmclient);
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
	public void supprimerClient() {
		CMClient cpro=this.tableViewClient.getSelectionModel().getSelectedItem();
		try {
			teamMembers.remove(cpro);
			DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().delete(cpro);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validerRecherche() {
		
	}
	public void cliquertable() {
		buttonSupprimer.setDisable(false);
		buttonCreer.setDisable(true);
		buttonModifier.setDisable(false);
	}
	public void cliquertext() {
		buttonCreer.setDisable(false);
		buttonModifier.setDisable(true);
		buttonSupprimer.setDisable(true);
	}
	public void cliquerblanc() {
		buttonCreer.setDisable(true);
		buttonModifier.setDisable(true);
		buttonSupprimer.setDisable(true);
		this.tableViewClient.getSelectionModel().clearSelection();
	}
	
	public boolean error() {
		String erreur="";
		if(textFieldNom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le nom \n";
		}
		if(textFieldPrenom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le prenom \n";
		}
		/*
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
		*/
		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	
}
