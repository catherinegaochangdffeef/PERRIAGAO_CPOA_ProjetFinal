package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Metier.CMCategorie;
import Metier.CMClient;
import Metier.CMCommande;
import Metier.CMLignedeCommande;
import Metier.CMProduit;
import SQL.MySQLCategorieDAO;
import SQL.MySQLClientDAO;
import SQL.MySQLCommandeDAO;
import SQL.MySQLLignedeCommandeDAO;
import SQL.MySQLProduitDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;

public class MySQLLignedeCommandeDAOTest {
private CMLignedeCommande l;
private CMCategorie cat;
private CMProduit p;
private CMClient cli;
private CMCommande c;
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
    cat=new CMCategorie("Watch","watch.png");
    	 p=new CMProduit("aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	 cli=new CMClient("Jack","Ma");
    	 c=new CMCommande("2020-01-01" ,cli);
    	l=new CMLignedeCommande(c,p,5);
	
	MySQLCategorieDAO.getInstance().create(cat);
	MySQLProduitDAO.getInstance().create(p);
	MySQLClientDAO.getInstance().create(cli);
	MySQLCommandeDAO.getInstance().create(c);
	MySQLLignedeCommandeDAO.getInstance().create(l);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLLignedeCommandeDAO.getInstance().delete(l);
	MySQLCommandeDAO.getInstance().delete(c);
	MySQLProduitDAO.getInstance().delete(p);
	MySQLCategorieDAO.getInstance().delete(cat);
	MySQLClientDAO.getInstance().delete(cli);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
	assertNotNull(l);
 }
	@Test
	public void testGetbyid() throws Exception {
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById2(c.getId(),p.getIdProduit());
		}
	    	catch(Exception e) {
	    	   fail("erreur de getbyid");
	    	}
	}

	@Test
	public void testCreate() throws Exception {
	 cat=new CMCategorie("Watch","watch.png");
    	 p=new CMProduit("aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	cli=new CMClient("Jack","Ma");
    	c=new CMCommande("2020-01-01" ,cli);
    l=new CMLignedeCommande(c,p,6);
		try {
		MySQLLignedeCommandeDAO.getInstance().create(l);
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(l.getQuantite(),6);
		assertEquals(l.getCMCommande().getIdClient().getNom(),"Jack");
		MySQLLignedeCommandeDAO.getInstance().delete(l);
		
	}
	@Test
	public void testDelete() throws Exception {
		  try {
				MySQLLignedeCommandeDAO.getInstance().delete(l);
			}catch(Exception e) {
				fail("Ligne de commande n'a pas était supprimer");
				}	
	}
	
	@Test
	public void testUpdate() throws Exception {
		
		 cat=new CMCategorie("Watch","watch.png");
    	 p=new CMProduit("aaa", "222xx",(float) 1.0,"xxx.png",cat);
    	cli=new CMClient("Jack","Mam");
    	c=new CMCommande("2020-01-01" ,cli);
        l=new CMLignedeCommande(c,p,5);
	 DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().update(l);
		assertEquals(l.getQuantite(),5);
		assertEquals(l.getCMCommande().getIdClient().getNom(),"Jack");
	}

}
