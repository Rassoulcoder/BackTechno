package com.example.demo.service;

import java.nio.file.Path;

import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface docService {
	void init();
void saveFile(MultipartFile file, Long candidatureId);
Resource load(String filname);
void deleteAll();
Stream<Path> loadAll();
}
