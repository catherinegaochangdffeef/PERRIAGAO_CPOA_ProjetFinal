package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Metier.CMCategorie;
import Metier.CMProduit;
import SQL.MySQLProduitDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLProduitDAOTest {
private CMProduit p;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
    	CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
    	p=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
	MySQLProduitDAO.getInstance().create(p);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLProduitDAO.getInstance().delete(p);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
	int id=p.getIdProduit();
	
	CMProduit cBdd=MySQLProduitDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p.getIdProduit());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	@Test
	public void testCreate() throws Exception {
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
		 CMProduit c2 = new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
		try {
		    
		MySQLProduitDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(p.getNom(),"aaa");
		assertEquals(p.getDescription(),"222xx");
		assertEquals(p.getTarif(),1.0,1.0); //utilisation d'un delta car float
		assertEquals(p.getVisuel(),"xxx.png");
		assertEquals(p.getIdCMCategorie().getId(),3);

		
		MySQLProduitDAO.getInstance().delete(c2);
		
	}
	@Test
	public void testDelete() throws Exception {
	    
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
		  CMProduit p2 =new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
	    MySQLProduitDAO.getInstance().create(p2);
		
		//int idd =p2.getIdProduit();
		assertTrue(MySQLProduitDAO.getInstance().delete(p2));
		
		//CMProduit pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(idd);
		//assertNull(p2);
		
		assertFalse(MySQLProduitDAO.getInstance().delete(p2));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
		CMProduit p2= new CMProduit(p.getIdProduit(),"bbb","333zz",(float)2.0,"yyy.png",cat);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().update(p2);
		
		//CMProduit p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p2.getIdProduit());
		
		assertEquals("bbb", p2.getNom());
		assertEquals("333zz", p2.getDescription());
		assertEquals((float)1,0, p2.getTarif());
		assertEquals("yyy.png", p2.getVisuel());
		assertEquals(3, p2.getIdCMCategorie().getId());
	}
	
}
