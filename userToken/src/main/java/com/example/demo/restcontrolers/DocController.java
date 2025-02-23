package com.example.demo.restcontrolers;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.modele.Document;
import com.example.demo.service.docService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(value = "/api/v1/document",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public class DocController {
	@Autowired
	private docService docservice;
	
	@PostMapping(value = "/envoyer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
		docservice.saveFile(file);
		return ResponseEntity.ok("Fichier enregietrer avec succes"+file.getOriginalFilename());		
	}
	
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	@GetMapping("/docs")
	private ResponseEntity<List<Document>> getAllDoc(){
		
		List<Document> doc = docservice.loadAll()
				 .map(path -> {
				        String filename = path.getFileName().toString();
				        String url = MvcUriComponentsBuilder.fromMethodName(
				                        DocController.class, "getFile", filename)
				                .build()
				                .toString();
				        return new Document(filename, url, null);
				    })
				    .collect(Collectors.toList());
		return ResponseEntity.ok(doc);
		
	}
	
	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<?> getFile(@PathVariable String filename){
		Resource file = docservice.load(filename);
		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment;filename\""+file.getFilename()+"\"").body(file);
		
	}
	

}
