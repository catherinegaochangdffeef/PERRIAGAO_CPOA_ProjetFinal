package SQL;


import java.sql.*;
import java.util.ArrayList;

import dao.ProduitDAO;
import Metier.CMCategorie;
import Metier.CMProduit;



public class MySQLProduitDAO implements ProduitDAO{

	
public CMProduit getById(int id_produit) throws SQLException {
		
		CMProduit produit = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from ProduitController where id_produit = ?");
		req.setInt(1, id_produit);
		
		
		ResultSet res = req.executeQuery();
		CMCategorie cat=null;
		int idCat=0;
		while (res.next()) {
			produit= new CMProduit(id_produit, res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), cat);
		idCat=res.getInt("id_categorie");
		}
		PreparedStatement req1 =cnx.prepareStatement("select id_categorie, titre, visuel from Categorie where id_categorie="+idCat);
		ResultSet res1 = req1.executeQuery();
		while(res1.next()) {
			cat=new CMCategorie(idCat,res1.getString("titre"),res1.getString("visuel"));
		}
		produit.setCMCategorie(cat);
		cnx.close();
		req.close();
		res.close();
		
		return produit;
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	

//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean create(CMProduit p) throws  SQLException{
		//
		Connection cnx = Connexion.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("INSERT INTO ProduitController (nom,description,tarif,visuel,id_categorie) values (?,?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setString(1, p.getNom());
				req.setString(2, p.getDescription());
				req.setFloat(3, p.getTarif());
				req.setString(4,p.getVisuel());
				req.setInt(5, p.getCMCategorie().getId());
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					p.setIdProduit(clef);
						
				}
				
				cnx.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMProduit p) throws SQLException {
		
		Connection cnx = Connexion.creeConnexion();
		
		PreparedStatement req = cnx.prepareStatement("update ProduitController set nom=?, description=?, tarif=?, visuel = ?, id_categorie=?  where id_produit=?");
		req.setInt(6, p.getIdProduit());
		req.setString(1,p.getNom());
		req.setString(2, p.getDescription());
		req.setFloat(3, p.getTarif());
		req.setString(4,p.getVisuel());
		req.setInt(5, p.getCMCategorie().getId());
	
		int nbLignes = req.executeUpdate();
	
		

		cnx.close();
		req.close();
		
	return nbLignes==1;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMProduit p) throws SQLException{
		
    	try {
    	Connection cnx = Connexion.creeConnexion();
	PreparedStatement req = cnx.prepareStatement("delete from ProduitController where id_produit=?");
	req.setInt(1,p.getIdProduit());
	
	
	
	int nbLignes = req.executeUpdate();
	

	cnx.close();
	req.close();
	
	return nbLignes==1;
    	}catch(Exception e) {
    	    return false;
    	}
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLProduitDAO instance;

	public static MySQLProduitDAO getInstance() {
		if (instance==null) {
			instance = new MySQLProduitDAO();
		}
		return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	

	@Override
	public ArrayList<CMProduit> findAll() throws Exception {
ArrayList<CMProduit> produ = new ArrayList<CMProduit>();
		
		
		Connection MaConnection = Connexion.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from ProduitController,Categorie where ProduitController.id_categorie=Categorie.id_categorie");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			CMCategorie cat=new CMCategorie(res.getInt("id_categorie"),res.getString("titre"),res.getString("visuel"));
			produ.add(new CMProduit(res.getInt("id_produit"), res.getString("nom"), res.getString("description"),res.getFloat("tarif"),res.getString("visuel"),cat));
			
		}
		

		req.close();
		res.close();
		return produ;
	}
	

}

