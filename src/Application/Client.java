package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SQL.Connexion;

public class Client {
	
	public static void ajouter(int idClient,String nom, String prenom, String identifiant,String motDePasse,String adrNumero,String adrVoie,String adrCodePostal,String adrVille,String adrPays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="INSERT INTO Client VALUES("+idClient+",'"+nom+"','"+prenom+"','"+identifiant+"','"+motDePasse+"','"+adrNumero+"','"+adrVoie+"','"+adrCodePostal+"','"+adrVille+"','"+adrPays+"')"; 
			requete.executeUpdate(query);
			System.out.println("Ligne de client ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void modifier(int idClient,String nom, String prenom, String identifiant,String motDePasse,String adrNumero,String adrVoie,String adrCodePostal,String adrVille,String adrPays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Client SET nom='"+nom+"',prenom='"+prenom+"',identifiant='"+identifiant+"',mot_de_passe='"+motDePasse+"',adr_numero='"+adrNumero+"',adr_voie='"+adrVoie+"',adr_code_postal='"+adrCodePostal+"',adr_ville='"+adrVille+"',adr_pays='"+adrPays+"' WHERE id_client="+idClient;
			requete.executeUpdate(query);
			System.out.println("Ligne de client modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void supprimer(int idClient) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Client WHERE id_client="+idClient;
			requete.executeUpdate(query);
			System.out.println("Ligne de client supprimée");
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	
	
	public static void lister() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Client ";
	
			ArrayList<String> listeC = new ArrayList<>();
			
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int idClient = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				String identifiant = rs.getString(4);
				String motDePasse = rs.getString(5);
				String adrNumero = rs.getString(6);
				String adrVoie = rs.getString(7);
				String adrCodePostal = rs.getString(8);
				String adrVille = rs.getString(9);
				String adrPays = rs.getString(10);
				
				listeC.add(idClient+"");
				listeC.add(nom);
				listeC.add(prenom);
				listeC.add(identifiant);
				listeC.add(motDePasse);
				listeC.add(adrNumero);
				listeC.add(adrVoie);
				listeC.add(adrCodePostal);
				listeC.add(adrVille);
				listeC.add(adrPays);
				
				System.out.println("id_client : " + idClient  + "  nom:"+nom +" prenom:"+prenom+" identifiant:"+identifiant+" mot_de_passe:"+motDePasse
						+" adr_numero:"+adrNumero+" adr_voie:"+adrVoie+" adr_code_postal:"+adrCodePostal+" adr_ville:"+adrVille+" adr_pays:"+adrPays);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	
}
