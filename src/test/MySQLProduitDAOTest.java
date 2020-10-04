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

import ListMemoire.ListeMemoireProduitDAO;
import Metier.CMProduit;
import SQL.MySQLProduitDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLProduitDAOTest {
private CMProduit p;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	p=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
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
	    CMProduit c2 = new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
		try {
		    
		MySQLProduitDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(p.getNom(),"aaa");
		assertEquals(p.getDescription(),"222xx");
		assertEquals(p.getTarif(),1.0,1.0); //utilisation d'un delta car float
		assertEquals(p.getVisuel(),"xxx.png");
		assertEquals(p.getIdCategorie(),10);

		
		MySQLProduitDAO.getInstance().delete(c2);
		
	}
	@Test
	public void testDelete() throws Exception {
	    

	    CMProduit p2 =new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
	    MySQLProduitDAO.getInstance().create(p2);
		
		int idd =p2.getIdProduit();
		assertTrue(MySQLProduitDAO.getInstance().delete(p2));
		
		CMProduit pr = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(idd);
		assertNull(pr);
		
		assertFalse(MySQLProduitDAO.getInstance().delete(pr));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMProduit p2= new CMProduit(p.getIdProduit(),"bbb","333zz",(float)2.0,"yyy.png",12);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().update(p2);
		CMProduit p3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().getById(p2.getIdProduit());
		
		assertEquals("bbb", p3.getNom());
		assertEquals("333zz", p3.getDescription());
		assertEquals((float)1,0, p3.getTarif());
		assertEquals("yyy.png", p3.getVisuel());
		assertEquals(2, p3.getIdCategorie());
	}
	@Test
	public void testfindAll() throws Exception{
		    	
			CMProduit p2=new CMProduit(p.getIdProduit(),"bbb","333zz",(float)2.0,"yyy.png",12);
			
			ListeMemoireProduitDAO lma = (ListeMemoireProduitDAO) DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO();
			
			ArrayList<CMProduit> ar = new ArrayList<CMProduit>(lma.findAll());
			
			ar.add(p2);
			
			lma.create(p2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().delete(p);
			
		}
}
