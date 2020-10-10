package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ListMemoire.ListeMemoireLignedeCommandeDAO;
import Metier.CMCategorie;
import Metier.CMClient;
import Metier.CMCommande;
import Metier.CMLignedeCommande;
import Metier.CMProduit;
import SQL.MySQLLignedeCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLLignedeCommandeDAOTest {
private CMLignedeCommande l;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
    	CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
    	CMProduit p=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	CMClient cli=new CMClient(2,"Jack","Ma");
    	CMCommande c=new CMCommande(1, "01-01-2020" ,cli);
    	l=new CMLignedeCommande(c,p,5);
	MySQLLignedeCommandeDAO.getInstance().create(l);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLLignedeCommandeDAO.getInstance().delete(l);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
	int id=l.getCMCommande().getId();
	
	CMLignedeCommande cBdd=MySQLLignedeCommandeDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(l.getCMCommande().getId());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testCreate() throws Exception {
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
    	CMProduit p=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	CMClient cli=new CMClient(2,"Jack","Ma");
    	CMCommande c=new CMCommande(1, "01-01-2020" ,cli);
    	CMLignedeCommande l2=new CMLignedeCommande(c,p,6);
		try {
		    
		MySQLLignedeCommandeDAO.getInstance().create(l2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(l.getCMProduit().getIdProduit(),1);
		assertEquals(l.getQuantite(),6);
		assertEquals(l.getCMProduit().getTarif(),1.0); 
		
		MySQLLignedeCommandeDAO.getInstance().delete(l2);
		
	}
	@Test
	public void testDelete() throws Exception {
	   
		CMCategorie cat=new CMCategorie(3,"Watch","watch.png");
    	CMProduit p=new CMProduit(1, "aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	CMClient cli=new CMClient(2,"Jack","Ma");
    	CMCommande c=new CMCommande(1, "01-01-2020" ,cli);
    	CMLignedeCommande l2=new CMLignedeCommande(c,p,6);
    	
	    MySQLLignedeCommandeDAO.getInstance().create(l2);
		
		//int idd = l2.getIdCommande();
		assertTrue(MySQLLignedeCommandeDAO.getInstance().delete(l2));
		
		//CMLignedeCommande cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(idd);
		//assertNull(cl);
		
		assertFalse(MySQLLignedeCommandeDAO.getInstance().delete(l2));
	
		
	
		
		
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMLignedeCommande c2= new CMLignedeCommande(l.getIdCommande(),1,5,3.0);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().update(c2);
		CMLignedeCommande l3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(c2.getIdCommande());
		
		assertEquals(1, l3.getIdProduit());
		assertEquals(3, l3.getQuantite());
		assertEquals(1.0, 1.0 ,l3.getTarifUnitaire());
	}

}
