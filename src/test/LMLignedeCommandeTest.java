package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import listmemoire.ListeMemoireLignedeCommandeDAO;
import listmemoire.ListeMemoireProduitDAO;
import metier.CMLignedeCommande;

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
		
	int id=l.getId_produit();
	
	CMLignedeCommande cLm=ListeMemoireLignedeCommandeDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(l.getId_commande());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(l.getId_produit(),1);
		assertEquals(l.getQuantite(),3);
		assertEquals(l.getTarif_unitaire(), 1.0, 1.0); //ici trois valeurs car la variable est un double, donc un delta de comparaison est necessaire
	
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------    
	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireLignedeCommandeDAO.getInstance().delete(l)), "");
		int id = l.getId_commande();

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
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}