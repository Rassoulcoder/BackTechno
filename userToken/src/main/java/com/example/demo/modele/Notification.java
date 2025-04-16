package com.example.demo.modele;

//import java.sql.Date;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Notification implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idnotif;
	private String destinataire;
	private String objet;
	private String contenu;
	private LocalDate dateenvoi;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private User user;

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(String destinataire, String objet, String contenu, LocalDate dateenvoi, User user) {
		super();
		this.destinataire = destinataire;
		this.objet = objet;
		this.contenu = contenu;
		this.dateenvoi = dateenvoi;
		this.user = user;
	}

	public Long getIdnotif() {
		return idnotif;
	}

	public void setIdnotif(Long idnotif) {
		this.idnotif = idnotif;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDate getDateenvoi() {
		return dateenvoi;
	}

	public void setDateenvoi(LocalDate dateenvoi) {
		this.dateenvoi = dateenvoi;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}




}
