package com.example.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.modele.Candidature;
import com.example.demo.modele.Document;
import com.example.demo.repository.CandidatureRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.restcontrolers.DocController;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class docImplService implements docService {
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	CandidatureRepository cand;
private final Path root = Paths.get("uploads");
	/*@Override
	public void saveFile(MultipartFile file, Long candidatureId) {
		init();
		
try {
	Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	   // Créer l'URL du fichier
    String url = MvcUriComponentsBuilder.fromMethodName(DocController.class, "getFile", file.getOriginalFilename())
                        .build().toString();
    Optional<Candidature> candidatureOpt = cand.findById(candidatureId);
    if (!candidatureOpt.isPresent()) {
        throw new IllegalArgumentException("Candidature introuvable avec l'ID : " + candidatureId);
    }
    
    Candidature candidature = candidatureOpt.get();
	 Document document = new Document(file.getOriginalFilename(), url,candidature);
     documentRepository.save(document);
} catch (IOException e) {
	e.printStackTrace();
}	
	}*/
@Override
public void saveFile(MultipartFile file, Long candidatureId) {
    init();
    
    try {
        // Vérifier si le fichier existe déjà
        Path destinationPath = this.root.resolve(file.getOriginalFilename());
        
        // Si le fichier existe, le remplacer
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        
        // Créer l'URL du fichier
        String url = MvcUriComponentsBuilder.fromMethodName(DocController.class, "getFile", file.getOriginalFilename())
                        .build().toString();
        
        // Vérifier si la candidature existe
        Optional<Candidature> candidatureOpt = cand.findById(candidatureId);
        if (!candidatureOpt.isPresent()) {
            throw new IllegalArgumentException("Candidature introuvable avec l'ID : " + candidatureId);
        }
        
        // Créer et enregistrer le document
        Candidature candidature = candidatureOpt.get();
        Document document = new Document(file.getOriginalFilename(), url, candidature);
        documentRepository.save(document);
        
    } catch (IOException e) {
        e.printStackTrace();
    }    
}


	@Override
	public Resource load(String filname) {
Path file = root.resolve(filname);
Resource ressource = null;
try {
	ressource = new UrlResource(file.toUri());
} catch (MalformedURLException e) {
	e.printStackTrace();
}
if(ressource.exists() || ressource.isReadable()) {
	return ressource;
}else {
	throw new RuntimeException("Ne peut pas lire ce fichier");
}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		try {
			return Files.walk(this.root, 1)
				    .filter(path -> !path.equals(this.root))
				    .map(this.root::resolve);
		} catch (IOException e) {
		throw new RuntimeException(e);
		}
		

	}

	@Override
	public void init() {
try {
	Files.createDirectories(root);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	
	}

}
