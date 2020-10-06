package Metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMClient {
private int idClient;
private String nom, prenom;



public CMClient(String nom,String prenom) {
	this(-1,nom,prenom);
}
	

	public CMClient(int idClient) {
		this.setIdClient(idClient);
		}


	public CMClient(int idClient, String nom, String prenom) {
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
