package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AnnonceDto;
import com.example.demo.modele.AnneeAcademic;
import com.example.demo.modele.Annonce;

public interface annonceService {
	AnnonceDto saveAnnonce(AnnonceDto p);
	AnnonceDto updateAnnonce(AnnonceDto p);
	void deleteAnnonce(Annonce p);
	void deleteAnnonceById(Long id);
	AnnonceDto getAnnonce(Long id);
	List<AnnonceDto> getAllannonce();
	List<Annonce> findByTitre(String nom);
	List<Annonce> findByTitreContains(String nom);
	List<Annonce> findByAnneeAcademic (AnneeAcademic annee);
	List<Annonce> findByAnneacIdAnnee (Long id);
	List<Annonce> findByOrderByTitreAsc();
	AnnonceDto convertEntityToDto(Annonce p);
	Annonce convertDtoToEntity(AnnonceDto pDto);
	Long getAuthenticatedUserId();

}
