package dao;


import metier.CMClient;

public interface ClientDAO extends DAO<CMClient> {
	public abstract CMClient getById(int id) throws Exception;
	
}
