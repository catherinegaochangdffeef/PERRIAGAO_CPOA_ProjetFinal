package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SQL.Connexion;

public class Client {
	
	public static void AjouterC(int id_client,String nom, String prenom, String identifiant,String mot_de_passe,String adr_numero,String adr_voie,String adr_code_postal,String adr_ville,String adr_pays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="INSERT INTO Client VALUES("+id_client+",'"+nom+"','"+prenom+"','"+identifiant+"','"+mot_de_passe+"','"+adr_numero+"','"+adr_voie+"','"+adr_code_postal+"','"+adr_ville+"','"+adr_pays+"')"; 
			requete.executeUpdate(query);
			System.out.println("Ligne de client ajout�e");
		}catch (SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void ModifierC(int id_client,String nom, String prenom, String identifiant,String mot_de_passe,String adr_numero,String adr_voie,String adr_code_postal,String adr_ville,String adr_pays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Client SET nom='"+nom+"',prenom='"+prenom+"',identifiant='"+identifiant+"',mot_de_passe='"+mot_de_passe+"',adr_numero='"+adr_numero+"',adr_voie='"+adr_voie+"',adr_code_postal='"+adr_code_postal+"',adr_ville='"+adr_ville+"',adr_pays='"+adr_pays+"' WHERE id_client="+id_client;
			requete.executeUpdate(query);
			System.out.println("Ligne de client modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void SupprimerC(int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Client WHERE id_client="+id_client;
			requete.executeUpdate(query);
			System.out.println("Ligne de client supprimée");
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	
	
	public static void ListeC() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Client ";
	
			ArrayList<String> listeC = new ArrayList<>();
			
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int id_client = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				String identifiant = rs.getString(4);
				String mot_de_passe = rs.getString(5);
				String adr_numero = rs.getString(6);
				String adr_voie = rs.getString(7);
				String adr_code_postal = rs.getString(8);
				String adr_ville = rs.getString(9);
				String adr_pays = rs.getString(10);
				
				listeC.add(id_client+"");
				listeC.add(nom);
				listeC.add(prenom);
				listeC.add(identifiant);
				listeC.add(mot_de_passe);
				listeC.add(adr_numero);
				listeC.add(adr_voie);
				listeC.add(adr_code_postal);
				listeC.add(adr_ville);
				listeC.add(adr_pays);
				
				System.out.println("id_client : " + id_client  + "  nom:"+nom +" prenom:"+prenom+" identifiant:"+identifiant+" mot_de_passe:"+mot_de_passe
						+" adr_numero:"+adr_numero+" adr_voie:"+adr_voie+" adr_code_postal:"+adr_code_postal+" adr_ville:"+adr_ville+" adr_pays:"+adr_pays);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	
}
