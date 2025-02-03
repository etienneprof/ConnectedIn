package bo;

import java.time.LocalDate;

public class Contact {
	private int id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String telephone;
	private String url;
	private String poste;
	private String specialite;
	
	public Contact(int id, String nom, String prenom, LocalDate dateNaissance, String telephone, String url,
			String poste, String specialite) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.url = url;
		this.poste = poste;
		this.specialite = specialite;
	}

	public Contact(String nom, String prenom, LocalDate dateNaissance, String telephone, String url, String poste,
			String specialite) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.url = url;
		this.poste = poste;
		this.specialite = specialite;
	}

	public Contact() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
}
