package Application;

import SQL.Connexion;
import java.sql.*;
import java.util.ArrayList;


public class Commande {

	public static void Ajouter(int id_commande,  Date date_commande,int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query ="INSERT INTO Commande VALUES("+id_commande+","+date_commande+","+id_client+")" ;
			requete.executeUpdate(query);
			System.out.println("ligne de commande ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select:"+ sqle.getMessage());
		}
	}
	public static void Modifier(int id_commande, Date date_commande,int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Commande SET date_commande='"+date_commande+"',"
					+"id_client="+id_client+""
					+ " WHERE id_commande="+id_commande;
			requete.executeUpdate(query);
			System.out.println("Ligne de commande modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void Supprimer(int id_commande) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Commande WHERE id_commande="+id_commande;
			requete.executeUpdate(query);
			System.out.println("Ligne de commande supprimée");

		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}



	public static void Liste() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Commande ";
			ArrayList<String> liste = new ArrayList<>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int id_commande = rs.getInt(1);
			    Date date_commande = rs.getDate(2);
				int id_client = rs.getInt(3);
				liste.add(id_commande+"");
				liste.add(date_commande+"");
				liste.add(id_client+"");

				System.out.println("id_commande : " + id_commande  + "  date_commande:"+date_commande+" id_client:"+id_client);
			}

		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}





}