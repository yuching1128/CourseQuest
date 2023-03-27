package com.vt.coursequest.dao;

import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.Review;
import com.vt.coursequest.entity.University;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 3/21/23 10:23 AM
 * @description: some desc
 */
public interface ReviewRepository extends JpaRepository<Review, Integer>, PagingAndSortingRepository<Review, Integer> {
    List<Review> findByCourseIdAndUniversityId(int courseId, int universityId, Pageable pageable);

    List<Review> findByCourseIdAndUniversityId(int courseId, int universityId);

    Optional<Review> findByCourseIdAndUniversityIdAndUserId(int courseId, int universityId, int userId);
}
