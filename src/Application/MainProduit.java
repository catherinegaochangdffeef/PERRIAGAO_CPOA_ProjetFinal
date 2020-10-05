package Application;

import java.util.Scanner;
import Metier.CMProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainProduit {
	public static void main() {
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Voulez vous travailler sur MySQL ou sur ListMemoire? (1)ListMemoire , (2) MySQL :");
		int option=scanner.nextInt();
		
		if(option==1) {		
			DAOFactory daos =DAOFactory.getDAOFactory(Persistance.ListMemoire);
		

		System.out.println("Produit");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Afficher");
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
		main();
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
			main();
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
				main();
			}
			else if(p==4) {
			
					try {
						for(int i=0;i<daos.getProduitDAO().findAll().size();i++){
						System.out.println("nom: "+daos.getProduitDAO().findAll().get(i).getNom()
								+" ||| description: "+daos.getProduitDAO().findAll().get(i).getDescription()
								+" ||| tarif: "+daos.getProduitDAO().findAll().get(i).getTarif()
								+" ||| visuel: "+daos.getProduitDAO().findAll().get(i).getVisuel()
								+" ||| categorie: "+daos.getProduitDAO().findAll().get(i).getIdCategorie());
					}
						} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				main();
			}
			else {
				Main.main(null);
				}
		}
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------		
		else if(option==2) {
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);

	System.out.println("Produit");
	System.out.println("Choisir une méthode");
	System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Afficher");
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
	main();
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
		main();
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
			main();
		}
		else if(p==4) {
		
				try {
					for(int i=0;i<daos.getProduitDAO().findAll().size();i++){
					System.out.println("nom: "+daos.getProduitDAO().findAll().get(i).getNom()
							+" ||| description: "+daos.getProduitDAO().findAll().get(i).getDescription()
							+" ||| tarif: "+daos.getProduitDAO().findAll().get(i).getTarif()
							+" ||| visuel: "+daos.getProduitDAO().findAll().get(i).getVisuel()
							+" ||| categorie: "+daos.getProduitDAO().findAll().get(i).getIdCategorie());
				}
					} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			main();
		}
		else {
			Main.main(null);
			}
		
}}}
