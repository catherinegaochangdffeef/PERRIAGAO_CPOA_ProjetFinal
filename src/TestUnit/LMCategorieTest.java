package TestUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ListMemoire.ListeMemoireCategorieDAO;
import Metier.CMCategorie;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class LMCategorieTest {
    private CMCategorie c;
    
    @Before
    public void Setup() {
	c=new CMCategorie(1,"aa", "aaa.png");
	ListeMemoireCategorieDAO.getInstance().create(c);
    }
    
    
	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getId();
	
	CMCategorie cLm=ListeMemoireCategorieDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c.getId());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(c.getTitre(),"aa");
		assertEquals(c.getVisuel(),"aaa.png");

		
	}
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireCategorieDAO.getInstance().delete(c)), "");
		int id = c.getId();
		
		
		
		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(id);
		fail("Le client existe toujours");
		}catch(Exception e){
		    
		}
		
		try {
		ListeMemoireCategorieDAO.getInstance().delete(c);
		fail("Le client existe toujours");
		}
		catch (Exception e){
		    ;
		}
		
	
		
		
	}
	


	@Test
	public void testUpdate() throws Exception {

		
		CMCategorie c2= new CMCategorie(c.getId(),"bb","bbb.png");
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().update(c2);
		CMCategorie c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().getById(c2.getId());
		assertEquals("bb", c3.getTitre());
		assertEquals("bbb", c3.getVisuel());
	
	}
	
	@Test
	public void testfindAll() throws Exception{
		    	
			CMCategorie c2=new CMCategorie(1,"aa", "aaa");
			
			ListeMemoireCategorieDAO lma = (ListeMemoireCategorieDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO();
			
			ArrayList<CMCategorie> ar = new ArrayList<CMCategorie>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().delete(c);
			
		}
}