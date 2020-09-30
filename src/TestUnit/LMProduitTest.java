package TestUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ListMemoire.ListeMemoireProduitDAO;
import Metier.CMProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


public class LMProduitTest {
    private CMProduit p;
    
    @Before
    public void Setup() throws Exception {
    	
	p =new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
	ListeMemoireProduitDAO.getInstance().create(p);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------       
	@Test
	public void testSelectExiste() throws Exception {
		
	int id=p.getId_produit();
	
	CMProduit cLm=ListeMemoireProduitDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testGetbyid() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p.getId_produit());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(p.getNom(),"aaa");
		assertEquals(p.getDescription(),"222xx");
		assertEquals(p.getTarif(),(float)1,0);
		assertEquals(p.getVisuel(),"xxx.png");
		assertEquals(p.getId_categorie(),10);

	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireProduitDAO.getInstance().delete(p)), "");
		int id = p.getId_produit();

		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(id);
		fail("Le produit existe toujours");
		}catch(Exception e){
		    ;
		}
		
		try {
		ListeMemoireProduitDAO.getInstance().delete(p);
		fail("Le produit existe toujours");
		}
		catch (Exception e){
		    ;
		}	
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testUpdate() throws Exception {
		
		CMProduit p2= new CMProduit(p.getId_produit(),"bbb","333zz",(float)2.0,"yyy.png",12);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().update(p2);
		CMProduit p3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p2.getId_produit());
		
		assertEquals("bbb", p3.getNom());
		assertEquals("333zz", p3.getDescription());
		assertEquals((float)1,0, p3.getTarif());
		assertEquals("yyy.png", p3.getVisuel());
		assertEquals(2, p3.getId_categorie());
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testfindAll() throws Exception{
		
			CMProduit c2=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",10);
			ListeMemoireProduitDAO lma = (ListeMemoireProduitDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO();
			ArrayList<CMProduit> ar = new ArrayList<CMProduit>(lma.findAll());
			ar.add(c2);
			lma.create(c2);	
			assertEquals(lma.findAll(), ar);
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().delete(p);
			
		}
}
