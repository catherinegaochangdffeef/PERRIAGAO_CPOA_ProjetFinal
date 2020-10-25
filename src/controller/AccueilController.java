package controller;
import controller.ProduitController;
import controller.AccueilController;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import dao.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vue.VueAccueil;
import vue.VueProduit;
import vue.VueCommande;
import vue.VueCategorie;
import vue.VueClient;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AccueilController implements Initializable  {

	@FXML
	private Menu Menuvoir;
	@FXML
	private Menu Persistance;
	@FXML
	private MenuItem MenuItemProduit;
	@FXML
	private MenuItem MenuItemClient;
	@FXML
	private MenuItem MenuItemCategorie;
	@FXML
	private MenuItem MenuItemCommande;
	@FXML
	private MenuItem MenuItemSQL;
	@FXML
	private MenuItem MenuItemListeMemoire;
	@FXML
	private VBox VboxMenu;
	
	
	//Stage stage=new Stage();
	 DAOFactory.Persistance Peri ;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Peri =DAOFactory.Persistance.MYSQL;
		
	}
	public void PasserASql() {
		Peri =DAOFactory.Persistance.MYSQL;
	}
	public void PasserAListeMemoire() {
		Peri= DAOFactory.Persistance.ListMemoire;
	}
	
	
	// changer � l'interface de commande
	public void VerCommande() {
		new VueCommande().start(new Stage());
	}
	// changer � l'interface du produit
	public void VerProduit(ActionEvent event) throws Exception {
		new VueProduit().start(new Stage());
	}

	// changer � l'interface du client
	public void VerClient() {
		new VueClient().start(new Stage());
	}
	// Changer � l'interdace du cat�gorie
	public void VerCategorie() {
		new VueCategorie().start(new Stage());
	}
	
	
	
	
	
	
	
}
