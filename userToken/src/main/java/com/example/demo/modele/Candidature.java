package com.example.demo.modele;

import java.io.Serializable;
import java.util.List;

//import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.Version;
//import jakarta.persistence.OneToMany;

@Entity
public class Candidature implements Serializable {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idcandidature;
	
	private String nomcandidature;
	@OneToMany(mappedBy="candidature")
	@JsonIgnore
	private List<User> user;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.MERGE})
	@JoinColumn(name = "idannnce", nullable = false)
	private Annonce annonce1;
	@OneToMany(mappedBy = "candidature1" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) 
	@JsonIgnore
	private List<Document> documents;
	/*@Version
	private Integer version=0;*/
	public Candidature() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Candidature(Long idcandidature,String nomcandidature, List<User> user, Annonce annonce, List<Document> documents) {
		super();
		this.nomcandidature = nomcandidature;
		this.user = user;
		this.annonce1 = annonce;
		this.documents = documents;
		this.idcandidature=idcandidature;
	}
	public String getNomcandidature() {
		return nomcandidature;
	}
	public void setNomcandidature(String nomcandidature) {
		this.nomcandidature = nomcandidature;
	}
	public Long getIdcandidature() {
		return idcandidature;
	}
	public void setIdcandidature(Long idcandidature) {
		this.idcandidature = idcandidature;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public Annonce getAnnonce() {
		return annonce1;
	}
	public void setAnnonce(Annonce annonce) {
		this.annonce1 = annonce;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	/*public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	*/



}
