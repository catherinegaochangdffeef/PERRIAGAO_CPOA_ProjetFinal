package Application;
import SQL.Connexion;
import java.sql.*;
import java.util.ArrayList;

public class Categorie {
	
 
	public static void AjouterC(int id_categorie,String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="INSERT INTO Categorie VALUES("+id_categorie+",'"+titre+"','"+visuel+"')"; 
			requete.executeUpdate(query);
			System.out.println("Ligne de catégorie ajoutée");
		}catch (SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void ModifierC(int id_categorie,String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="UPDATE Categorie SET titre='"+titre+"',visuel='"+visuel+"' WHERE id_categorie="+id_categorie;
			requete.executeUpdate(query);
			System.out.println("Ligne de catégorie modifiée");
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void SupprimerC(int id_categorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="DELETE FROM Categorie WHERE id_categorie="+id_categorie;
			requete.executeUpdate(query);
			System.out.println("Ligne de catégorie supprimée");
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}

	public static void ListeC() {		
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			String query="SELECT * FROM Categorie ";
			ArrayList<String> listeC = new ArrayList<>();
			ResultSet rs = requete.executeQuery(query);
			while(rs.next())
			{
				int id_categorie = rs.getInt(1);
				String titre = rs.getString(2);
				String visuel = rs.getString(3);
				listeC.add(id_categorie+"");
				listeC.add(titre);
				listeC.add(visuel);
				
				System.out.println("id_categorie : " + id_categorie  + "  titre:"+titre +" visuel:"+visuel);
			}
			
		}catch(SQLException sqle) {
			System.out.println("Pb select :" + sqle.getMessage());
		}
	}
/*	 deuxième méthode 
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
