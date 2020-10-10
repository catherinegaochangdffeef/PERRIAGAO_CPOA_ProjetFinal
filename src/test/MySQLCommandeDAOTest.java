package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Metier.CMClient;
import Metier.CMCommande;
import SQL.MySQLCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


public class MySQLCommandeDAOTest {
private CMCommande c;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	CMClient cli=new CMClient(2,"Jack","Ma");
    c=new CMCommande(1, "01-01-2020" ,cli);
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
		CMClient client=new CMClient(2,"JOURNET","Maxime");
		CMCommande c2=new CMCommande(3,"2020/01/01",client);
		try {
		    
		MySQLCommandeDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(c.getId(),3);
		assertEquals(c.getIdClient().getIdClient(),2);
		assertEquals(c.getDateCommande().toString(),"2020/01/01");
		
		MySQLCommandeDAO.getInstance().delete(c2);
		
	}
	@Test
	public void testDelete() throws Exception {
	   
		CMClient client=new CMClient(2,"JOURNET","Maxime");
		CMCommande c2=new CMCommande(3,"2020/01/01",client);
	    MySQLCommandeDAO.getInstance().create(c2);
		
		//int idd = c2.getId();
		assertTrue(MySQLCommandeDAO.getInstance().delete(c2));
		
		//CMCommande cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(idd);
		//assertNull(cl);
		
		assertFalse(MySQLCommandeDAO.getInstance().delete(c2));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		CMClient client=new CMClient(2,"JOURNET","Maxime");
		CMCommande c2=new CMCommande(3,"2020/01/01",client);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().update(c2);
		//CMCommande c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c2.getId());
		
		assertEquals(1, c2.getIdClient());
		assertEquals("01-01-2020", c2.getDateCommande() );
	}

}
