package com.example.demo.service;

import java.util.Collections;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
//import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
//import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AnnonceDto;
import com.example.demo.dto.CandidatureDto;
import com.example.demo.dto.AnnonceDto.StatutAnnonce;
import com.example.demo.modele.AnneeAcademic;
import com.example.demo.modele.Annonce;
import com.example.demo.modele.User;
import com.example.demo.repository.AnneeRepository;

//import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.repository.AnnoceRepository;
import com.example.demo.repository.UserRepository;
//import com.example.demo.security.CustumDetail;

@Transactional
@Service
public class Annoceimplservice implements annonceService {
	@Autowired
	AnnoceRepository annoncerepo;

	@Autowired
	AnneeRepository anneerep;

	@Autowired
	anneeService anneeser;
    
	@Autowired
	UserRepository userep;
	
   @Autowired
	ModelMapper modelMapper;
	@Override
	public AnnonceDto saveAnnonce(AnnonceDto p) {
		

		try {
			Long userId = getAuthenticatedUserId(); // Récupère l'utilisateur connecté
		        p.setIduser(userId); // Associe l'utilisateur à l'annonce
			return convertEntityToDto(annoncerepo.save(convertDtoToEntity(p)));
		} catch (OptimisticLockingFailureException e) {
			// Gestion du conflit de version
			throw new RuntimeException("");
		}
	}

	@Override
	public AnnonceDto updateAnnonce(AnnonceDto p) {
		try {
			Long userId = getAuthenticatedUserId(); // Récupère l'utilisateur connecté
		        p.setIduser(userId); // Associe l'utilisateur à l'annonce
			return convertEntityToDto(annoncerepo.save(convertDtoToEntity(p)));
		} catch (OptimisticLockingFailureException e) {
			// Gestion du conflit de version
			throw new RuntimeException("");
		}	}

	@Override
	public void deleteAnnonce(Annonce p) {

		annoncerepo.delete(p);

	}

	@Override
	public void deleteAnnonceById(Long id) {

		annoncerepo.deleteById(id);		

	}

	@Override
	public AnnonceDto getAnnonce(Long id) {

		return convertEntityToDto(annoncerepo.findById(id).get());

	}

	@Override
	public List<AnnonceDto> getAllannonce() {

		return annoncerepo.findAll().stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());

	}

	@Override
	public List<Annonce> findByTitre(String nom) {

		return annoncerepo.findByTitre(nom);

	}

	@Override
	public List<Annonce> findByTitreContains(String nom) {

		// TODO Auto-generated method stub
		return annoncerepo.findByTitreContains(nom);
	}

	@Override
	public List<Annonce> findByAnneeAcademic(AnneeAcademic annee) {

		// TODO Auto-generated method stub
		return annoncerepo.findByAnneac(annee);
	}

	@Override
	public List<Annonce> findByAnneacIdAnnee(Long id) {

		return annoncerepo.findByAnneacIdAnnee(id);
	}

	@Override
	public List<Annonce> findByOrderByTitreAsc() {


		return annoncerepo.findByOrderByTitreAsc();
	}

	@Override
	public AnnonceDto convertEntityToDto(Annonce p) 
{  		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		AnnonceDto annoncedto = modelMapper.map(p, AnnonceDto.class);;
        annoncedto.setDatepubli(p.getDatepubli());
        annoncedto.setTitre(p.getTitre());
        annoncedto.setDescription(p.getDescription());
        annoncedto.setIdannnce(p.getIdannnce());
        annoncedto.setIduser(getAuthenticatedUserId());
        annoncedto.setAnnedebut(p.getAnneac().getDatedebut());
        annoncedto.setAnnedefin(p.getAnneac().getDatedefin());
        annoncedto.setAnneeac(p.getAnneac());
       annoncedto.setCandidature(p.getCandidature());
       annoncedto.setStatutdto(StatutAnnonce.valueOf(p.getStatut().toString()));
      

        if (p.getAnneac() != null) {
            annoncedto.setAnnedebut(p.getAnneac().getDatedebut());
        } else {
            annoncedto.setAnnedebut(p.getAnneac().getDatedebut()); // ou une valeur par défaut
        }        



		return annoncedto;
	}



	@Override
	public Annonce convertDtoToEntity(AnnonceDto pDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Annonce annonce =modelMapper.map(pDto, Annonce.class);
		annonce.setDatepubli(pDto.getDatepubli());
		annonce.setDescription(pDto.getDescription());
		annonce.setIdannnce(pDto.getIdannnce());
		annonce.setTitre(pDto.getTitre());
		annonce.setAnneac(pDto.getAnneeac());
		annonce.setCandidature(pDto.getCandidature());
	    annonce.setStatut(com.example.demo.modele.Annonce.StatutAnnonce.valueOf(pDto.getStatutdto().toString()));

		Optional<User> user = userep.findByUserid(pDto.getIduser());
		if (user.isEmpty()) {
			throw new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + pDto.getIduser());
		}

		annonce.setUsers(Collections.singletonList(user.get()));
		List<AnneeAcademic> anneeAcademic =  anneerep.findByDatedebut(pDto.getAnnedebut());
		if (anneeAcademic!= null) {
			AnneeAcademic anne = new AnneeAcademic();
			anne.setDatedebut(pDto.getAnnedebut());
			anne.setDatedefin(pDto.getAnnedefin());
			annonce.setAnneac(anne);
		} else {
			throw new IllegalArgumentException("L'année académique est obligatoire pour l'annonce.");
		}
		AnneeAcademic anneeAcade= annonce.getAnneac();
		anneeser.saveAnnee(anneeAcade);
		annonce.setAnneac(anneeAcade);
		
		return annonce;
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





}
