package com.example.demo.service;

//import java.time.LocalDate;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.example.demo.modele.AnneeAcademic;

public interface anneeService {
	AnneeAcademic saveAnnee(AnneeAcademic p);
	AnneeAcademic updatesaveAnnee(AnneeAcademic p);
	List<AnneeAcademic> findByDatedebut1(Date anneac);
	Optional<AnneeAcademic> findByIdAnnee(Long idAnnee);
	List<AnneeAcademic> findByOrderByDatedebutAsc();
	List<AnneeAcademic> getAllannee();
	void deleteAnneeac(AnneeAcademic p);
	void deleteAnneeacById(Long id);
	List<AnneeAcademic> findByDatedebut(Date anneac);
}
