package Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Metier.CMCommande;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainCommande {
public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
		
		System.out.println("Commande");
		System.out.println("Choisir une méthode");
		System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
		int p=scanner.nextInt();
		if(p==1) {
			System.out.println("Ajouter");
			System.out.println ("date_commande=");
			String date=scanner.next();
			 DateTimeFormatter formatage = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			    LocalDate dateDebut = LocalDate.parse(date, formatage);
			System.out.println ("id_client");
			int idcl=scanner.nextInt();
			try {
				daos.getCommandeDAO().create(new CMCommande(dateDebut,idcl));
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
			
	}
		else if(p==4){
			System.out.println("Chercher");
			System.out.println("id_commande=");
			int id=scanner.nextInt();	
			try {
				daos.getCommandeDAO().getById(id);	;
			} catch (Exception e) {
				System.out.println("ce commande n'exist pas!");
				e.printStackTrace();
			}
			
			
			
			}
		else{
			System.out.println("invalide");
		}
}}
