package com.vt.coursequest.elasticsearch.model;

import lombok.Data;

@Data
public class CourseInfo {

	private String[] fullTextSearch;

	private String degreeType;

	private Integer level;

	private Integer universityId;
}
