package Application;

import java.util.Scanner;

import Metier.CMClient;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainClient {
	public static void main() {
		
		Scanner scanner=new Scanner(System.in);
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
		
		System.out.println("Client");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Afficher");
		int p=scanner.nextInt();
		if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("nom=");
				String nom=scanner.next();
				System.out.println ("prenom=");
				String prenom=scanner.next();
				
				try {
					daos.getClientDAO().create(new CMClient(nom,prenom));
				} catch (Exception e) {
					e.printStackTrace();
				}
				main();
				}
		else if(p==2) {
			System.out.println("Modifier");
			System.out.println("id_client=");
			int id=scanner.nextInt();
			System.out.println ("nom=");
			String nom=scanner.next();
			System.out.println ("prenom=");
			String prenom=scanner.next();
					
			try {
				daos.getClientDAO().update(new CMClient(id, nom, prenom));
			} catch (Exception e) {				
				e.printStackTrace();
			}
			main();
				}
		else if (p==3) {
			System.out.println("Supprimer");
			System.out.println("id_client=");
			int id=scanner.nextInt();			
			try {
				daos.getClientDAO().delete(new CMClient(id));
			} catch (Exception e) {				
				e.printStackTrace();
			}
			main();
		}
		else if(p==4) {
			System.out.println("Affhicer");
			try {
				for(int i=0;i<daos.getClientDAO().findAll().size();i++) {
					System.out.println("nom:"+daos.getClientDAO().findAll().get(i).getNom()+"    prenom:"
							+daos.getClientDAO().findAll().get(i).getPrenom());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main();
		}
		String[] args = null;
		Main.main(args);
	}
	
}
