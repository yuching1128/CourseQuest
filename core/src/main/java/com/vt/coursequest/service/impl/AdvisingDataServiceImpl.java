package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.AdvisingTimeslotRepository;
import com.vt.coursequest.dao.AppointmentRepository;
import com.vt.coursequest.entity.*;
import com.vt.coursequest.service.AdvisingDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.PreRemove;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdvisingDataServiceImpl implements AdvisingDataService {

    @Resource
    private AdvisingTimeslotRepository advisingTimeslotRepository;

    @Resource
    private AppointmentRepository appointmentRepository;

    /**
     * For any timeslots that are in the past, update the status as either:
     *  FREE -> EXPIRED (for unbooked timeslots)
     *  SCHEDULED -> COMPLETED (for booked and completed timeslots)
     */
    private void updateTimeslotsInPast() {
        List<AdvisingTimeslot> advisingTimeslotList = advisingTimeslotRepository.findAll();
        for (AdvisingTimeslot advisingTimeslot : advisingTimeslotList) {
            // FREE -> EXPIRED
            if (advisingTimeslot.getStatus().equals(AdvisingTimeslotStatus.FREE) && advisingTimeslot.getTime().before(new Date())) {
                advisingTimeslot.setStatus(AdvisingTimeslotStatus.EXPIRED);
                advisingTimeslotRepository.save(advisingTimeslot);
            }
            // SCHEDULED -> COMPLETED
            else if (advisingTimeslot.getStatus().equals(AdvisingTimeslotStatus.SCHEDULED) && advisingTimeslot.getTime().before(new Date())) {
                advisingTimeslot.setStatus(AdvisingTimeslotStatus.COMPLETED);
                advisingTimeslotRepository.save(advisingTimeslot);
            }
        }
    }

    /**
     * For any appointments that are in the past and not cancelled, update the status:
     *  UPCOMING -> COMPLETED
     */
    private void updateAppointmentsInPast() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        for (Appointment appointment : appointmentList) {
            // UPCOMING -> COMPLETED
            if (appointment.getAppointmentStatus().equals(AppointmentStatus.UPCOMING) && appointment.getAdvisingTimeslot().getTime().before(new Date())) {
                appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
                appointmentRepository.save(appointment);
            }
        }
    }

    @Override
    public List<AdvisingTimeslot> findAdvisingTimeslotsByAdvisorId(Integer advisorId) {
        updateTimeslotsInPast();
        return advisingTimeslotRepository.findByAdvisorIdOrderByTimeAsc(advisorId);
    }

    @Override
    public List<AdvisingTimeslot> findAllAdvisingTimeslots() {
        updateTimeslotsInPast();
        return advisingTimeslotRepository.findAll();
    }

    @Override
    public List<Object[]> findFreeAdvisingTimeslotsForCourse(Integer courseId, Integer userId) {
        updateTimeslotsInPast();
        return advisingTimeslotRepository.findAllFreeForCourse(courseId, userId);
    }

    @Override
    public AdvisingTimeslot createTimeslot(Date timeslot, User advisor) {
        AdvisingTimeslot newAdvisingTimeslot = new AdvisingTimeslot();
        newAdvisingTimeslot.setAdvisor(advisor);
        newAdvisingTimeslot.setStatus(AdvisingTimeslotStatus.FREE);
        newAdvisingTimeslot.setTime(timeslot);
        return advisingTimeslotRepository.save(newAdvisingTimeslot);
    }

    @Override
    @PreRemove
    public void deleteTimeslot(Integer timeslotId) {
        advisingTimeslotRepository.deleteById(timeslotId);
    }

    @Override
    public Optional<Appointment> findAppointmentById(Integer appointmentId) {
        updateAppointmentsInPast();
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public List<Appointment> findAppointmentsByAdvisor(Integer advisorId) {
        updateAppointmentsInPast();
        return appointmentRepository.findByAdvisorId(advisorId);
    }

    @Override
    public List<Appointment> findAppointmentsByAdvisee(Integer adviseeId) {
        updateAppointmentsInPast();
        return appointmentRepository.findByAdviseeId(adviseeId);
    }

    @Override
    public Appointment createAppointment(Appointment appointment, User user) {
        // update advising timeslot status from 'free' to 'scheduled'
        advisingTimeslotRepository.findById(appointment.getAdvisingTimeslot().getId()).map(
                newTimeslot -> {
                    newTimeslot.setStatus(AdvisingTimeslotStatus.SCHEDULED);
                    return advisingTimeslotRepository.save(newTimeslot);
                }
        );

        appointment.setCreatedAt(new Date());
        appointment.setAdvisee(user);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void cancelAppointment(Integer appointmentId) {
        // set the timeslot status from 'scheduled' to 'free'
        appointmentRepository.findById(appointmentId).map(
                currentAppointment -> {
                    AdvisingTimeslot currentTimeslot = currentAppointment.getAdvisingTimeslot();
                    currentTimeslot.setAdvisingTimeslotStatus(AdvisingTimeslotStatus.FREE);
                    return advisingTimeslotRepository.save(currentTimeslot);
                }
        );

        // set the appointment status to CANCELLED
        appointmentRepository.findById(appointmentId).map(
                currentAppointment -> {
                    currentAppointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
                    return appointmentRepository.save(currentAppointment);
                }
        );

    }

    // not used
    @Override
    public void deleteAppointment(Integer appointmentId) {
        // set the timeslot status from 'scheduled' to 'free'

        // delete
        appointmentRepository.deleteById(appointmentId);
    }


}
