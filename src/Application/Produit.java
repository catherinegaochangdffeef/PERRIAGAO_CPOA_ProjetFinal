package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SQL.Connexion;

public class Produit {
	
	public static void AjouterP(int id_produit,String nom, String description, float tarif, String visuel, int id_categorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="INSERT INTO Produit VALUES("+id_produit+",'"+nom+"','"+description+"',"+tarif+",'"+visuel+"',"+id_categorie+")"; 
			requete.executeUpdate(query);
			System.out.println("Ligne de produit ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void ModifierP(int id_produit,String nom, String description, float tarif, String visuel, int id_categorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Produit SET nom='"+nom+"',"
					+ "description='"+description+"' ,"
					+ "tarif="+tarif+" ,"
					+ "visuel='"+visuel+"' ,"
					+ "id_categorie="+id_categorie+" "
					+ "WHERE id_produit="+id_produit;
			requete.executeUpdate(query);
			System.out.println("Ligne de produit modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void SupprimerP(int id_produit) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Produit WHERE id_produit="+id_produit;
			requete.executeUpdate(query);
			System.out.println("Ligne de produit supprimée");
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	
	public static void ListeP() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Produit ";
			ArrayList<String> listeC = new ArrayList<>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int id_produit = rs.getInt(1);
				String nom = rs.getString(2);
				String description = rs.getString(3);
				float  tarif = rs.getFloat(4);
				String visuel = rs.getString(5);
				int  id_categorie = rs.getInt(6);
				listeC.add(id_produit+"");
				listeC.add(nom);
				listeC.add(description);
				listeC.add(tarif+"");
				listeC.add(visuel);
				listeC.add(id_categorie+"'");
				
				System.out.println("id_produit : " + id_produit  + "  nom:"+nom +" description:"+description+"tarif : " + tarif  + "  visuel:"+visuel +" id_categorie:"+id_categorie);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
}
