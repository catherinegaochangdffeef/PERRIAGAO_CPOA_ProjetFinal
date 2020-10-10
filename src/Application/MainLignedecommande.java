package Application;

import java.util.Scanner;

import Metier.CMCategorie;
import Metier.CMCommande;
import Metier.CMLignedeCommande;
import Metier.CMProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainLignedecommande {
public static void main() {
		
	Scanner scanner=new Scanner(System.in);
	System.out.println("Voulez vous travailler sur MySQL ou sur ListMemoire? (1)ListMemoire , (2) MySQL :");
	int option=scanner.nextInt();
	
	if(option==1) {		
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.ListMemoire);
		/*
		System.out.println("Ligne de Commande");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Afficher");
		int p=scanner.nextInt();
		if(p==1) {
			System.out.println("Ajouter");
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			System.out.println ("quantite=");
			int quan=scanner.nextInt();
			try {
		//		commande.addProducts(daos.getProduitDAO().getById(idp), quan);
			} catch (Exception e) {
				e.printStackTrace();
			}
			main();
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
			main();
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
			main();
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
			main();
		}
		else {
			Main.main(null);
			}
	
		
		*/	
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------		
		else if(option==2) {
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
		
		System.out.println("Ligne de Commande");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Afficher");
		int p=scanner.nextInt();
		if(p==1) {
			System.out.println("Ajouter");
			System.out.println ("id_commande=");
			int idcom=scanner.nextInt();
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			System.out.println ("quantite=");
			int quan=scanner.nextInt();
			try {
				CMCommande idco=daos.getCommandeDAO().getById(idcom);
				CMProduit idpr=daos.getProduitDAO().getById(idp);
				daos.getLignedeCommandeDAO().create(new CMLignedeCommande(idco,idpr,quan));
			} catch (Exception e) {
				e.printStackTrace();
			}
			main();
			}
		else if(p==2) {
			System.out.println("Modifier");
			System.out.println ("id_commande=");
			int idcom=scanner.nextInt();
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			System.out.println ("quantite=");
			int quan=scanner.nextInt();
			try {
				CMCommande idco=daos.getCommandeDAO().getById(idcom);
				CMProduit idpr=daos.getProduitDAO().getById(idp);
				daos.getLignedeCommandeDAO().update(new CMLignedeCommande(idco,idpr,quan));
			} catch (Exception e) {
				e.printStackTrace();
			}
			main();
				}
		else if(p==3) {
			System.out.println("Supprimer");
			System.out.println ("id_commande=");
			int idcom=scanner.nextInt();
			System.out.println ("id_produit=");
			int idp=scanner.nextInt();
			try {
				//CMCommande idco=daos.getCommandeDAO().getById(idcom);
				//CMProduit idpr=daos.getProduitDAO().getById(idp);
				daos.getLignedeCommandeDAO().delete(new CMLignedeCommande(idcom,idp));
			} catch (Exception e) {
				e.printStackTrace();
			}
			main();
		}
		
		
		else if(p==4) {
			try {
				for(int i=0;i<daos.getLignedeCommandeDAO().findAll().size();i++){
					System.out.println("id_commande: "+daos.getLignedeCommandeDAO().findAll().get(i).getIdCommande()+" ||| id_produit: "+daos.getLignedeCommandeDAO().findAll().get(i).getIdProduit()
							+"||| quantite:"+daos.getLignedeCommandeDAO().findAll().get(i).getQuantite()+"|||tarif:"+daos.getLignedeCommandeDAO().findAll().get(i).getTarifUnitaire());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			main();
		}
		else {
			Main.main(null);
			}
	}
		
	
	

}}
