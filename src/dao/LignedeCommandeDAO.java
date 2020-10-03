package dao;

import Metier.CMLignedeCommande;

public interface LignedeCommandeDAO extends DAO<CMLignedeCommande> {
	public abstract CMLignedeCommande getById(int id) throws Exception;
}
