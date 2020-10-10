package Metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMLignedeCommande {
	private int idCommande,idProduit, quantite;
	private double tarifUnitaire;
	private CMCommande idCommandeCM;
	private CMProduit idProduitCM;
	
	public CMLignedeCommande(int id) {
		this.setIdCommande(id);
		}
 
	public CMLignedeCommande(CMCommande idCommande,CMProduit idProduit) {
		this.setCMCommande(idCommande);
		this.setCMProduit(idProduit);
		}
	
	public CMLignedeCommande(int idCommande,int idProduit, int quantite, double tarifUnitaire) {
		this.setIdCommande(idCommande);
		this.setIdProduit(idProduit);
		this.setQuantite(quantite);
		this.setTarifUnitaire(tarifUnitaire);
	}
	
	public CMLignedeCommande(CMCommande idCommande, CMProduit idProduit, int quantite) {
		this.setCMCommande(idCommande);
		this.setCMProduit(idProduit);
		this.setQuantite(quantite);
	}
	
	
	
	public CMLignedeCommande(int idCommande, int idProduit) {
		this.setIdCommande(idCommande);
		this.setIdProduit(idProduit);
	}

	public CMCommande getCMCommande() {
		return idCommandeCM;
	}
	public void setCMCommande(CMCommande idCommande) {
		this.idCommandeCM=idCommande;
	}
	
	public CMProduit getCMProduit() {
		return idProduitCM;
	}
	public void setCMProduit(CMProduit idProduit) {
		this.idProduitCM=idProduit;
	}
	
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getTarifUnitaire() {
		return tarifUnitaire;
	}
	public void setTarifUnitaire(double tarifUnitaire) {
		this.tarifUnitaire = tarifUnitaire;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMLignedeCommande other = (CMLignedeCommande) obj;
		if (idCommande != other.idCommande)
			return false;
		if (idProduit != other.idProduit)
			return false;
		return true;
	}
	
	
	
	
	
	
}
