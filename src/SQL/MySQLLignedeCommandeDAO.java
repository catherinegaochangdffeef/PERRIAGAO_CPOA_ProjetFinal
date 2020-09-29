package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import Metier.CMLignedeCommande;
import dao.LignedeCommandeDAO;

public class MySQLLignedeCommandeDAO implements LignedeCommandeDAO{

public CMLignedeCommande getById(int id_commande) throws SQLException {
		
		CMLignedeCommande lignedecommande = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Ligne_commande where id_commande = ?");
		req.setInt(1, id_commande);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			lignedecommande= new CMLignedeCommande(id_commande, res.getInt(2),res.getInt(3), res.getDouble(4));
			System.out.println("id_commande:"+lignedecommande.getId_commande());
			System.out.println("id_produit:"+lignedecommande.getId_produit());
			System.out.println("quantite:"+lignedecommande.getQuantite());
			System.out.println("tarif_unitaire"+lignedecommande.getTarif_unitaire());
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
			PreparedStatement req = cnx.prepareStatement("INSERT INTO Ligne_commande (id_commande,id_produit,quantite,tarif_unitaire) values (?,?,?,?)");
			    req.setInt(1, ldc.getId_commande());
			    req.setInt(2, ldc.getId_produit());
				req.setInt(3, ldc.getQuantite());
				req.setDouble(4, ldc.getTarif_unitaire());
				
				int nbLignes = req.executeUpdate();
			
				
				cnx.close();
				req.close();
				
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMLignedeCommande ldc) throws SQLException {
	    
		Connection cnx = Connexion.creeConnexion();
		
		PreparedStatement req = cnx.prepareStatement("update Ligne_commande set quantite=?, tarif_unitaire = ? where id_commande=? and id_produit=?");
		req.setInt(3, ldc.getId_commande());
		req.setInt(4, ldc.getId_produit());
	
		req.setInt(1,ldc.getQuantite());
		req.setDouble(2,ldc.getTarif_unitaire());
		
	
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
	PreparedStatement requete = laConnexion.prepareStatement("delete from Ligne_commande where id_commande=? id_produit=?");
	
	requete.setInt(1,ldc.getId_commande());
	requete.setInt(2, ldc.getId_produit());
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
		PreparedStatement req = MaConnection.prepareStatement("select * from LignedeCommande ");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			ldc.add(new CMLignedeCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4)));
			
		}
		

		req.close();
		res.close();
		return ldc;
	}



}
