package metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMClient {
private int idClient,adrNumero,adrCodePostal;
private String nom, prenom, identifiant,motDePasse,adrVoie, adrVille,adrPays;

	public CMClient(String nom, String prenom, String identifiant,String motDePasse,int adrNumero,String adrVoie,int adrCodePostal,String adrVille,String adrPays)
	{
		this(-1,nom, prenom, identifiant,motDePasse,adrNumero,adrVoie,adrCodePostal, adrVille,adrPays);
	}

	
	public CMClient(int id_client,String nom, String prenom, String identifiant,String motDePasse,int adrNumero,String adrVoie,int adrCodePostal,String adrVille,String adrPays) {
		this.setIdClient(id_client);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMotDePasse(motDePasse);
		this.setAdrNumero(adrNumero);
		this.setAdrVoie(adrVoie);
		this.setAdrCodePostal(adrCodePostal);
		this.setAdrVille(adrVille);
		this.setAdrPays(adrPays);
	}

	public CMClient(int idClient) {
		this.setIdClient(idClient);
		}


	public int getIdClient() {
		return idClient;
	}


	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public int getAdrNumero() {
		return adrNumero;
	}


	public void setAdrNumero(int adrNumero) {
		this.adrNumero = adrNumero;
	}


	public String getAdrVoie() {
		return adrVoie;
	}


	public void setAdrVoie(String adrVoie) {
		this.adrVoie = adrVoie;
	}


	public int getAdrCodePostal() {
		return adrCodePostal;
	}


	public void setAdrCodePostal(int adrCodePostal) {
		this.adrCodePostal = adrCodePostal;
	}


	public String getAdrVille() {
		return adrVille;
	}


	public void setAdrVille(String adrVille) {
		this.adrVille = adrVille;
	}


	public String getAdrPays() {
		return adrPays;
	}


	public void setAdrPays(String adrPays) {
		this.adrPays = adrPays;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMClient other = (CMClient) obj;
		if (adrCodePostal != other.adrCodePostal)
			return false;
		if (adrNumero != other.adrNumero)
			return false;
		if (adrPays == null) {
			if (other.adrPays != null)
				return false;
		} else if (!adrPays.equals(other.adrPays))
			return false;
		if (adrVille == null) {
			if (other.adrVille != null)
				return false;
		} else if (!adrVille.equals(other.adrVille))
			return false;
		if (adrVoie == null) {
			if (other.adrVoie != null)
				return false;
		} else if (!adrVoie.equals(other.adrVoie))
			return false;
		if (idClient != other.idClient)
			return false;
		if (identifiant == null) {
			if (other.identifiant != null)
				return false;
		} else if (!identifiant.equals(other.identifiant))
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}




	
	
	
}
