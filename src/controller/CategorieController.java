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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vue.VueAccueil;
import dao.DAOFactory;
import Metier.CMCategorie;
public class CategorieController implements Initializable{

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
	private Button buttonRetourMenu;
	@FXML
	private Label labelAffichage;
	@FXML
	private TableView<CMCategorie> tableViewCategorie;
	@FXML
	private TableColumn<CMCategorie,String> tableColumnTitre;
	@FXML
	private TableColumn<CMCategorie,String> tableColumnVisuel;
	@FXML
	private TableColumn<CMCategorie,Integer> tableColumnIdCategorie;
	private ObservableList<CMCategorie> teamMembers;
	
	
	



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			teamMembers=FXCollections.observableArrayList(DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
FilteredList<CMCategorie> filteredData= new FilteredList<>(teamMembers,p->true);
		
		SortedList<CMCategorie> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableViewCategorie.comparatorProperty());
		this.tableViewCategorie.setItems(sortedData);
		
		
		tableColumnTitre.setCellValueFactory(new PropertyValueFactory<CMCategorie,String>("titre"));
		tableColumnVisuel.setCellValueFactory(new PropertyValueFactory<CMCategorie,String>("visuel"));
		tableColumnIdCategorie.setCellValueFactory(new PropertyValueFactory<CMCategorie,Integer>("id"));
		this.tableViewCategorie.getSortOrder().add(tableColumnIdCategorie);
		
		buttonCreer.setDisable(true);
		buttonModifier.setDisable(true);
		buttonSupprimer.setDisable(true);
		
	}
	
	
	public void creerCategorie() {
		if(!error()) {
			labelAffichage.setText(textFieldTitre.getText()+","+textFieldVisuel.getText());
			try {
				
				CMCategorie cat=new CMCategorie(1,textFieldTitre.getText(),textFieldVisuel.getText());
				DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().create(cat);
				
				textFieldTitre.setText("");
				textFieldVisuel.setText("");
				this.teamMembers.add(cat);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void supprimerCategorie() {
		CMCategorie cate=this.tableViewCategorie.getSelectionModel().getSelectedItem();
		try {
			teamMembers.remove(cate);
			DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().delete(cate);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modifierCategorie() {
		if(!error()) {
			labelAffichage.setText(textFieldTitre.getText()+","+textFieldVisuel.getText());
			try {
				int idcate=this.tableViewCategorie.getSelectionModel().getSelectedItem().getId();
				int index=this.tableViewCategorie.getSelectionModel().getFocusedIndex();
				CMCategorie cmca=new CMCategorie(idcate,textFieldTitre.getText(),textFieldVisuel.getText());
				DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().update(cmca);
				textFieldTitre.setText("");
				textFieldVisuel.setText("");
				this.teamMembers.set(index, cmca);
			}catch(Exception e) {
				e.printStackTrace();
			}
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

	public void versAccueil() {
		new VueAccueil().start(new Stage());
		this.quitter();

	}
	public void quitter() {
		Stage stage = (Stage) buttonRetourMenu.getScene().getWindow();
		stage.close();
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
		this.tableViewCategorie.getSelectionModel().clearSelection();
	}
	
	
	
}
