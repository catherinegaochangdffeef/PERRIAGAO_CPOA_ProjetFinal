package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CommandeDAO;
import metier.CMCommande;


public class MySQLCommandeDAO implements CommandeDAO{

public CMCommande getById(int id_commande) throws SQLException {
		
		CMCommande commande = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Commande where id_commande = ?");
		req.setInt(1, id_commande);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			commande= new CMCommande(id_commande, res.getDate(2), res.getInt(3));
			Date d=commande.getDate_commande();
			  System.out.println("id_commande:"+commande.getId());
				System.out.println("date_commande:"+d);
				System.out.println("id_client"+commande.getId_client());
		}
		
		
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
				req.setDate(1, c.getDate_commande());
				req.setInt(2, c.getId_client());
			
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					c.setId(clef);
						
				}
				
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
				+ "id_client=? where id_commande=?");
		req.setInt(3, c.getId());
		req.setDate(1,c.getDate_commande());
		req.setInt(2,c.getId_client());
	
	    nbLignes = req.executeUpdate();
	
		

		cnx.close();
		req.close();
		
	return nbLignes==1;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMCommande c) {
		int nbLignes=0;
    	try {
    	Connexion.getInstance();
		Connection laConnexion = Connexion.creeConnexion();
	PreparedStatement requete = laConnexion.prepareStatement("delete  from Commande where id_commande=?");
	PreparedStatement requete1 = laConnexion.prepareStatement("delete  from Ligne_commande where id_commande=?");
	requete.setInt(1,c.getId());
	requete1.setInt(1,c.getId());
	nbLignes = requete.executeUpdate();
	nbLignes = requete1.executeUpdate();
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
		PreparedStatement req = MaConnection.prepareStatement("select * from Commande ");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			c.add(new CMCommande(res.getInt(1), res.getDate(2), res.getInt(3)));
			
		}
		

		req.close();
		res.close();
		return c;
	}

}
