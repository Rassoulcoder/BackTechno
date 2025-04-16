package com.example.demo.modele;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.io.Serializable;

@Entity
public class Document implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iddoc;
	
	private String name;
	private String url;
	@Version
	private Integer version;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.MERGE}, fetch = FetchType.EAGER) 
	private Candidature candidature1;
	public Document() {
		super();
	}
	public Document(String name, String url, Candidature candidature1) {
		super();
		this.name = name;
		this.url = url;
		this.candidature1 = candidature1;
	}
	public Long getIddoc() {
		return iddoc;
	}
	public void setIddoc(Long iddoc) {
		this.iddoc = iddoc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Candidature getCandidature1() {
		return candidature1;
	}
	public void setCandidature1(Candidature candidature1) {
		this.candidature1 = candidature1;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	


}
