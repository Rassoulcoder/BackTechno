package com.example.demo.modele;

//import java.sql.Date;

//import java.time.Date;
import java.io.Serializable;
import java.util.List;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class AnneeAcademic implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAnnee;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datedebut;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datedefin;
	@OneToMany(mappedBy="anneac")
	@JsonIgnore
	private List<Annonce> annonce;
	public AnneeAcademic() {
		super();
	}
	public AnneeAcademic(Date datedebut, Date datedefin, List<Annonce> annonce) {
		super();
		this.datedebut = datedebut;
		this.datedefin = datedefin;
		this.annonce = annonce;
	}
	public Long getIdannee() {
		return idAnnee;
	}
	public void setIdannee(Long idannee) {
		this.idAnnee = idannee;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public Date getDatedefin() {
		return datedefin;
	}
	public void setDatedefin(Date datedefin) {
		this.datedefin = datedefin;
	}
	public List<Annonce> getAnnonce() {
		return annonce;
	}
	public void setAnnonce(List<Annonce> annonce) {
		this.annonce = annonce;
	}
	@Override
	public String toString() {
		return "AnneeAcademic [idannee=" + idAnnee + ", datedebut=" + datedebut + ", datedefin=" + datedefin + ", annonce="
				+ annonce + "]";
	}



}
