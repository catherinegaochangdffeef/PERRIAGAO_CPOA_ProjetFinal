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

import ListMemoire.ListeMemoireCommandeDAO;
import Metier.CMCommande;
import SQL.MySQLCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLCommandeDAOTest {
private CMCommande c;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	c=new CMCommande(1, "01-01-2020",1);
	MySQLCommandeDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLCommandeDAO.getInstance().delete(c);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
		
	
	
	
	int id=c.getId();
	
	CMCommande cBdd=MySQLCommandeDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c.getId());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	@Test
	public void testCreate() throws Exception {
	    CMCommande c2 = new CMCommande(1, "01-01-2020",1);
		try {
		    
		MySQLCommandeDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(c.getId(),1);
		assertEquals(c.getIdClient(),1);
		assertEquals(c.getDateCommande(),"01-01-2020");
		
		MySQLCommandeDAO.getInstance().delete(c2);
		
	}
	@Test
	public void testDelete() throws Exception {
	    

	    CMCommande c2 =new CMCommande(1, "01-01-2020",1);
	    MySQLCommandeDAO.getInstance().create(c2);
		
		int idd = c2.getId();
		assertTrue(MySQLCommandeDAO.getInstance().delete(c2));
		
		CMCommande cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLCommandeDAO.getInstance().delete(cl));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMCommande c2= new CMCommande(c.getId(),"01-01-2020",1);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().update(c2);
		CMCommande c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c2.getId());
		
		assertEquals(1, c3.getIdClient());
		assertEquals("01-01-2020", c3.getDateCommande() );
	}
	@Test
	public void testfindAll() throws Exception{
		    	
			CMCommande c2=new CMCommande(2,"02-02-2020", 2);
			
			ListeMemoireCommandeDAO lma = (ListeMemoireCommandeDAO) DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO();
			
			ArrayList<CMCommande> ar = new ArrayList<CMCommande>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().delete(c);
			
		}
}
