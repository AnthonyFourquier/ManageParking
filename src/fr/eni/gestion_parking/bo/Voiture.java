package fr.eni.gestion_parking.bo;

public class Voiture {

	private Integer id;
	private String nom;
	private String plaqueImmatriculation	;
	private Personne personne;
	

	
	public Voiture(Integer id, String nom, String plaqueImmatriculation,Personne personne) {
		this.id = id;
		this.nom = nom;
		this.plaqueImmatriculation = plaqueImmatriculation;
	}
	
	public Voiture(Integer id, String nom, String plaqueImmatriculation) {
		this.id = id;
		this.nom = nom;
		this.plaqueImmatriculation = plaqueImmatriculation;
	}
	
	public Voiture(String nom, String plaqueImmatriculation,Personne personne) {
		this.nom = nom;
		this.plaqueImmatriculation = plaqueImmatriculation;
		this.personne = personne;
	}
	
	public Voiture(String nom, String plaqueImmatriculation) {
		this.nom = nom;
		this.plaqueImmatriculation = plaqueImmatriculation;
	}
	
	public Voiture() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPlaqueImmatriculation() {
		return plaqueImmatriculation;
	}

	public void setPlaqueImmatriculation(String plaqueImmatriculation) {
		this.plaqueImmatriculation = plaqueImmatriculation;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	

	@Override
	public String toString() {
		return "Voiture [id=" + id + ", nom=" + nom + ", plaqueImmatriculation=" + plaqueImmatriculation + ", personne=" + personne + "]";
	}


	
	

}
