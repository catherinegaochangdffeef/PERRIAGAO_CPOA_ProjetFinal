package Metier;


public class CMCategorie {
	private int idCategorie;
	private String titre,visuel;
	
	public CMCategorie (String titre,String visuel)
	{
		this(-1,titre,visuel);
	}

	public CMCategorie(int idCategorie,String titre,String visuel)
	{
		this.setId(idCategorie);
		this.setTitre(titre);
		this.setVisuel(visuel);
	}
public CMCategorie(int id) {
	this.setId(id);
	}

	//-------------------------------------------------	
	public int getId() 
	{
		return this.idCategorie;
	}
	public void setId(int id_categorie)
	{
		this.idCategorie=id_categorie;
	}
//------------------------------------------------	
	public String getTitre()
	{
		return this.titre;
	}
	public void setTitre(String titre)
	{   
		/*if(titre==null|| titre.trim().length()==0)
		{
			throw new IllegalArgumentException("Titre de la catégorie!");
		}*/
		this.titre=titre;
	}
//-------------------------------------------------
	public String getVisuel()
	{
		return this.visuel;
	}
	public void setVisuel(String visuel)
	{
		this.visuel=visuel;
	}
//--------------------------------------------------
	
	public boolean equals(Object objet) {
	    if(objet == null) return false;
	    if(this == objet) return true;
	    if(getClass() != objet.getClass()) return false;
	    CMCategorie cat = (CMCategorie) objet;
	    return this.getId() == cat.getId();}
	
	
	
}
