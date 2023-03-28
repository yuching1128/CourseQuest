package com.vt.coursequest.importdata.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CourseMetaData {
	private String courseTitle;
	private String term;
	private String courseNo;
	private String instructor;
	private String crn;
	private String credits;
	
	@Override
	public String toString() {
		return courseTitle +"crn: "+ crn +"dddd"+ instructor;
	}
}
