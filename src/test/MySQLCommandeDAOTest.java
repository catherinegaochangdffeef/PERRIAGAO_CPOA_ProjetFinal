
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Metier.CMClient;
import Metier.CMCommande;
import SQL.MySQLClientDAO;
import SQL.MySQLCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


public class MySQLCommandeDAOTest {
private CMCommande c;
private CMClient client;   
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
    	client=new CMClient("JOURNET","Maxime","ddd","ddd","123","dd","33333","METZ","France");
    c=new CMCommande("2020-01-01" ,client);
	MySQLCommandeDAO.getInstance().create(c);
	MySQLClientDAO.getInstance().create(client);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLCommandeDAO.getInstance().delete(c);
	MySQLClientDAO.getInstance().delete(client);
	
    }
    
	@Test
	public void testSelectExiste() throws Exception {
	assertNotNull(c);
 }
	@Test
	public void testGetbyid() throws Exception {
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c.getIdCommande());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	}
	@Test
	public void testCreate() throws Exception {
		client=new CMClient("JOURNET","Maxime","ddd","ddd","123","dd","33333","METZ","France");
		 c=new CMCommande("2020-01-01",client);
		try {
	    MySQLClientDAO.getInstance().create(client);	
		MySQLCommandeDAO.getInstance().create(c);
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(c.getIdClient().getNom(),"JOURNET");
		assertEquals(c.getDateCommande().toString(),"2020-01-01");
		MySQLCommandeDAO.getInstance().delete(c);
		MySQLClientDAO.getInstance().delete(client);
	}
	@Test
	public void testDelete() throws Exception {
	    try {
			MySQLCommandeDAO.getInstance().delete(c);
		}catch(Exception e) {
			fail("L'abonnement n'a pas était supprimer");
			}	
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		client=new CMClient("JOURNET","Maxime","ddd","ddd","123","dd","33333","METZ","France");
		c=new CMCommande("2020-01-01",client);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().update(c);
		
		assertEquals(c.getIdClient().getNom(),"JOURNET");
		assertEquals(c.getDateCommande().toString(),"2020-01-01");
		MySQLCommandeDAO.getInstance().delete(c);
		MySQLClientDAO.getInstance().delete(client);
	}

}

