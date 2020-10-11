package dao;

import java.util.ArrayList;

import Metier.CMLignedeCommande;

public interface LignedeCommandeDAO extends DAO<CMLignedeCommande> {
	//public abstract CMLignedeCommande getById(int id) throws Exception;

	public abstract CMLignedeCommande getById2(int id,int idproduit) throws Exception;
}
