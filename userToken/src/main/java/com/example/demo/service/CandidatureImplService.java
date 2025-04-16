package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;

//import java.util.ArrayList;

//import java.util.Collections;
import java.util.List;
//import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
//import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
//import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CandidatureDto;
import com.example.demo.modele.Annonce;
//import com.example.demo.modele.Annonce;
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
		return candirepo.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	@Override
	public List<Candidature> getAll(){
		return candirepo.findAll();
	}
	@Override
	public List<CandidatureDto> getAllcandidatureUser() {
		  User user = userep.findById(getAuthenticatedUserId())
				 
                  .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
return candirepo.findByUser(user).stream()
        .map(this::convertEntityToDto)
        .collect(Collectors.toList());
	}
	public List<CandidatureDto> getAllcandidatureUser1() {
		  User user = userep.findById(getAuthenticatedUserId())
		
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
return candirepo.findByUser(user).stream()
      .map(this::convertEntityToDto)
      .collect(Collectors.toList());
	}
	@Override
	 public List<Candidature> getCandidaturesByConnectedUser() {
	        Long connectedUserId = getAuthenticatedUserId(); // Récupérer l'ID de l'utilisateur connecté

	        // Récupérer toutes les candidatures
	        List<Candidature> candidatures = candirepo.findAll(); 

	        // Filtrer les candidatures où l'utilisateur connecté fait partie de la liste 'user' de la candidature
	        return candidatures.stream()
	                .filter(candidature -> candidature.getUser().stream()
	                        .anyMatch(user -> user.getUserid().equals(connectedUserId))) // Vérifier si l'utilisateur connecté est dans la liste des utilisateurs
	                .collect(Collectors.toList());
	    }
	@Override
	public CandidatureDto convertEntityToDto(Candidature p) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	   CandidatureDto candidaturedto = modelMapper.map(p, CandidatureDto.class);
	   candidaturedto.setIduserdto(getAuthenticatedUserId());
	   candidaturedto.setDocumentdto(p.getDocuments());
	   //candidaturedto.setIddocdto(p.getDocuments().get(0).getIddoc());
	   candidaturedto.setIdannonce(p.getAnnonce().getIdannnce());
	   candidaturedto.setUserdto(p.getUser());
	   candidaturedto.setUsernamedto(p.getUser().get(0).getUsername());
	   // Récupération des ID des documents
	    if (p.getDocuments() != null && !p.getDocuments().isEmpty()) {
	        List<Long> idDocList = p.getDocuments().stream()
	                .map(Document::getIddoc)
	                .collect(Collectors.toList());
	        candidaturedto.setIddocdto(idDocList);
	    } else {
	        candidaturedto.setIddocdto(new ArrayList<>()); // Éviter les `null`
	    }
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
       //candidature.setAnnonce(pDto.getAnnonce());
       candidature.setDocuments(pDto.getDocumentdto());
       candidature.setUser(pDto.getUserdto());
       Optional<Annonce> annonce = annorep.findById(pDto.getIdannonce());
       if (annonce.isEmpty()) {
	        throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + pDto.getIdannonce());
	    }
       candidature.setAnnonce(annonce.get());  // Associer l'annonce à la candidature
	    // Récupération de l'utilisateur et de l'annonce
       if (pDto.getIdcandidature() != null) {
           candidature.setIdcandidature(pDto.getIdcandidature());
       }
	   User users = userep.findByUsername(pDto.getUsernamedto());
	    Optional<User> user = userep.findByUserid(pDto.getIduserdto());
	  
	    if (user.isEmpty()) {
	        throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + pDto.getIduserdto());
	    }
	    if (users == null) {
	        throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + pDto.getUsernamedto());
	    }

     
		candidature.setUser(Collections.singletonList(user.get()));
	    // Récupération des documents selon les ID fournis
		 if (pDto.getIddocdto() != null && !pDto.getIddocdto().isEmpty()) {
		        List<Document> documents = docurepo.findAllById(pDto.getIddocdto());
		        candidature.setDocuments(documents);
		    } else {
		        candidature.setDocuments(new ArrayList<>()); // Éviter les `null`
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
    public User getCurrentUser() {
        // Récupérer l'utilisateur connecté à partir du contexte de sécurité
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // Ou tu peux utiliser email ou autre attribut unique
            return userep.findByUsername(username); // Recherche de l'utilisateur dans la base
        }
        throw new RuntimeException("Aucun utilisateur connecté.");
    }

	
	

	

}
