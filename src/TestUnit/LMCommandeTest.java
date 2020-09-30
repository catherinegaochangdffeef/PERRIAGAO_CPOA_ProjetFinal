package TestUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import ListMemoire.ListeMemoireCommandeDAO;
import Metier.CMCommande;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;


public class LMCommandeTest {
private CMCommande c;
    
    @Before
    public void Setup() throws Exception {
	c =new CMCommande(1, "01-01-2020",1 );
	ListeMemoireCommandeDAO.getInstance().create(c);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------       
	@Test
	public void testSelectExiste() throws Exception {
		
	int id=c.getId();
	
	CMCommande cLm=ListeMemoireCommandeDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().getById(c.getId());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
	
	
	
}
