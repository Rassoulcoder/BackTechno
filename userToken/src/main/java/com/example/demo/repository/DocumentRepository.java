package com.example.demo.repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
   Optional<Document> findByIddoc(Long iddoc);



}
