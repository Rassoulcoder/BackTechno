package com.example.demo.restcontrolers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AnnonceDto;
//import com.example.demo.modele.AnneeAcademic;
//import com.example.demo.modele.Annonce;
import com.example.demo.service.anneeService;
import com.example.demo.service.annonceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/anonnce")
public class AnnonceRestController {
	@Autowired
	annonceService annonceservice;

	@Autowired
	anneeService anneeservice;
	@GetMapping
	public List<AnnonceDto> getAllAnnoce(){
		return annonceservice.getAllannonce();
	}

	@GetMapping("/getbyid/{id}")
	public AnnonceDto getannonceById(@PathVariable("id") Long id)
	{ 
		return annonceservice.getAnnonce(id);
	}
	@PostMapping

	public AnnonceDto creationProduit(@RequestBody AnnonceDto annonce)
	{
		return annonceservice.saveAnnonce(annonce);
	}
	@PutMapping("/updateannonce")	
	public AnnonceDto updateAnnonce(@RequestBody AnnonceDto annonce )
	{
		return annonceservice.updateAnnonce(annonce);
	}
	@DeleteMapping("/delpro/{id}")
	public void deleteProduit(@PathVariable("id") Long id)
	{
		annonceservice.deleteAnnonceById(id);
	}




}
