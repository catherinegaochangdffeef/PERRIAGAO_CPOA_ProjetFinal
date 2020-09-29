package dao;

import SQL.MySQLCategorieDAO;
import SQL.MySQLClientDAO;
import SQL.MySQLCommandeDAO;
import SQL.MySQLLignedeCommandeDAO;
import SQL.MySQLProduitDAO;


public class MySQLDAOFactory extends DAOFactory{

	@Override
	public CategorieDAO getCategorieDAO() {
		return MySQLCategorieDAO.getInstance();
		
	}

	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
		
	}
	
	@Override
	public CommandeDAO getCommandeDAO() {
		return MySQLCommandeDAO.getInstance();
		
	}
	
	@Override
	public LignedeCommandeDAO getLignedeCommandeDAO() {
		return MySQLLignedeCommandeDAO.getInstance();
		
	}
	@Override
	public ProduitDAO getProduitDAO() {
		return MySQLProduitDAO.getInstance();
		
	}
	
}
