package com.vt.coursequest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @description: some desc
 */
@Data
@Entity
@Table(name = "department", schema = "CourseQuest")
public class Department {

	@JsonProperty
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Integer id;

	@JsonProperty
	@Column(name = "name")
	String name;

	@JsonIgnore
	@JoinColumn(name = "university_id")
	@ManyToOne
	University university;

	public Department() {
	}

	public Department(String dept, University university) {
		this.name = dept;
		this.university = university;
	}


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Department deptObj = (Department) obj;
		return name.equals(deptObj.name);
	}
}
