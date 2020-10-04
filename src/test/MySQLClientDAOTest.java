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

import ListMemoire.ListeMemoireClientDAO;
import Metier.CMClient;
import SQL.MySQLClientDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


public class MySQLClientDAOTest {
private CMClient c;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	c=new CMClient(1,"aa", "aaa", "222xx","xxx",1,"3",57000," Metz","France ");
	MySQLClientDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLClientDAO.getInstance().delete(c);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
		
	
	
	
	int id=c.getIdClient();
	
	CMClient cBdd=MySQLClientDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(c.getIdClient());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	@Test
	public void testCreate() throws Exception {
	    CMClient c2 = new CMClient(1,"aa", "aaa", "222xx","xxx",1,"3",57000,"Metz","France ");
		try {
		    
		MySQLClientDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"aa");
		assertEquals(c.getPrenom(),"aaa");
		assertEquals(c.getIdentifiant(),"222xx");
		assertEquals(c.getMotDePasse(),"xxx");
		assertEquals(c.getAdrNumero(),1);
		assertEquals(c.getAdrVoie(),"3");
		assertEquals(c.getAdrCodePostal(),57000);
		assertEquals(c.getAdrVille(),"Metz");
		assertEquals(c.getAdrPays(),"France");
		
		MySQLClientDAO.getInstance().delete(c2);
		
	}
	@Test
	public void testDelete() throws Exception {
	    

	    CMClient c2 =new CMClient(1,"aa", "aaa", "222xx","xxx",1,"3",57000,"Metz","France ");
	    MySQLClientDAO.getInstance().create(c2);
		
		int idd = c2.getIdClient();
		assertTrue(MySQLClientDAO.getInstance().delete(c2));
		
		CMClient cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLClientDAO.getInstance().delete(cl));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMClient c2= new CMClient(c.getIdClient(),"bb","bbb","333zz","yyy",2, "5",58111,"zteM","ecnarF");
		DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().update(c2);
		CMClient c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(c2.getIdClient());
		
		assertEquals("bb", c3.getNom());
		assertEquals("bbb", c3.getPrenom());
		assertEquals("333zz", c3.getIdentifiant());
		assertEquals("yyy", c3.getMotDePasse());
		assertEquals(2, c3.getAdrNumero());
		assertEquals("5", c3.getAdrVoie());
		assertEquals(58111, c3.getAdrCodePostal());
		assertEquals("zteM", c3.getAdrVille());
		assertEquals("ecnarF", c3.getAdrPays());
	}

}
