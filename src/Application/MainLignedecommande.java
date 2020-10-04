package Application;

import java.util.Scanner;

import Metier.CMCommande;
import Metier.CMLignedeCommande;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainLignedecommande {
public static void main(CMCommande commande) {
		
		Scanner scanner=new Scanner(System.in);
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
		
		System.out.println("Ligne de Commande");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
		int p=scanner.nextInt();
		if(p==1) {
			System.out.println("Ajouter");
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			System.out.println ("quantite=");
			int quan=scanner.nextInt();
			try {
				commande.addProducts(daos.getProduitDAO().getById(idp), quan);
			} catch (Exception e) {
				e.printStackTrace();
			}
			main(commande);
			}
		else if(p==2) {
			System.out.println("Modifier");
			System.out.println ("id_commande=");
			int idcom=scanner.nextInt();
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			System.out.println ("quantite=");
			int quan=scanner.nextInt();
			System.out.println ("tarif_unitaire =");
			double tarifu=scanner.nextDouble();
			try {
				daos.getLignedeCommandeDAO().update(new CMLignedeCommande(idcom,idp,quan,tarifu));
			} catch (Exception e) {
				e.printStackTrace();
			}
			main(commande);
				}
		else if(p==3) {
			System.out.println("Supprimer");
			System.out.println ("id_commande=");
			int idcom=scanner.nextInt();
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			try {
				daos.getLignedeCommandeDAO().delete(new CMLignedeCommande(idcom,idp));
			} catch (Exception e) {
				e.printStackTrace();
			}
			main(commande);
		}
		
		
		else if(p==4) {
			System.out.println("Chercher");
			System.out.println("id_commande=");
			int id=scanner.nextInt();	
			try {
				daos.getLignedeCommandeDAO().getById(id);
			} catch (Exception e) {
				System.out.println("cette ligne de commande n'exist pas!");
				e.printStackTrace();
			}
			main(commande);
		}
		String[] args = null;
		Main.main(args);
	}
		
	
	

}
