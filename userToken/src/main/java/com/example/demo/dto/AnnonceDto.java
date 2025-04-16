package com.example.demo.dto;

//import java.sql.Date;
//import java.time.Date;
import java.io.Serializable;
import java.util.Date;

import java.util.List;
//import java.util.Optional;

import com.example.demo.modele.AnneeAcademic;
//import com.example.demo.modele.User;
import com.example.demo.modele.Candidature;
//import com.example.demo.modele.Annonce.StatutAnnonce;
import com.example.demo.modele.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
/*import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;*/
import jakarta.persistence.Version;
public class AnnonceDto  implements Serializable {

	private Long idannnce;
	private String titre;
	private String description;
	private Date datepubli;
	//private User users;
	private Date annedebut;
	private Date annedefin;
	private List<Candidature> candidature;
	@Enumerated(EnumType.STRING)
    private StatutAnnonce statutdto;
	@Version
		private Integer version;
		//private Long idanneac;
		private AnneeAcademic anneeac;
		private List<User> userdto;
		private String usernamedto;


	private Long iduser;

	 public AnnonceDto(Long idannnce, String titre, String description, Date datepubli, Date annedebut, Date annedefin,
			List<Candidature> candidature, StatutAnnonce statutdto, Integer version, AnneeAcademic anneeac,
			List<User> userdto, String usernamedto, Long iduser) {
		super();
		this.idannnce = idannnce;
		this.titre = titre;
		this.description = description;
		this.datepubli = datepubli;
		this.annedebut = annedebut;
		this.annedefin = annedefin;
		this.candidature = candidature;
		this.statutdto = statutdto;
		this.version = version;
		this.anneeac = anneeac;
		this.userdto = userdto;
		this.usernamedto = usernamedto;
		this.iduser = iduser;
	}
	public enum StatutAnnonce {
	        EN_COURS, 
	        TERMINEE
	    }
	 public StatutAnnonce getStatutdto() {
			return statutdto;
		}
		public void setStatutdto(StatutAnnonce statut) {
			this.statutdto = statut;
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
	public List<User> getUserdto() {
		return userdto;
	}
	public void setUserdto(List<User> userdto) {
		this.userdto = userdto;
	}
	public String getUsernamedto() {
		return usernamedto;
	}
	public void setUsernamedto(String usernamedto) {
		this.usernamedto = usernamedto;
	}
	


}
