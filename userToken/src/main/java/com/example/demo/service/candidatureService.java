package com.example.demo.service;

import java.util.List;
import com.example.demo.dto.CandidatureDto;
import com.example.demo.modele.Candidature;

public interface candidatureService {
	
	CandidatureDto saveCandidature(CandidatureDto p);
	CandidatureDto updateCandidature(CandidatureDto p);
	void deleteCandidature(Candidature p);
	void deleteCandidatureById(Long id);
	CandidatureDto convertEntityToDto(Candidature p);
	Candidature convertDtoToEntity(CandidatureDto pDto);
	public List<CandidatureDto> getAllcandidature();
	public List<CandidatureDto> getAllcandidatureUser();
	Long getAuthenticatedUserId();
}
