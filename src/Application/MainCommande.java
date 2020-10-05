package Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

import Metier.CMCommande;
import Metier.CMProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainCommande {
public static void main() {
		
		Scanner scanner=new Scanner(System.in);
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
		
		System.out.println("Commande");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Afficher");
		int p=scanner.nextInt();
		if(p==1) {
			System.out.println("Ajouter");
			System.out.println ("date_commande=(yyyy/MM/dd)");
			String date=scanner.next();
			 DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			    LocalDate dateDebut = LocalDate.parse(date, formatage);
			System.out.println ("id_client");
			int idcl=scanner.nextInt();
			
				CMCommande co;
				try {
					co = new CMCommande(dateDebut,daos.getClientDAO().getById(idcl), new HashMap<CMProduit, Integer>());
			daos.getCommandeDAO().create(co);
			MainLignedecommande.main(co);
			} catch (Exception e) {
				System.out.println("ce commande n'exist pas!");
				e.printStackTrace();
			}
			
				}
		else if (p==2) {
			System.out.println("Modifier");
			System.out.println("id_commande=");
			int id=scanner.nextInt();
			System.out.println ("date_commande=(yyyy/MM/dd)");
			String date=scanner.next();
			 DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			    LocalDate dateDebut = LocalDate.parse(date, formatage);
			System.out.println ("id_client");
			int idcl=scanner.nextInt();		
			try {
				daos.getCommandeDAO().update(new CMCommande(id,dateDebut,idcl));
			} catch (Exception e) {				
				e.printStackTrace();
			}
			main();
		}
		else if (p==3) {
			System.out.println("Supprimer");
			System.out.println("id_commande=");
			int id=scanner.nextInt();			
			try {
				daos.getCommandeDAO().delete(new CMCommande(id));
			} catch (Exception e) {				
				e.printStackTrace();
			}
			main();
			
	}
		else if(p==4){
			try {
				for(int i=0;i<daos.getCommandeDAO().findAll().size();i++){
					System.out.println("id: "+daos.getCommandeDAO().findAll().get(i).getId()+" ||| date: "+daos.getCommandeDAO().findAll().get(i).getDateCommande().toString()+" ||| client: "+daos.getCommandeDAO().findAll().get(i).getIdClient());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main();
			}
		String[] args = null;
		Main.main(args);
}}
