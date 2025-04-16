package com.example.demo.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

//import com.example.demo.dto.AnnonceDto;
import com.example.demo.modele.AnneeAcademic;
import com.example.demo.modele.Annonce;
//import com.example.demo.modele.User;

public interface AnnoceRepository extends JpaRepository<Annonce, Long> {
	List<Annonce> findByTitre(String titre);
	List<Annonce> findByTitreContains(String nom);
	List<Annonce> findByAnneac(AnneeAcademic anneac);
	List<Annonce> findByAnneacIdAnnee(Long idAnnee);
	List<Annonce> findByOrderByTitreAsc();
	//List<User> findByUsersUser_id(Long id);
	Annonce findByIdannnce(Long idannnce);
	

}
