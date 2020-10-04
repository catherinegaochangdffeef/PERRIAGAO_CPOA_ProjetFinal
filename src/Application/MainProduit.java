package Application;

import java.util.Scanner;
import Metier.CMProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainProduit {
	public static void main(String[] args) {
		
	Scanner scanner=new Scanner(System.in);
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);

	System.out.println("Produit");
	System.out.println("Choisir une méthode");
	System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
	int p=scanner.nextInt();
	if(p==1) {
		System.out.println("Ajouter");
		System.out.println ("nom=");
		String nom=scanner.next();
		System.out.println ("description=");
		String description=scanner.next();
		System.out.println ("tarif=");
		float tarif=scanner.nextFloat();
		System.out.println ("visuel=");
		String visuel=scanner.next();
		System.out.println ("id catégorie=");
		int idc=scanner.nextInt();
		try {
			daos.getProduitDAO().create(new CMProduit(nom,description,tarif,visuel,idc));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
			}
	else if(p==2) {
		System.out.println("Modifier");
		System.out.println("id_produit=");
		int id=scanner.nextInt();
		System.out.println ("nom=");
		String nom=scanner.next();
		System.out.println ("description=");
		String description=scanner.next();
		System.out.println ("tarif=");
		float tarif=scanner.nextFloat();
		System.out.println ("visuel=");
		String visuel=scanner.next();
		System.out.println ("id catégorie=");
		int idc=scanner.nextInt();			
		try {
			daos.getProduitDAO().update(new CMProduit(id,nom,description,tarif,visuel,idc));
		} catch (Exception e) {				
			e.printStackTrace();}
	}
		else if(p==3) {
			System.out.println("Supprimer");
			System.out.println("id produit=");
			int id=scanner.nextInt();			
			try {
				daos.getProduitDAO().delete(new CMProduit(id));
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if(p==4) {
			System.out.println("Chercher");
			System.out.println("id produit=");
			int id=scanner.nextInt();	
			try {
				daos.getProduitDAO().getById(id);	;
			} catch (Exception e) {
				System.out.println("ce produit n'existe pas!");
				e.printStackTrace();
			}
		}
		else 
		{
			System.out.println("invalide");
		}
		
}}
