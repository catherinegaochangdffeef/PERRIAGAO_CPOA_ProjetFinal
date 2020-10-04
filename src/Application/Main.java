package Application;

import java.util.Scanner;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import Metier.CMCommande;

 
public class Main {
	public static void main(String[] args) {

		Scanner scanner=new Scanner(System.in);
		System.out.println("Passer de MySQL à ListeMemoire?  1:yes 2:no");
		int option=scanner.nextInt();
		if(option==1) {
			DAOFactory daos =DAOFactory.getDAOFactory(Persistance.ListMemoire);
		}
		else if(option==2) {
			DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);	
		System.out.println("choisir une partie");
		System.out.println("1:Catégorie 2: Produit 3: Client 4: Commande 5: Ligne de commande");
		int partie = scanner.nextInt();
		
// categorie
		if (partie==1) {
			MainCategorie.main();
		}
		
		
// Produit
		else if(partie==2) {
			MainProduit.main();
		}
	
// Client
		else if( partie==3) {
			MainClient.main();
		}
		
		
//commande	
		else if(partie==4) {
			MainCommande.main();
	
		}
//ligne de commande
		else if (partie==5) {
			CMCommande commande = null;
			MainLignedecommande.main(commande);
		}
		
		else {
			main(args);
		}
		
}}}
		

	
	