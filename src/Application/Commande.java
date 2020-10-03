package Application;

import java.sql.*;
import java.util.ArrayList;

import SQL.Connexion;


public class Commande {

	public static void ajouter(int idCommande,  Date dateCommande,int idClient) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query ="INSERT INTO Commande VALUES("+idCommande+","+dateCommande+","+idClient+")" ;
			requete.executeUpdate(query);
			System.out.println("ligne de commande ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select:"+ sqle.getMessage());
		}
	}
	public static void modifier(int idCommande,  Date dateCommande,int idClient) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Commande SET date_commande='"+dateCommande+"',"
					+"id_client="+idClient+""
					+ " WHERE id_commande="+idCommande;
			requete.executeUpdate(query);
			System.out.println("Ligne de commande modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void supprimer(int idCommande) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Commande WHERE id_commande="+idCommande;
			requete.executeUpdate(query);
			System.out.println("Ligne de commande supprimée");

		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}



	public static void lister() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Commande ";
			ArrayList<String> liste = new ArrayList<>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int idCommande = rs.getInt(1);
			    Date dateCommande = rs.getDate(2);
				int idClient = rs.getInt(3);
				liste.add(idCommande+"");
				liste.add(dateCommande+"");
				liste.add(idClient+"");

				System.out.println("idCommande : " + idCommande  + "  dateCommande:"+dateCommande+" idClient:"+idClient);
			}

		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}





}