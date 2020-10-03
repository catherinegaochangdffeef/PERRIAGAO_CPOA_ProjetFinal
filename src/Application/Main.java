package Application;

import java.util.Scanner;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
//import sql.MySQLCategorieDAO;


 
public class Main {
	public static void main(String[] args) {

		Scanner scanner=new Scanner(System.in);
		System.out.println("Passer de MySQL à ListeMemoire?  1:yes 2:no");
		int option=scanner.nextInt();
		if(option==1) {
			DAOFactory daos =DAOFactory.getDAOFactory(Persistance.ListMemoire);
		}
		else if(option==2) {
		System.out.println("choisir une partie");
		System.out.println("1:Catégorie 2: Produit 3: Client 4: Commande 5: Ligne de commande");
		int partie = scanner.nextInt();
		
// categorie
		if (partie==1) {
			MainCategorie.main(args);
		}
		
		
// Produit
		else if(partie==2) {
			MainProduit.main(args);
		}
	
// Client
		else if( partie==3) {
			MainClient.main(args);
		}
		
		
//commande	
		else if(partie==4) {
			MainCommande.main(args);
	
		}
//ligne de commande
		else if (partie==5) {
			MainLignedecommande.main(args);;
			
		}
		
		else {
			System.out.println("invalide");
		}
		
}}}
		

	
	