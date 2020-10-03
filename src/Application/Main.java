package application;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
//import sql.MySQLCategorieDAO;
import metier.CMCategorie;
import metier.CMClient;
import metier.CMCommande;
import metier.CMLignedeCommande;
import metier.CMProduit;
 
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
				
				
				
				}
			else{
				System.out.println("invalide");
			}
		}
		
		
// Produit
		else if(partie==2) {
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
					e.printStackTrace();
				}
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
			
			
		}
	
// Client
		else if( partie==3) {
			System.out.println("Client");
			System.out.println("Choisir une méthode");
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
			}
		}
		
		
//commande	
		else if(partie==4) {
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
	
		}
//ligne de commande
		else if (partie==5) {
			System.out.println("Ligne de Commande");
			System.out.println("Choisir une méthode");
			System.out.println("1.Ajouter 2:Modifier 3:Supprimer 4: Chercher");
			int p=scanner.nextInt();
			if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("id_commande=");
				int idcom=scanner.nextInt();
				System.out.println ("id_produit=");
				int idp=scanner.nextInt();
				System.out.println ("quantite=");
				int quan=scanner.nextInt();
				System.out.println ("tarif_unitaire =");
				double tarifu=scanner.nextDouble();
				try {
					daos.getLignedeCommandeDAO().create(new CMLignedeCommande(idcom,idp,quan,tarifu));
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			else if(p==2) {
				System.out.println("Modifier");
				System.out.println ("id_commande=");
				int idcom=scanner.nextInt();
				System.out.println ("id_produit=");
				int idp=scanner.nextInt();
				System.out.println ("quantite=");
				int quan=scanner.nextInt();
				System.out.println ("tarif_unitaire =");
				double tarifu=scanner.nextDouble();
				try {
					daos.getLignedeCommandeDAO().update(new CMLignedeCommande(idcom,idp,quan,tarifu));
				} catch (Exception e) {
					e.printStackTrace();
				}
					}
			else if(p==3) {
				System.out.println("Supprimer");
				System.out.println ("id_commande=");
				int idcom=scanner.nextInt();
				System.out.println ("id_produit=");
				int idp=scanner.nextInt();
				try {
					daos.getLignedeCommandeDAO().delete(new CMLignedeCommande(idcom,idp));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			else if(p==4) {
				System.out.println("Chercher");
				System.out.println("id_commande=");
				int id=scanner.nextInt();	
				try {
					daos.getLignedeCommandeDAO().getById(id);
				} catch (Exception e) {
					System.out.println("cette ligne de commande n'exist pas!");
					e.printStackTrace();
				}
			}
		}
			
		}
		
		else {
			System.out.println("invalide");
		}
		

	}
}
	