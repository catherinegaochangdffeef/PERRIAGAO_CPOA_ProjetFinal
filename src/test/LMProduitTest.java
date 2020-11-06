package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import ListMemoire.ListeMemoireProduitDAO;
import Metier.CMCategorie;
import Metier.CMProduit;


public class LMProduitTest {
    private CMProduit p;
    
    @Before
    public void Setup() throws Exception {
    	CMCategorie cat=new CMCategorie(3,"Watch","watch.png");	
    	p =new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
	ListeMemoireProduitDAO.getInstance().create(p);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------       
	@Test
	public void testSelectExiste() throws Exception {
		
	int id=p.getIdProduit();
	
	CMProduit cLm=ListeMemoireProduitDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p.getIdProduit());}
	    catch(Exception e) {
		    fail("Erreur lors de la r�cup�ration");
		}
		
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testCreate() throws Exception {
		//CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
		//assertEquals(c.getId(),1);
		assertEquals(p.getNom(),"aaa");
		assertEquals(p.getDescription(),"222xx");
		assertEquals(p.getTarif(),1.0,1.0); //utilisation d'un delta car float
		assertEquals(p.getVisuel(),"xxx.png");
		assertEquals(p.getIdCMCategorie().getId(),3);

	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireProduitDAO.getInstance().delete(p)), "");
		int id = p.getIdProduit();

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
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
		CMProduit p2= new CMProduit(p.getIdProduit(),"bbb","333zz",(float)2.0,"yyy.png",cat);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().update(p2);
		//CMProduit p3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p2.getIdProduit());
		
		assertEquals("bbb", p2.getNom());
		assertEquals("333zz", p2.getDescription());
		assertEquals((float)1,0, p2.getTarif());
		assertEquals("yyy.png", p2.getVisuel());
		assertEquals(3, p2.getIdCMCategorie().getId());
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testfindAll() throws Exception{
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
		CMProduit c2=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
			ListeMemoireProduitDAO lma = (ListeMemoireProduitDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO();
			ArrayList<CMProduit> ar = new ArrayList<CMProduit>(lma.findAll());
			ar.add(c2);
			lma.create(c2);	
			assertEquals(lma.findAll(), ar);
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().delete(p);
			
		}
}
