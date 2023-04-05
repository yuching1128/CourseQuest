package com.vt.coursequest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vt.coursequest.entity.Level;

/**
 * @description: some desc
 */
public interface LevelRepository extends JpaRepository<Level, Integer> {

	List<Level> findByUniversityId(int universityId);

	Optional<Level> findByNameAndUniversityId(String level, int universityId);
	
}
