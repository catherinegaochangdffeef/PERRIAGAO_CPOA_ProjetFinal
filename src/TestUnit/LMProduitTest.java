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
/*
private int id_produit,id_categorie;
private String nom, description, visuel;
private float tarif;*/
public class LMProduitTest {
    private CMProduit p;
    
    @Before
    public void Setup() {
	p =new CMProduit(1,1, "aaa", "222xx","xxx.png",10);
	ListeMemoireProduitDAO.getInstance().create(p);
    }
    
    
	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getId_client();
	
	CMProduit cLm=ListeMemoireProduitDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p.getId_produit());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"aa");
		assertEquals(c.getPrenom(),"aaa");
		assertEquals(c.getIdentifiant(),"222xx");
		assertEquals(c.getMot_de_passe(),"xxx");
		assertEquals(c.getAdr_numero(),1);

	}
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireProduitDAO.getInstance().delete(p)), "");
		int id = p.getId_client();
		
		
		
		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(id);
		fail("Le client existe toujours");
		}catch(Exception e){
		    
		}
		
		try {
		ListeMemoireProduitDAO.getInstance().delete(c);
		fail("Le client existe toujours");
		}
		catch (Exception e){
		    ;
		}
		
	
		
		
	}
	


	@Test
	public void testUpdate() throws Exception {

		
		CMProduit p2= new CMProduit(p.getId_produit(),"bb","bbb","333zz","yyy",2, "5",58111,"zteM","ecnarF");
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().update(p2);
		CMProduit p3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p2.getId_produit());
		
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
	
	@Test
	public void testfindAll() throws Exception{
		    	
			CMProduit c2=new CMProduit(1,"aa", "aaa", "222xx","xxx",1,"3",57000," Metz","France ");
			
			ListeMemoireProduitDAO lma = (ListeMemoireProduitDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO();
			
			ArrayList<CMProduit> ar = new ArrayList<CMProduit>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().delete(p);
			
		}
}
