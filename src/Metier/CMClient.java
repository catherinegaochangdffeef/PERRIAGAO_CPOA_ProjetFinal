package Metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMClient {
private int idClient;
private String nom, prenom,identifiant,motDePasse,adrNumero,adrVoie,adrCodePostal,ville,pays;


/*
public CMClient(String nom,String prenom) {
	this(-1,nom,prenom);
}
	*/
public CMClient(String nom,String prenom,String identifiant,String motDePasse, String adrNumero,String adrVoie,String adrCodePostal,String ville, String pays) {
	this(-1,nom, prenom,identifiant,motDePasse,adrNumero,adrVoie,adrCodePostal,ville,pays);
}
	public CMClient(int idClient) {
		this.setIdClient(idClient);
		}


	public CMClient(int idClient,String nom,String prenom,String identifiant,String motDePasse, String adrNumero,String adrVoie,String adrCodePostal,String ville, String pays) {
		this.setIdClient(idClient);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMotDePasse(motDePasse);
		this.setAdrNumero(adrNumero);
		this.setAdrVoie(adrVoie);
		this.setAdrCodePostal(adrCodePostal);
		this.setVille(ville);
		this.setPays(pays);
	
	}

	
	public CMClient(int idClient,String nom,String prenom) {
		this.setIdClient(idClient);
		this.setNom(nom);
		this.setPrenom(prenom);
		
		
	
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

	public String getAdrNumero() {
		return adrNumero;
	}

	public void setAdrNumero(String adrNumero) {
		this.adrNumero = adrNumero;
	}

	public String getAdrVoie() {
		return adrVoie;
	}

	public void setAdrVoie(String adrVoie) {
		this.adrVoie = adrVoie;
	}

	public String getAdrCodePostal() {
		return adrCodePostal;
	}

	public void setAdrCodePostal(String adrCodePostal) {
		this.adrCodePostal = adrCodePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	
	

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + idClient;
	return result;
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
	if (idClient != other.idClient)
		return false;
	return true;
}



	
	
	
}
