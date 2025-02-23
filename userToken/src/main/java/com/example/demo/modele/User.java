package com.example.demo.modele;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class User {
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long userid;
@Column(unique=true)
private String username;
private String nom;
private String prenom;
@Column(unique=true)
private Integer Telephone;
private String password;
private Boolean enabled;
private String email;
@OneToMany(mappedBy = "user")
private List<Notification> notif;
@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
@JoinTable(name="candidat_candidature",joinColumns = @JoinColumn(name="userid"),
inverseJoinColumns = @JoinColumn(name="idcandidature"))
private Candidature candidature;
@ManyToOne
@JoinTable(name="candidat_annonce",joinColumns = @JoinColumn(name="userid"),
inverseJoinColumns = @JoinColumn(name="idannnce"))
private Annonce annonce;
 @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
@JoinTable(name="user_role",joinColumns = @JoinColumn(name="userid") ,
inverseJoinColumns = @JoinColumn(name="role_id"))
private List<Role> roles;

 
public User() {
	super();
	// TODO Auto-generated constructor stub
}

public User(String username, String nom, String prenom, Integer telephone, String password, Boolean enabled,
		String email, List<Role> roles) {
	super();
	this.username = username;
	this.nom = nom;
	this.prenom = prenom;
	Telephone = telephone;
	this.password = password;
	this.enabled = enabled;
	this.email = email;
	this.roles = roles;
}

public Long getUserid() {
	return userid;
}
public void setUserid(Long user_id) {
	this.userid = user_id;
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
public Boolean getEnabled() {
	return enabled;
}
public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
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

