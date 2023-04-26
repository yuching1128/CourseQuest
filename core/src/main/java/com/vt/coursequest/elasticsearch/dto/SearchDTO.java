package com.vt.coursequest.elasticsearch.dto;

import lombok.Data;

@Data
public class SearchDTO {

	private String fullTextSearch = "";

	private String dept = "";

	private String level = "";

	private String universityId = "";

	public boolean isEmpty() {
		if (fullTextSearch.isBlank() && dept.isBlank() && level.isBlank() && !universityId.isBlank()) {
			return true;
		}
		return false;
	}
}
