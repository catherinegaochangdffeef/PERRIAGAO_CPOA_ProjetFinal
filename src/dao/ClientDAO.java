package dao;


import Metier.CMClient;

public interface ClientDAO extends DAO<CMClient> {
	public abstract CMClient getById(int idClient) throws Exception;
	
}
