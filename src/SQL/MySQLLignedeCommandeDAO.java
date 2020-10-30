package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.LignedeCommandeDAO;
import dao.DAOFactory.Persistance;
import Metier.CMCategorie;
import Metier.CMCommande;
import Metier.CMLignedeCommande;
import Metier.CMProduit;

public class MySQLLignedeCommandeDAO implements LignedeCommandeDAO{

public CMLignedeCommande getById(int id_commande) throws SQLException {
		
		CMLignedeCommande lignedecommande = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select Commande.id_commande,ProduitController.id_produit,quantite,ProduitController.tatif from Ligne_commande,Commande,ProduitController where id_commande = ? and Commande.id_commande=Ligne_commande.id_commande and Ligne_commande.id_produit=ProduitController.id_produit");
		req.setInt(1, id_commande);
	
		
		ResultSet res = req.executeQuery();
		double d=0;
		while (res.next()) {
		
			lignedecommande= new CMLignedeCommande(id_commande, res.getInt(2),res.getInt(3),d);
			d=lignedecommande.getCMProduit().getTarif();
			lignedecommande.setTarifUnitaire(d);
		}
		cnx.close();
		req.close();
		res.close();
		
		return lignedecommande;
		
	}
public CMLignedeCommande getById2(int id_commande,int id_produit) throws SQLException, IllegalArgumentException {
	CMCommande cm=null;
	CMLignedeCommande lignedecommande = null;
	CMProduit p= null;
	try {
	    cm = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(id_commande);
	    p = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(id_produit);
	} catch (Exception e) {
	    throw new IllegalArgumentException("Le client ou la revue n'existe pas");
	}
	
	Connection cnx = Connexion.creeConnexion();
	PreparedStatement req =cnx.prepareStatement("select* from Ligne_commande where id_commande=? and id_produit=?");
	req.setInt(1, id_commande);
	req.setInt(2, id_produit);
	
	ResultSet res = req.executeQuery();
	
	while (res.next()) {
	
		lignedecommande= new CMLignedeCommande(cm,p);
	}
	cnx.close();
	req.close();
	res.close();
	
	return lignedecommande;
	
}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	

//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean create(CMLignedeCommande ldc) throws  SQLException{
		//
		Connection cnx = Connexion.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("INSERT INTO Ligne_commande (id_commande,id_produit,quantite,tarif_unitaire) values (?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
			    req.setInt(1, ldc.getIdCommande());
			    req.setInt(2, ldc.getIdProduit());
				req.setInt(3, ldc.getQuantite());
				req.setDouble(4, ldc.getTarifUnitaire());
				
				int nbLignes = req.executeUpdate();
			
				
				cnx.close();
				req.close();
				
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMLignedeCommande ldc) throws SQLException {
	    
		Connection cnx = Connexion.creeConnexion();
		
		PreparedStatement req = cnx.prepareStatement("update Ligne_commande set quantite=?, tarif_unitaire = ? where id_commande=? and id_produit=?", java.sql.Statement.RETURN_GENERATED_KEYS);;
		req.setInt(3, ldc.getIdCommande());
		req.setInt(4,ldc.getIdProduit());
	
		req.setInt(1,ldc.getQuantite());
		req.setDouble(2, ldc.getTarifUnitaire());
		
	
		int nbLignes = req.executeUpdate();
	
		

		cnx.close();
		req.close();
		
	return nbLignes==1;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMLignedeCommande ldc) {
		int nbLignes=0;
    	try {
    	Connexion.getInstance();
		Connection laConnexion = Connexion.creeConnexion();
	PreparedStatement requete = laConnexion.prepareStatement("delete from Ligne_commande where id_commande=? and id_produit=?");
	
	requete.setInt(1,ldc.getIdCommande());
	requete.setInt(2, ldc.getIdProduit());
	nbLignes = requete.executeUpdate();
    	} catch(SQLException sqle) {
    		System.out.println("Pb delete ligne de commande"+sqle.getMessage());
    	}

	return nbLignes==1;
  
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLLignedeCommandeDAO instance;
	private MySQLLignedeCommandeDAO() {}

	public static MySQLLignedeCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MySQLLignedeCommandeDAO();
		}
		return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	


	public ArrayList<CMLignedeCommande> findAll() throws Exception {
	ArrayList<CMLignedeCommande> ldc = new ArrayList<CMLignedeCommande>();
		
		Connection MaConnection = Connexion.creeConnexion();
		//PreparedStatement req = MaConnection.prepareStatement("select * from LignedeCommande, Commande,ProduitController where Commande.id_commande=LigneCommande.id_commande and LigneCommande.id_produit=ProduitController.id_produit; ");
		PreparedStatement req = MaConnection.prepareStatement("select * from Ligne_commande ");
			
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
		//	CMCommande cmco= new CMCommande(res.getInt("id_commande"),res.getDate("date_commande"),);
			//CMProduit cmpr=new CMProduit();
			ldc.add(new CMLignedeCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4)));
			
		}
		

		req.close();
		res.close();
		return ldc;
	}



}
