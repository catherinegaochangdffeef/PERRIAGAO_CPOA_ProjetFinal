package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ListMemoire.ListeMemoireLignedeCommandeDAO;
import Metier.CMLignedeCommande;
import SQL.MySQLLignedeCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLLignedeCommandeDAOTest {
private CMLignedeCommande l;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	l=new CMLignedeCommande(1,1,5,3.0);
	MySQLLignedeCommandeDAO.getInstance().create(l);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLLignedeCommandeDAO.getInstance().delete(l);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
		
	
	
	
	int id=l.getIdCommande();
	
	CMLignedeCommande cBdd=MySQLLignedeCommandeDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(l.getIdCommande());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	@Test
	public void testCreate() throws Exception {
	    CMLignedeCommande l2 = new CMLignedeCommande(1,1,5,3.0);
		try {
		    
		MySQLLignedeCommandeDAO.getInstance().create(l2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(l.getIdProduit(),1);
		assertEquals(l.getQuantite(),3);
		assertEquals(l.getTarifUnitaire(), 1.0, 1.0); 
		
		MySQLLignedeCommandeDAO.getInstance().delete(l2);
		
	}
	@Test
	public void testDelete() throws Exception {
	    

	    CMLignedeCommande l2 =new CMLignedeCommande(1,1,5,3.0);
	    MySQLLignedeCommandeDAO.getInstance().create(l2);
		
		int idd = l2.getIdCommande();
		assertTrue(MySQLLignedeCommandeDAO.getInstance().delete(l2));
		
		CMLignedeCommande cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLLignedeCommandeDAO.getInstance().delete(cl));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMLignedeCommande c2= new CMLignedeCommande(l.getIdCommande(),1,5,3.0);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().update(c2);
		CMLignedeCommande l3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(c2.getIdCommande());
		
		assertEquals(1, l3.getIdProduit());
		assertEquals(3, l3.getQuantite());
		assertEquals(1.0, 1.0 ,l3.getTarifUnitaire());
	}

}
