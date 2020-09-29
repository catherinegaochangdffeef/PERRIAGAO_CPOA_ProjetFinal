package dao;


import Metier.CMCommande;

public interface CommandeDAO extends DAO<CMCommande> {
	public abstract CMCommande getById(int id) throws Exception;
}
