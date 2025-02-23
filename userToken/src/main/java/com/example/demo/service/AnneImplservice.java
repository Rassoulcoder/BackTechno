package com.example.demo.service;

//import java.time.Date;
import java.util.Date;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modele.AnneeAcademic;
import com.example.demo.repository.AnneeRepository;

@Transactional
@Service
public class AnneImplservice implements anneeService {
	@Autowired
	AnneeRepository anneerepo;
	@Override
	public AnneeAcademic saveAnnee(AnneeAcademic p) {
		return anneerepo.save(p);
	}

	@Override
	public AnneeAcademic updatesaveAnnee(AnneeAcademic p) {
		return anneerepo.save(p);
	}

	@Override
	public List<AnneeAcademic> findByDatedebut1(Date anneac) {
		return anneerepo.findByDatedebut(anneac);
	}

	@Override
	public Optional<AnneeAcademic> findByIdAnnee(Long idAnnee) {
		// TODO Auto-generated method stub
		return anneerepo.findByIdAnnee(idAnnee);
	}

	@Override
	public List<AnneeAcademic> findByOrderByDatedebutAsc() {
		return anneerepo.findByOrderByDatedebutAsc();
	}

	@Override
	public void deleteAnneeac(AnneeAcademic p) {
		anneerepo.delete(p);
	}

	@Override
	public void deleteAnneeacById(Long id) {
		anneerepo.deleteById(id);
	}

	@Override
	public List<AnneeAcademic> getAllannee() {
		// TODO Auto-generated method stub
		return anneerepo.findAll();
	}

	@Override
	public List<AnneeAcademic> findByDatedebut(Date anneac) {
		// TODO Auto-generated method stub
		return null;
	}


}
