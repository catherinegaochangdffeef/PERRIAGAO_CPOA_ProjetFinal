package controller;
import controller.AccueilController;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vue.VueProduit;
import vue.VueCommande;
import vue.VueCategorie;
import vue.VueClient;

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
	@FXML
	private MenuBar menuBar;
	
	//Stage stage=new Stage();
	public static DAOFactory.Persistance Peri ;
	
	

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

	// changer à l'interface de commande
	public void VerCommande() {
		new VueCommande().start(new Stage());
		this.quitter();
	}
	// changer à l'interface du produit
	public void VerProduit(ActionEvent event) throws Exception {
		new VueProduit().start(new Stage());
		this.quitter();
	}

	// changer à l'interface du client
	public void VerClient() {
		new VueClient().start(new Stage());
		this.quitter();
	}
	// Changer à l'interdace du catégorie
	public void VerCategorie() {
		new VueCategorie().start(new Stage());
		this.quitter();
	}

	public void quitter() {
		Stage stage=(Stage) menuBar.getScene().getWindow();
		stage.close();
	}
	
	
	
	
	
}
