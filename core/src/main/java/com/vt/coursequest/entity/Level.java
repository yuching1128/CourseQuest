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
@Table(name = "level", schema = "CourseQuest")
public class Level {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Level() {
	}

	public Level(String level, University university) {
		this.name = level;
		this.university = university;
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
		Level levelObj = (Level) obj;
		return name.equals(levelObj.name);
	}
}
