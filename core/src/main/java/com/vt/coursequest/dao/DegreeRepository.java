package com.vt.coursequest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.Degree;

/**
 * @author: EugeneFeng
 * @date: 3/10/23 12:23 AM
 * @description: some desc
 */
public interface DegreeRepository extends JpaRepository<Degree, Integer> {
	
	Optional<Degree> findByName(String name);

}
