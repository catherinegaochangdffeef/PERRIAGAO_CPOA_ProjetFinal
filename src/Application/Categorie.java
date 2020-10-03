package application;
import java.sql.*;
import java.util.ArrayList;

import sql.Connexion;

public class Categorie {
	
 
	public static void ajouter(int idCategorie,String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="INSERT INTO Categorie VALUES("+idCategorie+",'"+titre+"','"+visuel+"')"; 
			requete.executeUpdate(query);
			System.out.println("Ligne de catéorie ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void modifierC(int idCategorie,String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Categorie SET titre='"+titre+"',visuel='"+visuel+"' WHERE id_categorie="+idCategorie;
			requete.executeUpdate(query);
			System.out.println("Ligne de catégorie modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void supprimer(int idCategorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Categorie WHERE id_categorie="+idCategorie;
			requete.executeUpdate(query);
			System.out.println("Ligne de catégorie supprimée");
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void lister() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Categorie ";
			ArrayList<String> listeC = new ArrayList<>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int idCategorie = rs.getInt(1);
				String titre = rs.getString(2);
				String visuel = rs.getString(3);
				listeC.add(idCategorie+"");
				listeC.add(titre);
				listeC.add(visuel);
				
				System.out.println("id_categorie : " + idCategorie  + "  titre:"+titre +" visuel:"+visuel);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
/*	 deuxiÃ¨me mÃ©thode 
    int id_categorie ;
	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	String titre ;
	String visuel;

	public static void ListeCNext() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Categorie ";
			ArrayList<Categorie> listeC = new ArrayList<Categorie>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				
				int id_categorie = rs.getInt(1);
				String titre = rs.getString(2);
				String visuel = rs.getString(3);
				
				Categorie ca = new Categorie();
				ca.setId_categorie(id_categorie);
				ca.setTitre(titre);
				ca.setVisuel(visuel);
				
				listeC.add(ca);
				
				System.out.println("id_categorie : " + id_categorie  + "  titre:"+titre +" visuel:"+visuel);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
	*/
}
