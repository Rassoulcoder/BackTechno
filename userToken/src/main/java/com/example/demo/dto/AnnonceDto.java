package com.example.demo.dto;

//import java.sql.Date;
//import java.time.Date;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.modele.AnneeAcademic;
//import com.example.demo.modele.User;
import com.example.demo.modele.Candidature;

/*import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;*/
import jakarta.persistence.Version;
public class AnnonceDto {

	private Long idannnce;
	private String titre;
	private String description;
	private Date datepubli;
	//private User users;
	private Date annedebut;
	private Date annedefin;
	private List<Candidature> candidature;

	@Version
		private Integer version;
		//private Long idanneac;
		private AnneeAcademic anneeac;



	private Long iduser;
	public AnnonceDto(String titre, String description, Date datepubli, /*List<User> users/Long idanneac,*/Long iduse,Date annedebut, Date annedefin,/*User users,*/ AnneeAcademic anneeac,List<Candidature> candidature) {
		super();
		this.titre = titre;
		this.description = description;
		this.datepubli = datepubli;
		//this.users = users;
		//this.idanneac = idanneac;
		this.annedebut = annedebut;
		this.iduser = iduse;
		this.anneeac = anneeac;
		this.setAnnedefin(annedefin);
		this.candidature=candidature;
	}
	public AnnonceDto() {
		super();
	}
	public Long getIdannnce() {
		return idannnce;
	}
	public void setIdannnce(Long idannnce) {
		this.idannnce = idannnce;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDatepubli() {
		return datepubli;
	}
	public void setDatepubli(Date datepubli) {
		this.datepubli = datepubli;
	}
	public Date getAnnedebut() {
		return annedebut;
	}
	public void setAnnedebut(Date annedebut) {
		this.annedebut = annedebut;
	}
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	/*public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	/*public Long getIdanneac() {
		return idanneac;
	}
	public void setIdanneac(Long idanneac) {
		this.idanneac = idanneac;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}*/
	public AnneeAcademic getAnneeac() {
		return anneeac;
	}
	public void setAnneeac(AnneeAcademic anneeac) {
		this.anneeac = anneeac;
	}
	public Date getAnnedefin() {
		return annedefin;
	}
	public void setAnnedefin(Date annedefin) {
		this.annedefin = annedefin;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public List<Candidature> getCandidature() {
		return candidature;
	}
	public void setCandidature(List<Candidature> candidature) {
		this.candidature = candidature;
	}
	


}
