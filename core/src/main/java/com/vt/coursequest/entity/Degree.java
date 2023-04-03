package com.vt.coursequest.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: EugeneFeng
 * @date: 3/9/23 11:23 PM
 * @description: some desc
 */
@Data
@Entity
@Table(name = "degree", schema = "CourseQuest")
public class Degree {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@Column(name = "name", unique = true)
	String name;

	public Degree(String name) {
		this.name = name;
	}
	
	public Degree() {}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Degree degreeObj = (Degree) obj;
		return name.equals(degreeObj.name);
	}
	
	@Override
		public int hashCode() {
			return name.hashCode();
		}
}
