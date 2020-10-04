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
import ListMemoire.ListeMemoireLignedeCommandeDAO;
import Metier.CMLignedeCommande;

public class LMLignedeCommandeTest {
    private CMLignedeCommande l;
    
    @Before
    public void Setup() throws Exception {
	l =new CMLignedeCommande(1,1,5,3.0);
	ListeMemoireLignedeCommandeDAO.getInstance().create(l);
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------       
	@Test
	public void testSelectExiste() throws Exception {
		
	int id=l.getIdProduit();
	
	CMLignedeCommande cLm=ListeMemoireLignedeCommandeDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(l.getIdCommande());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(l.getIdProduit(),1);
		assertEquals(l.getQuantite(),3);
		assertEquals(l.getTarifUnitaire(), 1.0, 1.0); //ici trois valeurs car la variable est un double, donc un delta de comparaison est necessaire
	
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireLignedeCommandeDAO.getInstance().delete(l)), "");
		int id = l.getIdCommande();

		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(id);
		fail("La ligne de commande existe toujours");
		}catch(Exception e){
		    ;
		}
		
		try {
		ListeMemoireLignedeCommandeDAO.getInstance().delete(l);
		fail("La ligne de commande existe toujours");
		}
		catch (Exception e){
		    ;
		}	
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------   	
	@Test
	public void testUpdate() throws Exception {
			
		CMLignedeCommande l2= new CMLignedeCommande(l.getIdCommande(),1,3,1.0);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().update(l2);
		CMLignedeCommande l3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(l2.getIdCommande());
		assertEquals(1, l3.getIdProduit());
		assertEquals(3, l3.getQuantite());
		assertEquals(1.0, 1.0 ,l3.getTarifUnitaire());
		
	
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------   
	@Test
	public void testfindAll() throws Exception{
		    	
		CMLignedeCommande c2=new CMLignedeCommande(2,4, 2.0);
		
		ListeMemoireLignedeCommandeDAO lma = (ListeMemoireLignedeCommandeDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO();			
		ArrayList<CMLignedeCommande> ar = new ArrayList<CMLignedeCommande>(lma.findAll());

		ar.add(c2);
		lma.create(c2);
			
		assertEquals(lma.findAll(), ar);		
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().delete(l);
			
		}
	
	
}