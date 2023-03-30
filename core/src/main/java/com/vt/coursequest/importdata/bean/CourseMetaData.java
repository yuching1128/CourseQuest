package com.vt.coursequest.importdata.bean;

public class CourseMetaData {

	public String getCourseTitle() {
		return courseTitle;
	}

	public String getTerm() {
		return term;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public String getInstructor() {
		return instructor;
	}

	public String getCrn() {
		return crn;
	}

	public String getCredits() {
		return credits;
	}

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
