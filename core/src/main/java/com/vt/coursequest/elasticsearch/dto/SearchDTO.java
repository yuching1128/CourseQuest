package com.vt.coursequest.elasticsearch.dto;

import lombok.Data;

@Data
public class SearchDTO {

	private String[] fullTextSearch;

	private String degreeType;

	private Integer level;

	private Integer universityId;
}
