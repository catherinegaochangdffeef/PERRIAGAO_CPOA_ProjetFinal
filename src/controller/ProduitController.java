package controller;
import java.net.URL;
import java.util.ArrayList;
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
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import Metier.CMCommande;
import Metier.CMProduit;




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
	private TextField txtfieldRechercher;

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
	//DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	private ArrayList<CMProduit> listeProduit;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			for(int i=0;i<DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().findAll().size();i++) {
			choiceBoxCategorie.getItems().add(DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().findAll().get(i).getTitre());
			}
			
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
		
		
		if (txtfieldRechercher.getText().trim().isEmpty()) {	
			try {
				teamMembers=FXCollections.observableArrayList(DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().findAll());
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.tableViewProduit.setItems(teamMembers);
			}
			else {
				try {
					for (CMProduit lc: DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().findAll()) {
						if (String.valueOf(lc.getIdProduit()).toLowerCase().contains(txtfieldRechercher.getText())) {
							listeProduit.add(lc);
							teamMembers=FXCollections.observableList(listeProduit);
							this.tableViewProduit.setItems(teamMembers);
						}}
				} catch (Exception e) {}
			}
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
		this.tableViewProduit.getSelectionModel().clearSelection();
	}
	
	
	public void creerProduit() {
		if(!error()) {
		labelAffichage.setText(textFieldNom.getText() +"("+choiceBoxCategorie.getValue()+") , " + textFieldTarif.getText() + " euros");
		int Cate;
		try {
			Cate = DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().getByTitre(choiceBoxCategorie.getValue()).getId();
			CMProduit pro=new CMProduit(1,textFieldNom.getText(),textAreaDescription.getText(),Float.parseFloat(textFieldTarif.getText()),textFieldVisuel.getText(),Cate);
			DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().create(pro);
			
			textFieldNom.setText("");
			textFieldTarif.setText("");
			textAreaDescription.setText("");
			textFieldVisuel.setText("");
			choiceBoxCategorie.setValue(null);
			this.teamMembers.add(pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public void supprimerProduit() {
		CMProduit cpro=this.tableViewProduit.getSelectionModel().getSelectedItem();
		try {
			teamMembers.remove(cpro);
			DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().delete(cpro);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void modifierProduit() {
		if(!error()) {
			labelAffichage.setText(textFieldNom.getText() +"("+choiceBoxCategorie.getValue()+") , " + textFieldTarif.getText() + " euros");
			try {
			int idpro=this.tableViewProduit.getSelectionModel().getSelectedItem().getIdProduit();
			int index=this.tableViewProduit.getSelectionModel().getFocusedIndex();
			int Cate = DAOFactory.getDAOFactory(AccueilController.Peri).getCategorieDAO().getByTitre(choiceBoxCategorie.getValue()).getId();
			CMProduit cmpm=new CMProduit(idpro,textFieldNom.getText(),textAreaDescription.getText(),Float.parseFloat(textFieldTarif.getText()),textFieldVisuel.getText(),Cate);
			DAOFactory.getDAOFactory(AccueilController.Peri).getProduitDAO().update(cmpm);
			textFieldNom.setText("");
			textFieldTarif.setText("");
			textAreaDescription.setText("");
			textFieldVisuel.setText("");
			choiceBoxCategorie.setValue(null);
			this.teamMembers.set(index, cmpm);
			}catch(Exception e) {
				e.printStackTrace();
			}
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
