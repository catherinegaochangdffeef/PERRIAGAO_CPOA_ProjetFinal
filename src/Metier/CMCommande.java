package Metier;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;




  

public class CMCommande {
	private int idCommande;
	private Date dateCommande;
	private HashMap<CMProduit,Integer> products;
	private CMClient idClient;	
	int idClient2;
/*
public CMCommande(LocalDate dateCommande,int idClient) {
	this(-1,dateCommande,idClient);
}
*/
	
	
	
public CMCommande(int idCommande,Date dateCommande,int idclient) {
	this.setId(idCommande);
	this.setDate_commande1(dateCommande);
	this.setIdClient2(idclient);	
}
public CMCommande(int idCommande,LocalDate dateCommande,int idclient) {
	this.setId(idCommande);
	this.setDate_commande3(dateCommande);
	this.setIdClient2(idclient);	
}
public CMCommande(int idCommande,String dateCommande,CMClient idClient,HashMap<CMProduit, Integer> products) {
	this.setId(idCommande);
	this.setDate_commande2(dateCommande);
	this.setIdClient(idClient);
	this.setProducts(products);
}

	public CMCommande(int idCommande,LocalDate dateCommande,CMClient idClient,HashMap<CMProduit, Integer> products) {
		this.setId(idCommande);
		this.setDate_commande3(dateCommande);
		this.setIdClient(idClient);
		this.setProducts(products);
	}

	public CMCommande(int id) {
		this.setId(id);
		}
	public CMCommande(LocalDate dateCommande, CMClient idClient, HashMap<CMProduit, Integer> products) {
		this(-1,dateCommande,idClient,products);
		
	}
	public void addProducts(CMProduit product, Integer quantity) {
		if(products==null) {
			products=new HashMap<>();
		}
		products.put(product,quantity);
	}
	public void removeProducts(CMProduit product)
	{
		products.remove(product);
	}
	public int getId() {
		return idCommande;
	}

	public void setId(int idCommande) {
		this.idCommande = idCommande;
	}

	public CMClient getIdClient() {
		return idClient;
	}

	public void setIdClient(CMClient idClient) {
		this.idClient = idClient;
	}
	public int getIdClient2() {
		return idClient2;
	}
	public void setIdClient2(int idclient) {
		this.idClient=idClient;
	}
	public HashMap<CMProduit, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<CMProduit, Integer> products) {
		this.products = products;
	}
	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDate_commande1(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public void setDate_commande2(String dateCommande) {
		this.dateCommande = java.sql.Date.valueOf(dateCommande);
	}
	public void setDate_commande3(LocalDate dateCommande) {
		this.dateCommande = java.sql.Date.valueOf(dateCommande);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMCommande other = (CMCommande) obj;
		if (dateCommande == null) {
			if (other.dateCommande != null)
				return false;
		} else if (!dateCommande.equals(other.dateCommande))
			return false;
		if (idClient != other.idClient)
			return false;
		if (idCommande != other.idCommande)
			return false;
		return true;
	}
	
	
}
