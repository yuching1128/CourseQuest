package com.vt.coursequest.service;

import com.vt.coursequest.entity.AdvisingTimeslot;

import java.util.List;

/**
 *
 * This service contains all the advising related services.
 */
public interface AdvisingDataService {

    /**
     * This function is used to get exhaustive list of all the time slots specific
     * to an user
     *
     * @param userid : the unique id of the user
     * @return List<TimeSlot> for a particular user
     */
    List<AdvisingTimeslot> findAll(Integer userid);

    /**
     * This function is used to create a timeslot associated with an user
     * @param advisingTimeslot the provided timeslot
     * @return the timeslot for the user
     */
    AdvisingTimeslot createTimeslot(AdvisingTimeslot advisingTimeslot);
}
