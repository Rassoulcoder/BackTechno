package com.example.demo.modele;

//import java.sql.Date;

//import java.time.Date;
import java.io.Serializable;
import java.util.List;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
@Entity
public class Annonce implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idannnce;
	private String titre;
	private String description;
	private Date datepubli;
	@OneToMany(mappedBy = "annonce", fetch = FetchType.EAGER)	
	@JsonIgnore
	private List<User> users;
	@OneToMany(mappedBy = "annonce1", fetch = FetchType.EAGER)	
	@JsonIgnore
	private List<Candidature> candidature;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinColumn(name = "idAnnee", nullable = true)
	private AnneeAcademic anneac;
	@Version
	private Integer version= 0 ; 
	@Enumerated(EnumType.STRING)
    private StatutAnnonce statut;
	
	public Annonce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Annonce(String titre, String description, Date datepubli, List<User> users) {
		super();
		this.titre = titre;
		this.description = description;
		this.datepubli = datepubli;
		this.users = users;
	}
	 public enum StatutAnnonce {
	        EN_COURS, 
	        TERMINEE
	    }
	 public StatutAnnonce getStatut() {
			return statut;
		}
		public void setStatut(StatutAnnonce statut) {
			this.statut = statut;
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public AnneeAcademic getAnneac() {
		return anneac;
	}
	public void setAnneac(AnneeAcademic anneac) {
		this.anneac = anneac;
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

	/*public void setAnneac(Optional<AnneeAcademic> anneeac) {
		
	}*/


}
