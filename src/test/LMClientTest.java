package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import ListMemoire.ListeMemoireClientDAO;
import Metier.CMClient;



public class LMClientTest {
    
    private CMClient c;
    
    @Before
    public void Setup() {
	c=new CMClient(1,"aa", "aaa");
	ListeMemoireClientDAO.getInstance().create(c);
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------   
	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getIdClient();
	
	CMClient cLm=ListeMemoireClientDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testGetbyid() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c.getIdClient());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"aa");
		assertEquals(c.getPrenom(),"aaa");

	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireClientDAO.getInstance().delete(c)), "");
		int id = c.getIdClient();
		
		
		
		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(id);
		fail("Le client existe toujours");
		}catch(Exception e){
		    
		}
		
		try {
		ListeMemoireClientDAO.getInstance().delete(c);
		fail("Le client existe toujours");
		}
		catch (Exception e){
		    ;
		}
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testUpdate() throws Exception {

		
		CMClient c2= new CMClient(c.getIdClient(),"bb","bbb");
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().update(c2);
		CMClient c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c2.getIdClient());
		
		assertEquals("bb", c3.getNom());
		assertEquals("bbb", c3.getPrenom());

	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testfindAll() throws Exception{
		    	
			CMClient c2=new CMClient(1,"aa", "aaa");
			
			ListeMemoireClientDAO lma = (ListeMemoireClientDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO();
			
			ArrayList<CMClient> ar = new ArrayList<CMClient>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().delete(c);
			
		}
	

}