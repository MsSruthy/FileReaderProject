package com.example.filereader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.filereader.service.FileReaderService;

@RestController
@RequestMapping("/fileReader")
public class FileReaderController {
	@Autowired
	public FileReaderService fileReaderService;

	@PostMapping(path = "/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		fileReaderService.uploadFile(file);
		return new ResponseEntity<String>("File uploaded Successfully", HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/fetchAll")
	public ResponseEntity fetchAll() {
		return new ResponseEntity(fileReaderService.fetchAll(), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/fetchByCode/{code}")
	public ResponseEntity fetchByCode(@PathVariable(name = "code") String code) {
		return new ResponseEntity(fileReaderService.fetchByCode(code), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/deleteAll")
	public ResponseEntity<String> deleteAll() {
		fileReaderService.deleteAll();
		return new ResponseEntity<String>("Deleted data Successfully", HttpStatus.OK);
	}
}
