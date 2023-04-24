package com.vt.coursequest.service;

import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.entity.Appointment;
import com.vt.coursequest.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
     * This function is used to get all free timeslots for a course
     * @return list of all free timeslots for a course
     */
    List<Object[]> findFreeAdvisingTimeslotsForCourse(Integer courseId, Integer userId);

    /**
     * This function is used to create a timeslot associated with an user
     * @param timeslot the provided timeslot as a datetime
     * @return the timeslot for the user
     */
    AdvisingTimeslot createTimeslot(Date timeslot, User advisor);

    /**
     * This function is used to delete a timeslot
     * @param timeslotId the unique id of the timeslot
     */
    void deleteTimeslot(Integer timeslotId);

    /**
     * This function is used to get an appointment by id
     *
     * @param appointmentId the appointment id
     * @return the appointment
     */
    Optional<Appointment> findAppointmentById(Integer appointmentId);

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
    Appointment createAppointment(Appointment appointment, User user);

    /**
     * This function is used to mark an appointment as cancelled. Does NOT delete an appointment from DB.
     * @param appointmentId the appointment Id
     */
    void cancelAppointment(Integer appointmentId);

    /**
     * This function is used to delete an appointment between an advisee and advisor
     * @param appointmentId
     */
    void deleteAppointment(Integer appointmentId);

}
