package Application;

import Metier.CMCategorie;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import java.util.Scanner;

public class MainCategorie {
	public static void main() {
		
	Scanner scanner=new Scanner(System.in);
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	
	System.out.println("Catégorie");
	System.out.println("Choisir une méthode");
	System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
	
	int p=scanner.nextInt();
	if(p==1) {
		System.out.println("Ajouter");
		System.out.println ("titre=");
		String titre=scanner.next();
		System.out.println ("visuel=");
		String visuel=scanner.next();
		try {
			daos.getCategorieDAO().create(new CMCategorie( titre, visuel));
		} catch (Exception e) {
			e.printStackTrace();
		}
		main();
			}
	else if (p==2) {
		System.out.println("Modifier");
		System.out.println("id_categorie=");
		int id=scanner.nextInt();
		System.out.println ("titre=");
		String titre=scanner.next();
		System.out.println ("visuel=");
		String visuel=scanner.next();			
		try {
			daos.getCategorieDAO().update(new CMCategorie(id,titre,visuel));
		} catch (Exception e) {				
			e.printStackTrace();
		}
		main();
	}
	else if (p==3) {
		System.out.println("Supprimer");
		System.out.println("id_categorie=");
		int id=scanner.nextInt();			
		try {
			daos.getCategorieDAO().delete(new CMCategorie(id));
		} catch (Exception e) {				
			e.printStackTrace();
		}
		main();
		
}
	else if(p==4){
		System.out.println("Chercher");
		System.out.println("id_categorie=");
		int id=scanner.nextInt();	
		try {
			daos.getCategorieDAO().getById(id);	;
		} catch (Exception e) {
			System.out.println("cette catégorie n'existe pas!");
			e.printStackTrace();
		}
		main();		
		}
		String[] args = null;
		Main.main(args);
}
}
