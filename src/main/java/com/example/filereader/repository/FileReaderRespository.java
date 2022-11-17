package com.example.filereader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.filereader.model.FileReaderEntity;

@Repository
public interface FileReaderRespository extends CrudRepository<FileReaderEntity, Long> {

	FileReaderEntity findByCode(String code);

}
