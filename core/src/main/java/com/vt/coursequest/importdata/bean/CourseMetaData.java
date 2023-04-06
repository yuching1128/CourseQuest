package com.vt.coursequest.importdata.bean;

public class CourseMetaData {

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	private String courseTitle;
	private String term;
	private String courseNo;
	private String instructor;
	private String crn;
	private String credits;
	private String dept;
	private String description;

	@Override
	public String toString() {
		return courseTitle + "crn: " + crn + "dddd" + instructor;
	}
}
