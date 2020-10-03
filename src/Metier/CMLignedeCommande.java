package metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMLignedeCommande {
	private int idCommande,idProduit, quantite;
	private double tarifUnitaire;
	
	public CMLignedeCommande(int id) {
		this.setIdCommande(id);
		}
 
	public CMLignedeCommande(int id,int idp) {
		this.setIdCommande(id);
		this.setIdProduit(idp);
		}
 
	
	public CMLignedeCommande(int idProduit, int quantite, double tarifUnitaire) {
		this(-1,idProduit,quantite,tarifUnitaire);
	}
	public CMLignedeCommande(int idCommande,int idProduit, int quantite, double tarifUnitaire) {
		this.setIdCommande(idCommande);
		this.setIdProduit(idProduit);
		this.setQuantite(quantite);
		this.setTarifUnitaire(tarifUnitaire);
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
		if (quantite != other.quantite)
			return false;
		if (Double.doubleToLongBits(tarif_unitaire) != Double.doubleToLongBits(other.tarif_unitaire))
			return false;
		return true;
	}
	
	
	
	
	
	
}
