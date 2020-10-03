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
	c=new CMClient(1,"aa", "aaa", "222xx","xxx",1,"3",57000," Metz","France ");
	ListeMemoireClientDAO.getInstance().create(c);
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------   
	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getId_client();
	
	CMClient cLm=ListeMemoireClientDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testGetbyid() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c.getId_client());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"aa");
		assertEquals(c.getPrenom(),"aaa");
		assertEquals(c.getIdentifiant(),"222xx");
		assertEquals(c.getMot_de_passe(),"xxx");
		assertEquals(c.getAdr_numero(),1);
		assertEquals(c.getAdr_voie(),"3");
		assertEquals(c.getAdr_code_postal(),57000);
		assertEquals(c.getAdr_ville(),"Metz");
		assertEquals(c.getAdr_pays(),"France");
		
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireClientDAO.getInstance().delete(c)), "");
		int id = c.getId_client();
		
		
		
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

		
		CMClient c2= new CMClient(c.getId_client(),"bb","bbb","333zz","yyy",2, "5",58111,"zteM","ecnarF");
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().update(c2);
		CMClient c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c2.getId_client());
		
		assertEquals("bb", c3.getNom());
		assertEquals("bbb", c3.getPrenom());
		assertEquals("333zz", c3.getIdentifiant());
		assertEquals("yyy", c3.getMot_de_passe());
		assertEquals(2, c3.getAdr_numero());
		assertEquals("5", c3.getAdr_voie());
		assertEquals(58111, c3.getAdr_voie());
		assertEquals("zteM", c3.getAdr_ville());
		assertEquals("ecnarF", c3.getAdr_pays());
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testfindAll() throws Exception{
		    	
			CMClient c2=new CMClient(1,"aa", "aaa", "222xx","xxx",1,"3",57000," Metz","France ");
			
			ListeMemoireClientDAO lma = (ListeMemoireClientDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO();
			
			ArrayList<CMClient> ar = new ArrayList<CMClient>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().delete(c);
			
		}
	

}