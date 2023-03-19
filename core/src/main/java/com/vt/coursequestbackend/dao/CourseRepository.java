package com.vt.coursequestbackend.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vt.coursequestbackend.entity.Course;

/**
 * 
 * This class is for getting data based on query from Course table from the database
 * 
 * @author: EugeneFeng
 * @date: 3/8/23 4:32 AM
 * @description: some desc
 */
public interface CourseRepository extends JpaRepository<Course, Integer>, PagingAndSortingRepository<Course, Integer> {

	List<Course> findByUniversityId(int universityId);

	List<Course> findByUniversityId(int universityId, Pageable pageable);

	Optional<Course> findByUniversityIdAndId(int universityId, int id);

}
