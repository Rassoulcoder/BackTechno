package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modele.Candidature;
import com.example.demo.modele.User;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	Optional<Candidature> findByIdcandidature(Long id);
	List<Candidature> findByNomcandidature(String nom);
	 List<Candidature> findByUser(User user);
	 @Query("SELECT c FROM Candidature c WHERE :userid MEMBER OF c.user")
	 List<Candidature> findByUserId(@Param("userid") Long userid);

}
