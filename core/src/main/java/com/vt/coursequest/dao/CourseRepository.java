package com.vt.coursequest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.vt.coursequest.entity.Course;

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

	@Query("SELECT round(AVG(r.rating), 2) FROM Review r WHERE r.course.id = :courseId AND r.rating is not NULL")
	Double getAverageRatingForCourse(@Param("courseId") int courseId);
	
	Optional<Course> findByCourseNum(String courseNum);

	Optional<Course> findByCourseNumAndDeptIdAndUniversityId(String courseNo, Integer deptId, int universityId);

	@Query("SELECT c FROM Course c WHERE c.name in :courseNames")
	List<Course> getAvailableCourses(List<String> courseNames);
	
}
