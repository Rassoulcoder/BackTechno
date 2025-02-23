package com.example.demo.service.register;


public class RegistrationRequest {
	private String username;
	private String password;
	private String email;
	private String nom;
	private String prenom;
	private Integer Telephone;


	public RegistrationRequest(String username, String password, String email, String nom, String prenom,
			Integer telephone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		Telephone = telephone;
	}
	public RegistrationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Integer getTelephone() {
		return Telephone;
	}
	public void setTelephone(Integer telephone) {
		Telephone = telephone;
	}



}
