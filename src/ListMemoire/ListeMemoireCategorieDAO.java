package ListMemoire;

import java.util.ArrayList;
import java.util.List;

import dao.CategorieDAO;
import Metier.CMCategorie;

public class ListeMemoireCategorieDAO implements CategorieDAO{
	
	private static ListeMemoireCategorieDAO instance;

	private List<CMCategorie> donnees;


	public static ListeMemoireCategorieDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}

		return instance;
	}

	private ListeMemoireCategorieDAO() {

		this.donnees = new ArrayList<CMCategorie>();

		this.donnees.add(new CMCategorie(1, "Pulls", "pulls.png"));
		this.donnees.add(new CMCategorie(2, "Bonnets", "bonnets.png"));
	}


	public boolean create(CMCategorie objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet mÈtier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}


	public boolean update(CMCategorie objet) {
		
		// Ne fonctionne que si l'objet m√©tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}


	public boolean delete(CMCategorie objet) {

		CMCategorie supprime;
		
		// Ne fonctionne que si l'objet m√©tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	
	public CMCategorie getById(int id) {
		// Ne fonctionne que si l'objet m√©tier est bien fait...
		int idx = this.donnees.indexOf(new CMCategorie(id, "test", "test.png"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne prossËde cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}


	public ArrayList<CMCategorie> findAll() {
		return (ArrayList<CMCategorie>) this.donnees;
	}

	@Override
	public CMCategorie getByTitre(String titre) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	}

	
