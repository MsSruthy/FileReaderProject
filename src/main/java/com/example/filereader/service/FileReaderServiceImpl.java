package com.example.filereader.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.filereader.model.FileReaderEntity;
import com.example.filereader.repository.FileReaderRespository;
import com.opencsv.CSVReader;

@Service
@Transactional
public class FileReaderServiceImpl implements FileReaderService {
	@Resource
	FileReaderRespository fileReaderRepo;

	@Override
	public void uploadFile(MultipartFile file) {

		try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
			// csvReader.readAll().forEach(a->System.out.println(a.toString()));
			List<String[]> readAll = csvReader.readAll();
			ArrayList<FileReaderEntity> entityList = new ArrayList<>();
			boolean header = true;
			ArrayList<String> headerList = new ArrayList<>();
			for (String[] row : readAll) {
				if (header) {
					for (String s : row) {
						headerList.add(s);
					}
					header = false;
				} else {
					entityList.add(loadDataToEnity(headerList, row));
				}
			}
			fileReaderRepo.saveAll(entityList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public FileReaderEntity loadDataToEnity(ArrayList<String> headerList, String[] row) {

		FileReaderEntity fileReaderEntity = new FileReaderEntity();
		HashMap<String, String> fileData = new HashMap<>();
		for (int i = 0; i < headerList.size(); i++) {
			fileData.put(headerList.get(i), row[i]);
		}
		fileReaderEntity.setSource(fileData.get("source"));
		fileReaderEntity.setCodeListCode(fileData.get("codeListCode"));
		fileReaderEntity.setCode(fileData.get("code"));
		fileReaderEntity.setDisplayValue(fileData.get("displayValue"));
		fileReaderEntity.setLongDescription(fileData.get("displayValue"));
		fileReaderEntity.setFromDate(fileData.get("fromDate"));
		fileReaderEntity.setToDate(fileData.get("toDate"));
		fileReaderEntity.setSortingPriority(fileData.get("sortingPriority"));

		return fileReaderEntity;
	}

	@Override
	public Iterable<FileReaderEntity> fetchAll() {
		return fileReaderRepo.findAll();

	}

	@Override
	public FileReaderEntity fetchByCode(String code) {
		return fileReaderRepo.findByCode(code);

	}

	@Override
	public void deleteAll() {
		fileReaderRepo.deleteAll();

	}
}
