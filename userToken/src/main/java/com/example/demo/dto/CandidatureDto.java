package com.example.demo.dto;

import java.util.List;

import com.example.demo.modele.Annonce;
import com.example.demo.modele.Document;

import jakarta.persistence.Version;

public class CandidatureDto {

	private Long idcandidature;
	
	private String nomcandidature;
	private List<Long> iddocdto;
	private String name;
	private String url;

	private List<Document> documentdto;
	private Long iduserdto;
	private String usernamedto;
	private String titreannoncedto;
	private Annonce annonce;
    //@JsonProperty("idannonce")
	private Long idannonce;
	/*@Version
	private Integer version=0;*/
	public CandidatureDto() {
		super();
	}
	public CandidatureDto(Long idcandidature, String nomcandidature, List<Long> iddocdto, String name, String url,
			List<Document> documentdto, Long iduserdto, String usernamedto, String titreannoncedto, Annonce annonce,
			Long idannonce) {
		super();
		this.nomcandidature = nomcandidature;
		this.iddocdto = iddocdto;
		this.name = name;
		this.url = url;
		this.documentdto = documentdto;
		this.iduserdto = iduserdto;
		this.usernamedto = usernamedto;
		this.titreannoncedto = titreannoncedto;
		this.annonce = annonce;
		this.idannonce = idannonce;
		this.idcandidature= idcandidature;
	}
	public Long getIdcandidature() {
		return idcandidature;
	}
	public void setIdcandidature(Long idcandidature) {
		this.idcandidature = idcandidature;
	}
	public String getNomcandidature() {
		return nomcandidature;
	}
	public void setNomcandidature(String nomcandidature) {
		this.nomcandidature = nomcandidature;
	}
	public List<Long> getIddocdto() {
		return iddocdto;
	}
	public void setIddocdto(List<Long> long1) {
		this.iddocdto = long1;
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
	public List<Document> getDocumentdto() {
		return documentdto;
	}
	public void setDocumentdto(List<Document> documentdto) {
		this.documentdto = documentdto;
	}
	public Long getIduserdto() {
		return iduserdto;
	}
	public void setIduserdto(Long iduserdto) {
		this.iduserdto = iduserdto;
	}
	public String getUsernamedto() {
		return usernamedto;
	}
	public void setUsernamedto(String usernamedto) {
		this.usernamedto = usernamedto;
	}
	public String getTitreannoncedto() {
		return titreannoncedto;
	}
	public void setTitreannoncedto(String titreannoncedto) {
		this.titreannoncedto = titreannoncedto;
	}
	public Annonce getAnnonce() {
		return annonce;
	}
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	public Long getIdannonce() {
		return idannonce;
	}
	public void setIdannonce(Long idannonce) {
		this.idannonce = idannonce;
	}
	
	/*public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}*/
	@Override
	public String toString() {
		return "CandidatureDto [idcandidature=" + idcandidature + ", nomcandidature=" + nomcandidature + ", iddocdto="
				+ iddocdto + ", name=" + name + ", url=" + url + ", documentdto=" + documentdto + ", iduserdto="
				+ iduserdto + ", usernamedto=" + usernamedto + ", titreannoncedto=" + titreannoncedto + ", annonce="
				+ annonce + ", idannonce=" + idannonce + "]";
	}



}
