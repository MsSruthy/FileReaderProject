package com.example.filereader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fileData")
@Getter
@Setter
public class FileReaderEntity {


	@Column(name = "source")
	String source;
	@Column(name = "code_list_code")
	String codeListCode;
	@Id
	@Column(name = "code")
	String code;
	@Column(name = "display_value")
	String displayValue;
	@Column(name = "long_description")
	String longDescription;
	@Column(name = "from_date")
	String fromDate;
	@Column(name = "to_date")
	String toDate;
	@Column(name = "sorting_priority")
	String sortingPriority;

}
