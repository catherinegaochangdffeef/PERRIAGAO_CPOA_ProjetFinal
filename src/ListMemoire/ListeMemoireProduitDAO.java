package ListMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.ProduitDAO;
import Metier.CMCategorie;
import Metier.CMProduit;

public class ListeMemoireProduitDAO implements ProduitDAO {
	private static ListeMemoireProduitDAO instance;

	private List<CMProduit> donnees;

	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}

	private ListeMemoireProduitDAO() {
 
		CMCategorie cat=new CMCategorie(1,"Ipad","ipad.png");
		CMCategorie cat2=new CMCategorie(2,"Ipad2","ipad2.png");
		this.donnees = new ArrayList<CMProduit>();
		this.donnees.add(new CMProduit(1, "Caf�", "caf� sans sucre",3,"eif.png",cat));
		this.donnees.add(new CMProduit(2, "Stylo", "stylo noire",5,"stylo.png",cat2));
	}
	

	@Override
	public boolean create(CMProduit objet) throws Exception {
		objet.setIdProduit(3);
		while (this.donnees.contains(objet)) {
			objet.setIdProduit(objet.getIdProduit()+1);
		}
boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(CMProduit objet) throws Exception {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une produit inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}
	

	@Override
	public boolean delete(CMProduit objet) throws Exception {
		CMProduit supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une produit inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public CMProduit getById(int id) throws Exception {
		CMCategorie cat=new CMCategorie(1,"Ipad","ipad.png");
		int idx = this.donnees.indexOf(new CMProduit(id,"Caf�", "caf� sans sucre",3,"eif.png",cat));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune produit pross�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	@Override
	public ArrayList<CMProduit> findAll() throws Exception {
		return (ArrayList<CMProduit>) this.donnees;
	}

}
