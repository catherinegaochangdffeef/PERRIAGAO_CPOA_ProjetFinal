package controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import Metier.CMProduit;
import Metier.CMCategorie;

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
	private TableColumn<CMProduit,CMCategorie> tableColumnIdCategorie;
	
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
		tableColumnIdProduit.setCellValueFactory(new PropertyValueFactory<CMProduit,Integer>("idProduit"));
		tableColumnNom.setCellValueFactory(new PropertyValueFactory<CMProduit,String>("nom"));
		tableColumnTarif.setCellValueFactory(new PropertyValueFactory<CMProduit,Float>("tarif"));
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<CMProduit,String>("description"));
		//tableColumnIdCategorie.setCellValueFactory(new PropertyValueFactory<CMProduit,CMCategorie>("idCategorie"));
		this.tableViewProduit.getColumns().setAll(tableColumnIdProduit,tableColumnNom,tableColumnDescription,tableColumnTarif,tableColumnVisuel,tableColumnIdCategorie);
		try {
			this.tableViewProduit.getItems().addAll(
					daos.getProduitDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
