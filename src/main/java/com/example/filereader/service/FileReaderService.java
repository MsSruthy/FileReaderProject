package com.example.filereader.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.filereader.model.FileReaderEntity;


public interface FileReaderService {

	void uploadFile(MultipartFile file);
	Iterable<FileReaderEntity> fetchAll();
	FileReaderEntity fetchByCode(String code);
	void deleteAll();

}
