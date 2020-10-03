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
	

		public static void ajouter(int idCommande,int idPproduit, int quantite, double tarifUnitaire) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="INSERT INTO LignedeCommande VALUES("+idPproduit+","+idPproduit+","+quantite+","+tarifUnitaire+")"; 
				requete.executeUpdate(query);
				System.out.println("Ligne de 'ligne de commande'ajoutée");
			}catch (SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}	


		public static void modifier(int idCommande,int idPproduit, int quantite, double tarifUnitaire) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="UPDATE LignedeCommande SET id_produit='"+idPproduit+"',"
						+ "quantitie'"+quantite+"' ,"
						+ "tarif_unitaire="+tarifUnitaire+" "
						+ "WHERE id_commande="+idCommande;
				requete.executeUpdate(query);
				System.out.println("igne de 'ligne de commande'ajoutée");
			}catch(SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}

		public static void supprimer(int idCommande) {
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="DELETE FROM LignedeCommande WHERE id_commande="+idCommande;
				requete.executeUpdate(query);
				System.out.println("Ligne 'ligne de commande' supprimée");

			}catch(SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}

		public static void lister() {		
			try {
				Connection laConnexion = Connexion.creeConnexion();
				Statement requete = laConnexion.createStatement();
				String query="SELECT * FROM LignedeCommande ";
				ArrayList<String> listeC = new ArrayList<>();
				ResultSet rs = requete.executeQuery(query);
				while(rs.next())
				{
					int idCommande = rs.getInt(1);
					int idProduit = rs.getInt(2);
					int quantite  = rs.getInt(3);
					double  tarifUnitaire = rs.getDouble(4);

					listeC.add(idCommande+"");
					listeC.add(idProduit+"");
					listeC.add(quantite+"");
					listeC.add(tarifUnitaire+"");


					System.out.println("id_commande: " + idCommande + " id_produit:" + idProduit+ "quantite:" +quantite+ "tarif_unitaire : " + tarifUnitaire );
				}

			}catch(SQLException sqle) {
				System.out.println("Pb select :" + sqle.getMessage());
			}
		}


}