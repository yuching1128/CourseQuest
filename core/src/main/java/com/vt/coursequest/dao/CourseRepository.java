package com.vt.coursequest.dao;

import java.util.List;
import java.util.Optional;

import com.vt.coursequest.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vt.coursequest.entity.Course;
import org.springframework.data.repository.query.Param;

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

	@Query("SELECT AVG(r.rating) FROM Review r WHERE r.course.id = :courseId")
	Double getAverageRatingForCourse(@Param("courseId") int courseId);
	
	Optional<Course> findByCourseNum(String courseNum);

}
