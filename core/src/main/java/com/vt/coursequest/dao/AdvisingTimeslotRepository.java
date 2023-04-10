package com.vt.coursequest.dao;

import com.vt.coursequest.entity.AdvisingTimeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * This class is for getting data based on query from Timeslot table from the database
 *
 */
public interface AdvisingTimeslotRepository extends JpaRepository<AdvisingTimeslot, Integer> {

    List<AdvisingTimeslot> findByAdvisorId(int advisorId);

    @Query("SELECT a FROM AdvisingTimeslot a WHERE a.advisingTimeslotStatus = 0")
    List<AdvisingTimeslot> findAllFree();

}
