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

import ListMemoire.ListeMemoireCategorieDAO;
import Metier.CMCategorie;
import SQL.MySQLCategorieDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLCategorieDAOTest {
private CMCategorie c;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	c=new CMCategorie(1,"aa", "aaa.png");
	MySQLCategorieDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLCategorieDAO.getInstance().delete(c);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
		
	
	
	
	int id=c.getId();
	
	CMCategorie cBdd=MySQLCategorieDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(c.getId());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	@Test
	public void testCreate() throws Exception {
	    CMCategorie c2 = new CMCategorie(1,"aa", "aaa.png");
		try {
		    
		MySQLCategorieDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		//assertEquals(c.getId(),1);
		assertEquals(c.getTitre(),"aa");
		assertEquals(c.getVisuel(),"aaa.png");
		
		MySQLCategorieDAO.getInstance().delete(c2);
		
	}
	@Test
	public void testDelete() throws Exception {
	    

	    CMCategorie c2 =new CMCategorie(1,"aa", "aaa.png");
	    MySQLCategorieDAO.getInstance().create(c2);
		
		int idd = c2.getId();
		assertTrue(MySQLCategorieDAO.getInstance().delete(c2));
		
		CMCategorie cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLCategorieDAO.getInstance().delete(cl));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMCategorie c2= new CMCategorie(c.getId(),"bb","bbb.png");
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().update(c2);
		CMCategorie c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().getById(c2.getId());
		
		assertEquals("bb", c3.getTitre());
		assertEquals("bbb", c3.getVisuel());
	}
	@Test
	public void testfindAll() throws Exception{
		    	
			CMCategorie c2=new CMCategorie(1,"aa", "aaa");
			
			ListeMemoireCategorieDAO lma = (ListeMemoireCategorieDAO) DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO();
			
			ArrayList<CMCategorie> ar = new ArrayList<CMCategorie>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().delete(c);
			
		}
}
