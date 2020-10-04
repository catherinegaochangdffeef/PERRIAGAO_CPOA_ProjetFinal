package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClientDAO;
import Metier.CMClient;

public class MySQLClientDAO implements ClientDAO {




	public CMClient getById(int id_client) throws SQLException {
		
		CMClient client = null;
		
		Connection MaConnection = Connexion.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select id_client, nom, prenom from Client where id_client = ?");
		req.setInt(1, id_client);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			client= new CMClient(res.getInt(1), res.getString("nom"), res.getString("prenom"));	
			
		}
		
		MaConnection.close();
		req.close();
		res.close();
		
		return client;
		
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------	

	public boolean create(CMClient c) throws  SQLException{
		//
		Connection laConnection = Connexion.creeConnexion();
			PreparedStatement req = laConnection.prepareStatement("INSERT INTO Client (nom, prenom, identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,adr_pays) values (?,?,'','', '','', '','','')", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setString(1, c.getNom());
				req.setString(2, c.getPrenom());
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					c.setIdClient(clef);
						
				}
				
				laConnection.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean update(CMClient c) throws SQLException {
		
		Connection laConnection = Connexion.creeConnexion();
		
		PreparedStatement req = laConnection.prepareStatement("update Client set nom=? ,prenom=? where id_client=?");
		req.setInt(3, c.getIdClient());
		req.setString(1,c.getNom());
		req.setString(2,c.getPrenom());
		
		int nbLignes = req.executeUpdate();
	
		

		laConnection.close();
		req.close();
		
	return nbLignes==1;
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMClient c) throws SQLException {
		
	    	
	    	try {
	    	Connection laConnection = Connexion.creeConnexion();
		PreparedStatement req = laConnection.prepareStatement("delete from Client where id_client=?");
		req.setInt(1,c.getIdClient());
		
		
		
		int nbLignes = req.executeUpdate();
		

		laConnection.close();
		req.close();
		
		return nbLignes==1;
	    	}catch(Exception e) {
	    	    return false;
	    	}
}
		
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLClientDAO instance;
	
	private  MySQLClientDAO() {
		;
	}
	
	public static MySQLClientDAO getInstance() {
		if (instance==null) {
			instance = new MySQLClientDAO();
		}
	return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public ArrayList<CMClient> findAll() throws  SQLException{
		ArrayList<CMClient> cl = new ArrayList<CMClient>();
		
		
		Connection MaConnection = Connexion.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Client ");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			cl.add(new CMClient(res.getInt(1), res.getString(2), res.getString(3)));
			
		}
		

		req.close();
		res.close();
		return cl;
		}

	
}
