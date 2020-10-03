package dao;

import Metier.CMProduit;
public interface ProduitDAO extends DAO<CMProduit> {

	public abstract CMProduit getById(int id_produit) throws Exception;

}
