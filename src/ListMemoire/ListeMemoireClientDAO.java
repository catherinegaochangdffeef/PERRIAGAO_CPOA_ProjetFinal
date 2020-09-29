package ListMemoire;

import java.util.ArrayList;
import java.util.List;

import Metier.CMClient;
import dao.ClientDAO;

public class ListeMemoireClientDAO implements ClientDAO{

	private static ListeMemoireClientDAO instance;
	
	private List<CMClient> donnees;
	
	public static ListeMemoireClientDAO getInstance() {
		if(instance == null) {
			instance = new ListeMemoireClientDAO();
		}
		return instance;
	}
	private ListeMemoireClientDAO() {
		this.donnees=new ArrayList<CMClient>();
		
		this.donnees.add(new CMClient(1,"GAO","Chang","gao9","2",2334,"57",57000,"Metz","France"));
		this.donnees.add(new CMClient(2,"AlEX","Nicola","nicola0","4",4444,"57",57510,"Lyon","France"));
	}
	
	@Override
	public boolean create(CMClient objet) {
		objet.setId_client(3);
		while (this.donnees.contains(objet)) {
			objet.setId_client(objet.getId_client()+1);
		}
		boolean ok= this.donnees.add(objet);
		return ok;
	}
	

	@Override
	public boolean update(CMClient objet){
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un client inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(CMClient objet) {

		CMClient supprime;

		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un client inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}
	@Override
	public CMClient getById(int id) throws Exception {
	
			
		int idx = this.donnees.indexOf(new CMClient(id,"GAO","Chang","gao9","2",2334,"57",57000,"Metz","France"));
				if (idx == -1) {
					throw new IllegalArgumentException("Aucune categorie ne possède de cet identifiant");
				} else {
					return this.donnees.get(idx);
				}
	}

	@Override
	public ArrayList<CMClient> findAll() throws Exception {
		// TODO Auto-generated method stub
		return (ArrayList<CMClient>) this.donnees;
	}



}