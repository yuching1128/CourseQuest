package com.vt.coursequest.dao;

import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 *
 * This class is for getting data based on query from Timeslot table from the database
 *
 */
public interface AdvisingTimeslotRepository extends JpaRepository<AdvisingTimeslot, Integer> {

    List<AdvisingTimeslot> findByAdvisorIdOrderByTimeAsc(int advisorId);

    @Query(value = "SELECT a.id, a.advising_timeslot_status, a.time, a.advisor_id FROM advising_timeslot a \n" +
            "         WHERE a.advising_timeslot_status=0 \n" +
            "           AND a.advisor_id in (SELECT user_id FROM user_course WHERE course_id=:courseId) \n" +
            "           AND a.advisor_id != :userId \n" +
            "         ORDER BY a.time", nativeQuery = true)
    List<Object[]> findAllFreeForCourse(@Param("courseId") Integer courseId, @Param("userId") Integer userId);

}
