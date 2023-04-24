package com.vt.coursequest.elasticsearch.dto;

import lombok.Data;

@Data
public class SearchDTO {

	private String fullTextSearch;

	private String dept;

	private String level;

	private String universityId;
}
