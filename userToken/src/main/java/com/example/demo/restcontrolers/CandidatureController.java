package com.example.demo.restcontrolers;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.CandidatureDto;
import com.example.demo.modele.Candidature;
import com.example.demo.service.candidatureService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value ="/api/v1/postule", consumes = "application/json")
public class CandidatureController {
   @Autowired
   candidatureService candidatureservice;
   
   @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<CandidatureDto> creationCandidature(
           @RequestPart("candidature") String candidatureJson, // Récupérer JSON sous forme de String
           @RequestParam(value = "documents", required = false) List<MultipartFile> documents) throws IOException {

       // Convertir la chaîne JSON en objet Java
       ObjectMapper objectMapper = new ObjectMapper();
       CandidatureDto candidature = objectMapper.readValue(candidatureJson, CandidatureDto.class);

       // Log pour vérifier les valeurs reçues
       System.out.println("Candidature reçue : " + candidature);
       System.out.println("Nombre de documents reçus : " + (documents != null ? documents.size() : 0));

       // Sauvegarde de la candidature (Exemple)
       CandidatureDto savedCandidature = candidatureservice.saveCandidature(candidature);

       return ResponseEntity.ok(savedCandidature);
   
}
  /* @GetMapping("/user")
   public List<CandidatureDto> getUserCandidature() {
       return candidatureservice.getAllcandidatureUser();
   }   */
   @GetMapping(value = "/all", produces = "application/json")
   public List<Candidature> getMethodName() {
       return candidatureservice.getAll();
   }
   

}