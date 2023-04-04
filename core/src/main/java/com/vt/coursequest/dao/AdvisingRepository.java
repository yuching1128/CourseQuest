package com.vt.coursequest.dao;

import com.vt.coursequest.entity.AdvisingTimeslot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * This class is for getting data based on query from Advising table from the database
 *
 */
public interface AdvisingRepository extends JpaRepository<AdvisingTimeslot, Integer> {

    List<AdvisingTimeslot> findByUserId(int userId);

}
