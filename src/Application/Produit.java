package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sql.Connexion;

public class Produit {
	
	public static void ajouter(int idProduit,String nom, String description, float tarif, String visuel, int idCategorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="INSERT INTO Produit VALUES("+idProduit+",'"+nom+"','"+description+"',"+tarif+",'"+visuel+"',"+idCategorie+")"; 
			requete.executeUpdate(query);
			System.out.println("Ligne de produit ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void modifierP(int idProduit,String nom, String description, float tarif, String visuel, int idCategorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Produit SET nom='"+nom+"',"
					+ "description='"+description+"' ,"
					+ "tarif="+tarif+" ,"
					+ "visuel='"+visuel+"' ,"
					+ "id_categorie="+idCategorie+" "
					+ "WHERE id_produit="+idProduit;
			requete.executeUpdate(query);
			System.out.println("Ligne de produit modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void supprimerP(int idProduit) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Produit WHERE id_produit="+idProduit;
			requete.executeUpdate(query);
			System.out.println("Ligne de produit supprimÃ©e");
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	
	public static void lister() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Produit ";
			ArrayList<String> listeC = new ArrayList<>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int idProduit = rs.getInt(1);
				String nom = rs.getString(2);
				String description = rs.getString(3);
				float  tarif = rs.getFloat(4);
				String visuel = rs.getString(5);
				int  idCategorie = rs.getInt(6);
				listeC.add(idProduit+"");
				listeC.add(nom);
				listeC.add(description);
				listeC.add(tarif+"");
				listeC.add(visuel);
				listeC.add(idCategorie+"'");
				
				System.out.println("id_produit : " + idProduit  + "  nom:"+nom +" description:"+description+"tarif : " + tarif  + "  visuel:"+visuel +" id_categorie:"+idCategorie);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
}
