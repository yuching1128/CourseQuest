package com.vt.coursequest.service;

import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.entity.Appointment;

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
     * @param advisorId : the unique id of the user
     * @return List<TimeSlot> for a particular user
     */
    List<AdvisingTimeslot> findAdvisingTimeslotsByAdvisorId(Integer advisorId);

    /**
     * This function is used to get all timeslots.
     * @return list of all timeslots
     */
    List<AdvisingTimeslot> findAllAdvisingTimeslots();

    /**
     * This function is used to get all free timeslots
     * @return list of all free timeslots
     */
    List<AdvisingTimeslot> findFreeAdvisingTimeslots();

    /**
     * This function is used to create a timeslot associated with an user
     * @param advisingTimeslot the provided timeslot
     * @return the timeslot for the user
     */
    AdvisingTimeslot createTimeslot(AdvisingTimeslot advisingTimeslot);

    /**
     * This function is used to update the advising timeslot
     * @param timeslotId: the unique timeslot id
     * @param timeslot: the timeslot (as a Date)
     * @return: the timeslot
     * @throws Exception
     */
    AdvisingTimeslot updateTimeslot(Integer timeslotId, AdvisingTimeslot timeslot) throws Exception;

    /**
     *
     * @param timeslotId the unique id of the timeslot
     */
    void deleteTimeslot(Integer timeslotId);

    /**
     * This function is used to get a list of all appointments from an advisor
     * @param advisorId the advisor's id
     * @return the list of Appointments from an advisor
     */
    List<Appointment> findAppointmentsByAdvisor(Integer advisorId);

    /**
     * This function is used to get a list of all appointments from an advisee
     * @param adviseeId the advisee's id
     * @return the list of Appointments from an advisee
     */
    List<Appointment> findAppointmentsByAdvisee(Integer adviseeId);

    /**
     * This function is used to book an appointment between an advisee and advisor
     * @param appointment: the appointment
     * @return the appointment
     */
    Appointment createAppointment(Appointment appointment);


}
