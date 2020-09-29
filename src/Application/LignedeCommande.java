package Application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import SQL.Connexion;

public class LignedeCommande {

	HashMap<String,Integer> LigneC= new HashMap<String,Integer>();
	

		public static void Ajouter(int id_commande,int id_produit, int quantite, double tarif_unitaire) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="INSERT INTO LignedeCommande VALUES("+id_produit+","+id_produit+","+quantite+","+tarif_unitaire+")"; 
				requete.executeUpdate(query);
				System.out.println("Ligne de 'ligne de commande'ajoutée");
			}catch (SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}	


		public static void Modifier( int id_commande,int id_produit, int quantite, double tarif_unitaire) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="UPDATE LignedeCommande SET id_produit='"+id_produit+"',"
						+ "quantitie'"+quantite+"' ,"
						+ "tarif_unitaire="+tarif_unitaire+" "
						+ "WHERE id_commande="+id_commande;
				requete.executeUpdate(query);
				System.out.println("igne de 'ligne de commande'ajoutée");
			}catch(SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}

		public static void Supprimer(int id_commande) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="DELETE FROM LignedeCommande WHERE id_commande="+id_commande;
				requete.executeUpdate(query);
				System.out.println("Ligne 'ligne de commande' supprimée");

			}catch(SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}

		public static void Liste() {		
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="SELECT * FROM LignedeCommande ";
				ArrayList<String> listeC = new ArrayList<>();
				ResultSet rs = requete.executeQuery(query);
				while(rs.next())
				{
					int id_commande = rs.getInt(1);
					int id_produit = rs.getInt(2);
					int quantite  = rs.getInt(3);
					double  tarif_unitaire = rs.getDouble(4);

					listeC.add(id_commande+"");
					listeC.add(id_produit+"");
					listeC.add(quantite+"");
					listeC.add(tarif_unitaire+"");


					System.out.println("id_commande: " + id_commande + " id_produit:" + id_produit+ "quantite:" +quantite+ "tarif_unitaire : " + tarif_unitaire );
				}

			}catch(SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}


}