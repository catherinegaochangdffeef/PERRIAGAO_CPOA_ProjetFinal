package SQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import dao.CommandeDAO;
import Metier.CMCommande;
import Metier.CMProduit;
import Metier.CMClient;


public class MySQLCommandeDAO implements CommandeDAO{

public CMCommande getById(int id_commande) throws SQLException {
		
		CMCommande commande = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Commande where id_commande=? ");
		req.setInt(1, id_commande);

		ResultSet res = req.executeQuery();
		CMClient cli=null;
		int idclient=0;
		while (res.next()) {
			
			commande= new CMCommande(id_commande,res.getDate(2), cli);
			Date d=commande.getDateCommande();
			commande.setDate_commande1(d);;
			  idclient=res.getInt("id_client");
		}
		PreparedStatement req1=cnx.prepareStatement("select * from Client where id_client="+idclient);
		ResultSet res1 = req1.executeQuery();
		while (res1.next()) {
			cli= new CMClient(idclient,res1.getString("nom"),res1.getString("prenom"));
			
		}
		commande.setIdClient(cli);
		cnx.close();
		req.close();
		res.close();
		
		return commande;
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean create(CMCommande c) throws  SQLException{
		//
		Connection cnx = Connexion.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("INSERT INTO Commande (date_commande,id_client) values (?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setDate(1, c.getDateCommande());
				req.setInt(2, c.getIdClient2());
			
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					c.setIdCommande(clef);	
				}
				/*
				if(!c.getProducts().isEmpty()) {
					Set listkeys=c.getProducts().keySet();
					Iterator iterateur=listkeys.iterator();
					while(iterateur.hasNext()) {
						Object key=iterateur.next();
						PreparedStatement req1 = cnx.prepareStatement("INSERT INTO Ligne_commande (id_commande,id_produit,quantite,tarif_unitaire) values (?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
						req1.setInt(1, c.getId());
						req1.setInt(2, ((CMProduit)key).getIdProduit());
						req1.setInt(3, c.getProducts().get(key));
						req1.setDouble(4,  ((CMProduit)key).getTarif());
						req.executeUpdate();
					}
					
				}
				*/
				
				cnx.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMCommande c) throws SQLException {
		 int nbLignes =0;
		Connection cnx = Connexion.creeConnexion();
		
		PreparedStatement req = cnx.prepareStatement("update Commande set date_commande=? ,"
				+ "id_client=? where id_commande=?", java.sql.Statement.RETURN_GENERATED_KEYS);
		req.setInt(3, c.getIdCommande());
		req.setDate(1,c.getDateCommande());
		req.setInt(2,c.getIdClient2());
	
	    nbLignes = req.executeUpdate();
	    ResultSet res = req.getGeneratedKeys();
	    int clef;
		if(res.next()) {
			clef = res.getInt(1);
			c.setIdCommande(clef);	
		}
		

		cnx.close();
		req.close();
		res.close();
	return nbLignes==1;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMCommande c) {
		int nbLignes=0;
    	try {
    	Connexion.getInstance();
		Connection laConnexion = Connexion.creeConnexion();
	PreparedStatement requete = laConnexion.prepareStatement("delete  from Commande where id_commande=?");
	requete.setInt(1,c.getIdCommande());
	nbLignes = requete.executeUpdate();
	
    	} catch(SQLException sqle) {
    		System.out.println("Pb delete commande"+sqle.getMessage());
    	}

	return nbLignes==1;
  
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLCommandeDAO instance;
	private MySQLCommandeDAO() {}

	public static MySQLCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	


	public ArrayList<CMCommande> findAll() throws Exception {
	ArrayList<CMCommande> c = new ArrayList<CMCommande>();
		
		
		Connection MaConnection = Connexion.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Commande;");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			//CMClient cl=new CMClient(res.getInt("id_client"),res.getString("nom"),res.getString("prenom"));
			c.add(new CMCommande(res.getInt("id_commande"), res.getDate("date_commande"), res.getInt("id_client")));
			
		}
		

		req.close();
		res.close();
		return c;
	}

}
