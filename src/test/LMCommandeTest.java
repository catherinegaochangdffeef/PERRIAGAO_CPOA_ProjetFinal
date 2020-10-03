package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import ListMemoire.ListeMemoireCategorieDAO;
import ListMemoire.ListeMemoireCommandeDAO;
import Metier.CMCategorie;
import Metier.CMCommande;


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
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testCreate() throws Exception {
			
		//assertEquals(c.getId(),1);
		assertEquals(c.getId(),1);
		assertEquals(c.getIdClient(),1);
		assertEquals(c.getDateCommande(),"01-01-2020"); //pas sûre que la date doit être appelé comme ca 

		}	
	//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireCommandeDAO.getInstance().delete(c)), "");
		int id = c.getId();

		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().getById(id);
		fail("La commande existe toujours");
		}catch(Exception e){
		    ;
		}
		
		try {
		ListeMemoireCommandeDAO.getInstance().delete(c);
		fail("La commande existe toujours");
		}
		catch (Exception e){
		    ;
		}	
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testUpdate() throws Exception {
			
		CMCommande c2= new CMCommande(c.getId(),"01-01-2020",1);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().update(c2);
		CMCommande c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().getById(c2.getId());
		assertEquals(1, c3.getIdClient());
		assertEquals("01-01-2020", c3.getDateCommande() );
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   
	@Test
	public void testfindAll() throws Exception{
		    	
			CMCommande c2=new CMCommande(2,"02-02-2020", 2);
			
			ListeMemoireCommandeDAO lma = (ListeMemoireCommandeDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO();			
			ArrayList<CMCommande> ar = new ArrayList<CMCommande>(lma.findAll());

			ar.add(c2);
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);		
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().delete(c);
				
			}
}
