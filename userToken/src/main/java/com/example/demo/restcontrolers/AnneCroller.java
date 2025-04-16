package com.example.demo.restcontrolers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modele.AnneeAcademic;
import com.example.demo.service.anneeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/annee")
public class AnneCroller {
	@Autowired
	anneeService anneeservice;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public AnneeAcademic creationAnnee(@RequestBody AnneeAcademic anneeac)
	{
		return anneeservice.saveAnnee(anneeac);
	}
	@GetMapping
	public List<AnneeAcademic> getAllAnnee(){
		return anneeservice.getAllannee();
	}
	@PutMapping("/updateannee")	
	public AnneeAcademic updateAnnee(@RequestBody AnneeAcademic annee )
	{
		return anneeservice.updatesaveAnnee(annee);
	}
	@DeleteMapping("/delanne/{id}")
	public void deleteAnnee(@PathVariable("id") Long id)
	{
		anneeservice.deleteAnneeacById(id);
	}
	/*@DeleteMapping("/delanne")
	 public void deleteAnneee(@PathVariable("anneac") AnneeAcademic anneac)
	 {
		 anneeservice.deleteAnneeac(anneac);
	 }*/

}
