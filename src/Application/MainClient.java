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
		System.out.println("Choisir une m�thode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
		int p=scanner.nextInt();
		if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("nom=");
				String nom=scanner.next();
				System.out.println ("prenom=");
				String prenom=scanner.next();
				System.out.println ("identifiant=");
				String iden=scanner.next();
				System.out.println ("mode de passe =");
				String mode=scanner.next();
				System.out.println("adr_numero=");
				int adr=scanner.nextInt();
				System.out.println ("adr_voie=");
				String adr_voie=scanner.next();
				System.out.println ("adr_code_postal=");
				int adr_code_postal=scanner.nextInt();
				System.out.println ("adr_ville=");
				String adr_ville=scanner.next();
				System.out.println ("adr_pays=");
				String adr_pays=scanner.next();
				try {
					daos.getClientDAO().create(new CMClient(nom,prenom,iden,mode,adr,adr_voie,adr_code_postal,adr_ville,adr_pays));
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
			System.out.println ("identifiant=");
			String iden=scanner.next();
			System.out.println ("mode de passe =");
			String mode=scanner.next();
			System.out.println("adr_numero=");
			int adr=scanner.nextInt();
			System.out.println ("adr_voie=");
			String adr_voie=scanner.next();
			System.out.println ("adr_code_postal=");
			int adr_code_postal=scanner.nextInt();
			System.out.println ("adr_ville=");
			String adr_ville=scanner.next();
			System.out.println ("adr_pays=");
			String adr_pays=scanner.next();			
			try {
				daos.getClientDAO().update(new CMClient(id, nom, prenom, iden, mode, adr, adr_voie, adr_code_postal, adr_ville, adr_pays));
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
			System.out.println("Chercher");
			System.out.println("id_client=");
			int id=scanner.nextInt();	
			try {
				daos.getClientDAO().getById(id);
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
