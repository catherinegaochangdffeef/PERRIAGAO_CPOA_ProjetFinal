package dao;

import Metier.CMCategorie;

public interface CategorieDAO extends DAO<CMCategorie>{

	public abstract CMCategorie getById(int id) throws Exception;

}
