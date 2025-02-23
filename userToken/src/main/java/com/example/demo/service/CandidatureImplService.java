package com.example.demo.service;

import java.util.ArrayList;

//import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
//import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CandidatureDto;
import com.example.demo.modele.Annonce;
//import com.example.demo.modele.AnneeAcademic;
//import com.example.demo.modele.Annonce;
import com.example.demo.modele.Candidature;
import com.example.demo.modele.Document;
//import com.example.demo.modele.Document;
import com.example.demo.modele.User;
import com.example.demo.repository.AnnoceRepository;
import com.example.demo.repository.CandidatureRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class CandidatureImplService implements candidatureService {
	@Autowired
	UserRepository userep;
	@Autowired
	CandidatureRepository candirepo;
	@Autowired
	AnnoceRepository annorep;
	@Autowired
	DocumentRepository docurepo;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public CandidatureDto saveCandidature (CandidatureDto p) {
		try {      
	           
			Long userId = getAuthenticatedUserId(); // Récupère l'utilisateur connecté
			p.setIduserdto(userId); // Associe l'utilisateur à l'annonce
			return convertEntityToDto(candirepo.save(convertDtoToEntity(p)));
		} catch (OptimisticLockingFailureException e) {
			// Gestion du conflit de version
			throw new RuntimeException("Conflit de mise à jour : candidature a été modifiée par un autre utilisateur.");
		}
	}

	@Override
	public CandidatureDto updateCandidature(CandidatureDto p) {
		try {
			Long userId = getAuthenticatedUserId(); // Récupère l'utilisateur connecté
			p.setIduserdto(userId); // Associe l'utilisateur à l'annonce
			return convertEntityToDto(candirepo.save(convertDtoToEntity(p)));
		} catch (OptimisticLockingFailureException e) {
			// Gestion du conflit de version
			throw new RuntimeException("Conflit de mise à jour : l'annonce a été modifiée par un autre utilisateur.");
		}
	}

	@Override
	public void deleteCandidature(Candidature p) {
		candirepo.delete(p);
	}

	@Override
	public void deleteCandidatureById(Long id) {
		candirepo.deleteById(id);
	}
	@Override
	public List<CandidatureDto> getAllcandidature() {
		return candirepo.findAll().stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	@Override
	public List<CandidatureDto> getAllcandidatureUser() {
		User user = getAuthenticatedUser();
		return candirepo.findByUser(user).stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	@Override
	public CandidatureDto convertEntityToDto(Candidature p) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	   CandidatureDto candidaturedto = modelMapper.map(p, CandidatureDto.class);
	   candidaturedto.setIduserdto(getAuthenticatedUserId());
	   candidaturedto.setDocumentdto(p.getDocuments());
	   candidaturedto.setIdannonce(p.getAnnonce().getIdannnce());
	    if (p.getIdcandidature() == null) {
	        throw new IllegalStateException("ID candidature non généré après la sauvegarde.");
	    }
	    if (p.getAnnonce() == null) {
	        throw new IllegalStateException("Annonce non généré après la sauvegarde.");
	    }
	   

	    return candidaturedto;
	}
	@Override
	public Candidature convertDtoToEntity(CandidatureDto pDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    Candidature candidature = modelMapper.map(pDto, Candidature.class);
       candidature.setIdcandidature(pDto.getIdcandidature());
       candidature.setAnnonce(pDto.getAnnonce());
       candidature.setDocuments(pDto.getDocumentdto());
       if (pDto.getAnnonce() != null) {
           candidature.setAnnonce(pDto.getAnnonce());
       }
	    // Récupération de l'utilisateur et de l'annonce
       if (pDto.getIdcandidature() != null) {
           candidature.setIdcandidature(pDto.getIdcandidature());
       }
	 
	    Optional<User> user = userep.findByUserid(pDto.getIduserdto());
	  
	    if (user.isEmpty()) {
	        throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + pDto.getIduserdto());
	    }

	 
	    if (pDto.getDocumentdto() != null && !pDto.getDocumentdto().isEmpty()) {
	        List<Document> documents = new ArrayList<>();
	        for (Document doc : pDto.getDocumentdto()) {
	            doc.setCandidature1(candidature); // Associer la candidature au document
	            documents.add(docurepo.save(doc)); // Sauvegarde des documents
	        }
	        candidature.setDocuments(documents);
	    }

	    return candidature;
	}

	
	@Override
	public Long getAuthenticatedUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof String username) { 
			User user = userep.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("Utilisateur non trouvé");
			}
			return user.getUserid();
		} else {
			throw new IllegalStateException("Utilisateur non authentifié ou type inconnu");
		}
	}
    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userep.findByUsername(username);
               
    }

	
	

	

}
