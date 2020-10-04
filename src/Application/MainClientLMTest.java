package Application;

import java.util.Scanner;

import Metier.CMClient;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MainClientLMTest {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.ListMemoire);
		Persistance bdd = DAOFactory.Persistance.ListMemoire;
		
	System.out.println("Ajouter (1) Supprimer (2) Ou mettre a jour (autre)");
	int type = sc.nextInt();
	if(sc.hasNextLine()) sc.nextLine();
	switch(type) {
	case 1:
		System.out.println("Entrez les informations du client ï¿½ï¿½ ajoutï¿½ï¿½");
		
		try {
		    	CMClient cll = new CMClient(type);
		    	DAOFactory.getDAOFactory(bdd).getClientDAO().create(cll);
			System.out.println("Le client est ajoutï¿½ï¿½ avec l'id :" + cll.getIdClient());
		} catch (Exception e) {
			System.out.println("L'erreur suivante a eu lieu : " + e);
		}
		break;
	case 2:
			System.out.println("Quel est l'ID du client a supprimer ?");
			int idcll = sc.nextInt();
			CMClient cll =new CMClient(idcll);
			cll.setIdClient(idcll);
		
		try {
			if(DAOFactory.getDAOFactory(bdd).getClientDAO().delete(cll)) {
				System.out.println("La suppression a eu lieu avec succes");
			}else {
				System.out.println("La suppression n'a pas eu lieu : vérifier l'id du client");
			}
			
		} catch (Exception e) {
			System.out.println("L'erreur suivante a eu lieu : " + e);
		}
		
		break;
	default:
		System.out.println("quel est l'ID du client a mettre a jour ?");
		int id = sc.nextInt();
		if(sc.hasNextLine()) sc.nextLine();
		System.out.println("Entrez les informations sur le client mis a jour :");
		CMClient cl = new CMClient(id);
		cl.setIdClient(id);
		try {
			if(DAOFactory.getDAOFactory(bdd).getClientDAO().update(cl)) {
				System.out.println("Le client a ï¿½ï¿½tait mis a jour avec succï¿½ï¿½es");
			}else {
				System.out.println("Aucun client n'a ï¿½ï¿½tait modifier : verifiez l'ID");
			}
			}
		 catch (Exception e) {
			System.out.println("L'erreur suivante a eu lieu : " + e);
		}
		
		
	}
			
}
}
