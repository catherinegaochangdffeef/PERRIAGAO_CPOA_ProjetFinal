package Metier;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import metier.CMLignedeCommande;
  

public class CMCommande {
	private int idCommande, idClient;
	private Date dateCommande;
	private HashMap<CMLignedeCommande,Integer>lignecommande;
		

public CMCommande(LocalDate dateCommande,int idClient) {
	this(-1,dateCommande,idClient);
}
public CMCommande(int idCommande,Date dateCommande,int idClient) {
	this.setId(idCommande);
	this.setDate_commande1(dateCommande);
	this.setIdClient(idClient);
}
public CMCommande(int idCommande,String dateCommande,int idClient) {
	this.setId(idCommande);
	this.setDate_commande2(dateCommande);
	this.setIdClient(idClient);
}

	public CMCommande(int idCommande,LocalDate dateCommande,int idClient) {
		this.setId(idCommande);
		this.setDate_commande3(dateCommande);
		this.setIdClient(idClient);
	}
	/*public CMCommande(int id_commande,LocalDate date_commande,int id_client, HashMap<CMLignedeCommande,Integer> lignecommande) {
		this.setId(id_commande);
		this.setDate_commande3(date_commande);
		this.setId_client(id_client);
	}
*/
	public CMCommande(int id) {
		this.setId(id);
		}
	public void addLignedeCommande (CMLignedeCommande lignecommandes,Integer integer) {
		if(lignecommande==null) {
			lignecommande=new HashMap<>();
		}
		lignecommande.put(lignecommandes,integer);
	}
	public int getId() {
		return idCommande;
	}

	public void setId(int idCmande) {
		this.idCommande = idCommande;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
