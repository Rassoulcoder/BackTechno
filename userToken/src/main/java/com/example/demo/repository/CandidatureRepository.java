package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.modele.Candidature;
import com.example.demo.modele.User;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	Optional<Candidature> findByIdcandidature(Long id);
	List<Candidature> findByNomcandidature(String nom);
	 List<Candidature> findByUser(User user);
}
