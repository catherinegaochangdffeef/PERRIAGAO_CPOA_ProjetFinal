package Application;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;


import dao.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class Accueil implements Initializable  {

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
	
	
	
	
	 DAOFactory.Persistance Peri ;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Peri =DAOFactory.Persistance.MYSQL;
		
	}
	public void PasserASql() {
		Peri =DAOFactory.Persistance.MYSQL;
	}
	public void asserAListeMemoire() {
		Peri= DAOFactory.Persistance.ListMemoire;
	}
	
	
	// changer à l'interface de commande
	public void VerCommande() {
		
	}
	// changer à l'interface du produit
	public void VerProduit() {
		  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TAbonnement.fxml"));
		   
	}
	// changer à l'interface du client
	public void VerClient() {
		
	}
	// Changer à l'interdace du catégorie
	public void VerCategorie() {
		
	}
	
	
	
	
	
	
	
}
