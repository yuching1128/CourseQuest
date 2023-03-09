package com.vt.coursequestbackend.dao;

import com.vt.coursequestbackend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: EugeneFeng
 * @date: 3/8/23 4:32 AM
 * @description: some desc
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByUniversityId(int universityId);

    List<Course> findByUniversityIdAndId(int universityId, int id);
}
