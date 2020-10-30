package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import Metier.CMCommande;
import Metier.CMLignedeCommande;
import Metier.CMProduit;
import dao.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vue.VueAccueil;
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
	private ChoiceBox<String> choiceBoxCommande;
	@FXML
	private Button buttonCreer1;
	@FXML
	private Button buttonCreer2;
	@FXML
	private Button buttonModifier1;
	@FXML
	private Button buttonSupprimer1;
	@FXML
	private Button buttonModifier2;
	@FXML
	private Button buttonSupprimer2;
	@FXML
	private Button buttonRetour;
	@FXML
	private Label labelAffichage;
	@FXML
	private TableView<CMCommande> tableViewCommande;
	@FXML
	private TableView<CMLignedeCommande> tableViewLigneDeCommande;
	@FXML
	private TableColumn<CMCommande,Integer> tableColumnIdCommande;
	@FXML
	private TableColumn<CMCommande,Date> tableColumnDateCommande;
	@FXML
	private TableColumn<CMCommande,Integer> tableColumnIdClient;
	@FXML
	private TableColumn<CMLignedeCommande,Integer> tableColumnIdCommande2;
	@FXML
	private TableColumn<CMLignedeCommande,Double> tableColumnTarifUnitaire;
	@FXML
	private TableColumn<CMLignedeCommande,Integer> tableColumnIdProduit;
	@FXML
	private TableColumn<CMLignedeCommande,Integer> tableColumnQuantite;
	
	private ObservableList<CMCommande> teamMembersC;
	private ObservableList<CMLignedeCommande> teamMembersL;


	public void initialize(URL arg0, ResourceBundle arg1) {
		choiceBoxCommande.getItems().add("produit");
		choiceBoxCommande.getItems().add("client");
		try {
			teamMembersC=FXCollections.observableArrayList(DAOFactory.getDAOFactory(AccueilController.Peri).getCommandeDAO().findAll());
			teamMembersL=FXCollections.observableArrayList(DAOFactory.getDAOFactory(AccueilController.Peri).getLignedeCommandeDAO().findAll());
		
			FilteredList<CMCommande> filteredDataC= new FilteredList<>(teamMembersC,p->true);
			SortedList<CMCommande> sortedDataC = new SortedList<>(filteredDataC);
			sortedDataC.comparatorProperty().bind(tableViewCommande.comparatorProperty());
			this.tableViewCommande.setItems(sortedDataC);
			
			FilteredList<CMLignedeCommande> filteredDataL= new FilteredList<>(teamMembersL,p->true);
			SortedList<CMLignedeCommande> sortedDataL = new SortedList<>(filteredDataL);
			sortedDataL.comparatorProperty().bind(tableViewLigneDeCommande.comparatorProperty());
			this.tableViewLigneDeCommande.setItems(sortedDataL);
			
			tableColumnIdCommande.setCellValueFactory(new PropertyValueFactory<CMCommande,Integer>("idCommande"));
			tableColumnDateCommande.setCellValueFactory(new PropertyValueFactory<CMCommande,Date>("dateCommande"));
			tableColumnIdClient.setCellValueFactory(new PropertyValueFactory<CMCommande,Integer>("idClient2"));
			tableColumnIdCommande2.setCellValueFactory(new PropertyValueFactory<CMLignedeCommande,Integer>("idCommande"));
			tableColumnIdProduit.setCellValueFactory(new PropertyValueFactory<CMLignedeCommande,Integer>("idProduit"));
			tableColumnQuantite.setCellValueFactory(new PropertyValueFactory<CMLignedeCommande,Integer>("quantite"));
			tableColumnTarifUnitaire.setCellValueFactory(new PropertyValueFactory<CMLignedeCommande,Double>("tarifUnitaire"));
			this.tableViewCommande.getSortOrder().add(tableColumnIdCommande);
			
			buttonCreer1.setDisable(true);
			buttonCreer2.setDisable(true);
			buttonModifier1.setDisable(true);
			buttonSupprimer1.setDisable(true);
			buttonModifier2.setDisable(true);
			buttonSupprimer2.setDisable(true);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void CreerCommande() {
		if(!error()) {
			//labelAffichage.setText("id commande:"+textFieldIdCommande.getText()+", id produit:"+textFieldIdProduit.getText());
		int client;
		try {
			client=DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().getById(Integer.parseInt(textFieldIdClient.getText())).getIdClient();
			CMCommande cmc= new CMCommande(textFieldDateCommande.getText(),client);
			DAOFactory.getDAOFactory(AccueilController.Peri).getCommandeDAO().create(cmc);
			
			textFieldDateCommande.setText("");
			textFieldIdClient.setText("");
			this.teamMembersC.add(cmc);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public void CreerLigneDeCommande() {
		if(!error2()) {
			//labelAffichage.setText("id commande:"+textFieldIdCommande.getText()+", id produit:"+textFieldIdProduit.getText());
			try {
			CMCommande cmp=this.tableViewCommande.getSelectionModel().getSelectedItem();
			
			CMProduit pro=DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().getById(Integer.parseInt(textFieldIdProduit.getText()));
			CMLignedeCommande clc = new CMLignedeCommande(cmp.getIdCommande(),pro.getIdProduit(),Integer.parseInt(textFieldQuantite.getText()), pro.getTarif());
			DAOFactory.getDAOFactory(AccueilController.Peri).getLignedeCommandeDAO().create(clc);
			textFieldIdProduit.setText("");
			textFieldQuantite.setText("");
			this.teamMembersL.add(clc);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void modifierCommande(){
		if(!error()) {
			try {
				int idco=this.tableViewCommande.getSelectionModel().getSelectedItem().getIdCommande();
				int index=this.tableViewCommande.getSelectionModel().getFocusedIndex();
				int client=DAOFactory.getDAOFactory(AccueilController.Peri).getClientDAO().getById(Integer.parseInt(textFieldIdClient.getText())).getIdClient();
				
				CMCommande cmco=new CMCommande(idco,textFieldDateCommande.getText(),client);
				DAOFactory.getDAOFactory(AccueilController.Peri).getCommandeDAO().update(cmco);
				textFieldDateCommande.setText("");
				textFieldIdClient.setText("");
				
				this.teamMembersC.set(index, cmco);
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
	public void supprimerCommande() {
		
		CMCommande cmp=this.tableViewCommande.getSelectionModel().getSelectedItem();
		
		try {
			teamMembersC.remove(cmp);
			DAOFactory.getDAOFactory(AccueilController.Peri).getCommandeDAO().delete(cmp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void modifierLigneDeCommande() {
		if(!error2()) {
			try {
				int idco=this.tableViewLigneDeCommande.getSelectionModel().getSelectedItem().getIdCommande();
				int index=this.tableViewLigneDeCommande.getSelectionModel().getFocusedIndex();
				CMProduit cmp=DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().getById(Integer.parseInt(textFieldIdProduit.getText()));
				CMLignedeCommande cmli=new CMLignedeCommande(idco,cmp.getIdProduit(),Integer.parseInt(textFieldQuantite.getText()),cmp.getTarif());
				DAOFactory.getDAOFactory(AccueilController.Peri).getLignedeCommandeDAO().update(cmli);
				textFieldQuantite.setText("");
				textFieldIdProduit.setText("");
				
				this.teamMembersL.set(index, cmli);
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
	public void supprimerLigneDeCommande() {
		CMLignedeCommande clp=this.tableViewLigneDeCommande.getSelectionModel().getSelectedItem();
		
		try {
			teamMembersL.remove(clp);
			DAOFactory.getDAOFactory(AccueilController.Peri).getLignedeCommandeDAO().delete(clp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void versAccueil() {
		new VueAccueil().start(new Stage());
		this.quitter();

	}
	public void quitter() {
	Stage stage = (Stage) buttonRetour.getScene().getWindow();
	stage.close();
	}
	public void cliquertable1() {
		buttonSupprimer1.setDisable(false);
		buttonSupprimer2.setDisable(true);
		buttonCreer1.setDisable(true);
		buttonCreer2.setDisable(true);
		buttonModifier1.setDisable(false);
		buttonModifier2.setDisable(true);
	}
	public void cliquertable2() {
		buttonSupprimer1.setDisable(true);
		buttonSupprimer2.setDisable(false);
		buttonCreer1.setDisable(true);
		buttonCreer2.setDisable(true);
		buttonModifier1.setDisable(true);
		buttonModifier2.setDisable(false);
	}
	public void cliquertext1() {
		buttonCreer1.setDisable(false);
		buttonCreer2.setDisable(true);
		buttonModifier1.setDisable(true);
		buttonSupprimer1.setDisable(true);
		buttonModifier2.setDisable(true);
		buttonSupprimer2.setDisable(true);
	}
	public void cliquertext2() {
		buttonCreer1.setDisable(true);
		buttonCreer2.setDisable(false);
		buttonModifier1.setDisable(true);
		buttonSupprimer1.setDisable(true);
		buttonModifier2.setDisable(true);
		buttonSupprimer2.setDisable(true);
	}
	public void cliquerblanc() {
		buttonCreer1.setDisable(true);
		buttonCreer2.setDisable(true);
		buttonModifier1.setDisable(true);
		buttonSupprimer1.setDisable(true);
		buttonModifier2.setDisable(true);
		buttonSupprimer2.setDisable(true);
		this.tableViewLigneDeCommande.getSelectionModel().clearSelection();
	}
	
	public boolean error() {
		String erreur="";
		if(textFieldIdClient.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le id_client \n";
		}
		if(textFieldDateCommande.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le date de commande \n";
		}
		
		
		
		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}
	public boolean error2() {
		String erreur="";
		if(textFieldIdProduit.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le id_produit \n";
		}
		if(textFieldQuantite.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le quantite \n";
		}
		
		
		
		if(!erreur.equals("")) {
			Alert alert=new Alert(AlertType.ERROR,erreur,ButtonType.OK);
			alert.showAndWait();
			return true;
		}
		return false;
	}
	
	
	
	
	
}
