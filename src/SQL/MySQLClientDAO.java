package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Metier.CMClient;
import dao.ClientDAO;

public class MySQLClientDAO implements ClientDAO {




	public CMClient getById(int id_client) throws SQLException {
		
		CMClient client = null;
		
		Connection MaConnection = Connexion.creeConnexion();
		PreparedStatement req = MaConnection.prepareStatement("select * from Client where id_client = ?");
		req.setInt(1, id_client);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			client= new CMClient(res.getInt(1), res.getString(2), res.getString(3),res.getString(4), res.getString(5),res.getInt(6),res.getString(7),
					res.getInt(8),res.getString(9),res.getString(10));
			System.out.println("id:"+client.getId_client());
			System.out.println("nom:"+client.getNom());
			System.out.println("Prénom"+client.getPrenom());
			System.out.println("Identifiant:"+client.getIdentifiant());
			System.out.println("Mot de passe:"+client.getMot_de_passe());
			System.out.println("Adresse numéro:"+client.getAdr_numero());
			System.out.println("Adresse voie:"+client.getAdr_voie());
			System.out.println("Adresse postal:"+client.getAdr_code_postal());
			System.out.println("Adreese ville:"+client.getAdr_ville());
			System.out.println("Adresse pays:"+client.getAdr_ville());
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
			PreparedStatement req = laConnection.prepareStatement("INSERT INTO Client (nom, prenom, identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal,adr_ville,adr_pays) values (?,?, ?,?, ?,?, ?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setString(1, c.getNom());
				req.setString(2, c.getPrenom());
				req.setString(3, c.getIdentifiant());
				req.setString(4, c.getMot_de_passe());
				req.setInt(5, c.getAdr_numero());
				req.setString(6, c.getAdr_voie());
				req.setInt(7, c.getAdr_code_postal());
				req.setString(8, c.getAdr_ville());
				req.setString(9, c.getAdr_pays());
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					c.setId_client(clef);
						
				}
				
				laConnection.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean update(CMClient c) throws SQLException {
		
		Connection laConnection = Connexion.creeConnexion();
		
		PreparedStatement req = laConnection.prepareStatement("update Client set nom=? ,prenom=?, identifiant = ?, mot_de_passe=?,adr_numero=?,adr_voie=?, adr_code_postal=?, adr_ville=?, adr_pays=? where id_client=?");
		req.setInt(10, c.getId_client());
		req.setString(1,c.getNom());
		req.setString(2,c.getPrenom());
		req.setString(3, c.getIdentifiant());
		req.setString(4, c.getMot_de_passe());
		req.setInt(5, c.getAdr_numero());
		req.setString(6, c.getAdr_voie());
		req.setInt(7, c.getAdr_code_postal());
		req.setString(8, c.getAdr_ville());
		req.setString(9, c.getAdr_pays());
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
		req.setInt(1,c.getId_client());
		
		
		
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
			cl.add(new CMClient(res.getInt(1), res.getString(2), res.getString(3),res.getString(4), res.getString(5),res.getInt(6),res.getString(7),
					res.getInt(8),res.getString(9),res.getString(10)));
			
		}
		

		req.close();
		res.close();
		return cl;}

	
}
