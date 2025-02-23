package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.AnneeAcademic;

public interface AnneeRepository extends JpaRepository<AnneeAcademic, Long> {
	List<AnneeAcademic> findByDatedebut(Date anneac);
	Optional<AnneeAcademic> findByIdAnnee(Long idAnnee);
	List<AnneeAcademic> findByOrderByDatedebutAsc();
}
