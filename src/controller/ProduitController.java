package controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vue.VueAccueil;
import vue.VueCommande;
import dao.DAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import Metier.CMProduit;

import Metier.CMCategorie;
import Metier.CMClient;

public class ProduitController implements Initializable{

	@FXML
	private TextField textFieldNom;
	@FXML
	private TextField textFieldTarif;
	@FXML
	private TextArea textAreaDescription;
	@FXML
	private TextField textFieldVisuel;
	@FXML
	private Label labelAffichage;
	@FXML
	private Button buttonCreer;
	@FXML
	private Button buttonModifier;
	@FXML
	private Button buttonSupprimer;
	@FXML
	private Button buttonRetourMenu;
	@FXML
	private ChoiceBox<String> choiceBoxCategorie;
	@FXML
	private TableView<CMProduit> tableViewProduit;
	@FXML 
	private TableColumn<CMProduit,Integer> tableColumnIdProduit;
	@FXML 
	private TableColumn<CMProduit,String> tableColumnNom;
	@FXML 
	private TableColumn<CMProduit,Float> tableColumnTarif;
	@FXML 
	private TableColumn<CMProduit,String> tableColumnDescription;
	@FXML 
	private TableColumn<CMProduit,String> tableColumnVisuel;
	@FXML 
	private TableColumn<CMProduit,Integer> tableColumnIdCategorie;
	
	private ObservableList<CMProduit> teamMembers;
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			for(int i=0;i<daos.getCategorieDAO().findAll().size();i++) {
			choiceBoxCategorie.getItems().add(daos.getCategorieDAO().findAll().get(i).getTitre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			teamMembers=FXCollections.observableArrayList(daos.getProduitDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FilteredList<CMProduit> filteredData= new FilteredList<>(teamMembers,p->true);
		
		SortedList<CMProduit> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableViewProduit.comparatorProperty());
		this.tableViewProduit.setItems(sortedData);
		
		tableColumnIdProduit.setCellValueFactory(new PropertyValueFactory<CMProduit,Integer>("idProduit"));
		tableColumnNom.setCellValueFactory(new PropertyValueFactory<CMProduit,String>("nom"));
		tableColumnTarif.setCellValueFactory(new PropertyValueFactory<CMProduit,Float>("tarif"));
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<CMProduit,String>("description"));
		tableColumnVisuel.setCellValueFactory(new PropertyValueFactory<CMProduit,String>("visuel"));
		tableColumnIdCategorie.setCellValueFactory(new PropertyValueFactory<CMProduit,Integer>("idCategorie"));
		this.tableViewProduit.getSortOrder().add(tableColumnIdProduit);
		
		buttonCreer.setDisable(true);
		buttonModifier.setDisable(true);
		buttonSupprimer.setDisable(true);
		
	}
	
	public void versAccueil() {
		new VueAccueil().start(new Stage());
		this.quitter();

	}
	public void quitter() {
	Stage stage = (Stage) buttonRetourMenu.getScene().getWindow();
	stage.close();
	}
	public void creerProduit() {
		
		
		if(!error()) {
		labelAffichage.setText(textFieldNom.getText() +"("+choiceBoxCategorie.getValue()+") , " + textFieldTarif.getText() + " euros");
	}
	}

	
	public   boolean error() {
		String erreur="";
		if(textFieldNom.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le Nom \n";

		}
		if(textFieldTarif.getText().trim().isEmpty()) {
			erreur+=" Veuillez saisir le Tarif \n";
		}
		if(choiceBoxCategorie.getValue()==null){
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
