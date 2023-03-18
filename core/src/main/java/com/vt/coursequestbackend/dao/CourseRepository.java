package com.vt.coursequestbackend.dao;

import com.vt.coursequestbackend.entity.Course;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

/**
 * @author: EugeneFeng
 * @date: 3/8/23 4:32 AM
 * @description: some desc
 */
public interface CourseRepository extends JpaRepository<Course, Integer>, PagingAndSortingRepository<Course, Integer> {
    List<Course> findByUniversityId(int universityId);
    List<Course> findByUniversityId(int universityId, Pageable pageable);

    List<Course> findByUniversityIdAndId(int universityId, int id);
}
