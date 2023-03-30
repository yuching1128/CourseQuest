package com.vt.coursequest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author: EugeneFeng
 * @date: 3/8/23 11:25 AM
 * @description: some desc
 */

@Data
@Entity
@Table(name = "university")
public class University {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	int id;

	@Column(name = "name")
	String name;

	public University(int id) {
		this.id = id;
	}

	public University() {
	}
}
