package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.AdvisingTimeslotRepository;
import com.vt.coursequest.dao.AppointmentRepository;
import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.entity.AdvisingTimeslotStatus;
import com.vt.coursequest.entity.Appointment;
import com.vt.coursequest.service.AdvisingDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdvisingDataServiceImpl implements AdvisingDataService {

    @Resource
    private AdvisingTimeslotRepository advisingTimeslotRepository;

    @Resource
    private AppointmentRepository appointmentRepository;

    @Override
    public List<AdvisingTimeslot> findAdvisingTimeslotsByAdvisorId(Integer advisorId) {
        return advisingTimeslotRepository.findByAdvisorId(advisorId);
    }

    @Override
    public List<AdvisingTimeslot> findAllAdvisingTimeslots() {
        return advisingTimeslotRepository.findAll();
    }

    @Override
    public List<AdvisingTimeslot> findFreeAdvisingTimeslots() {
        return advisingTimeslotRepository.findAllFree();
    }

    @Override
    public AdvisingTimeslot createTimeslot(AdvisingTimeslot advisingTimeslot) {
        System.out.println(advisingTimeslot);
        return advisingTimeslotRepository.save(advisingTimeslot);
    }

    @Override
    public AdvisingTimeslot updateTimeslot(Integer timeslotId, AdvisingTimeslot timeslot) throws Exception {
        return advisingTimeslotRepository.findById(timeslotId)
                .map(newTimeslot -> {
                    newTimeslot.setTime(timeslot.getTime());
                    return advisingTimeslotRepository.save(newTimeslot);
        }).orElseThrow(() -> new Exception("Timeslot not found with id " + timeslotId));
    }

    @Override
    public void deleteTimeslot(Integer timeslotId) {
        advisingTimeslotRepository.deleteById(timeslotId);
    }

    @Override
    public List<Appointment> findAppointmentsByAdvisor(Integer advisorId) {
        return appointmentRepository.findByAdvisorId(advisorId);
    }

    @Override
    public List<Appointment> findAppointmentsByAdvisee(Integer adviseeId) {
        return appointmentRepository.findByAdviseeId(adviseeId);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // update advising timeslot status from 'free' to 'scheduled'
        advisingTimeslotRepository.findById(appointment.getAdvisingTimeslot().getId()).map(
                newTimeslot -> {
                    newTimeslot.setAdvisingTimeslotStatus(AdvisingTimeslotStatus.SCHEDULED);
                    return advisingTimeslotRepository.save(newTimeslot);
                }
        );

        appointment.setCreatedAt(new Date());
        return appointmentRepository.save(appointment);
    }


    @Override
    public Appointment cancelAppointment(Appointment appointment) {
        //update advising timeslot status from 'scheduled' to 'expired'
        advisingTimeslotRepository.findById(appointment.getAdvisingTimeslot().getId()).map(
                newTimeSlot -> {
                    newTimeSlot.setAdvisingTimeslotStatus(AdvisingTimeslotStatus.EXPIRED);
                    return advisingTimeslotRepository.save(newTimeSlot);
                }
        );
        appointment.setCreatedAt(new Date());
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        //delete the appointment and set the timeslot status from 'scheduled' to 'free'
        advisingTimeslotRepository.findById(appointment.getAdvisingTimeslot().getId()).map(
                newTimeSlot -> {
                    newTimeSlot.setAdvisingTimeslotStatus(AdvisingTimeslotStatus.FREE);
                    return advisingTimeslotRepository.save(newTimeSlot);
                }
        );
        appointmentRepository.deleteById(appointment.getId());
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) throws Exception {
        appointmentRepository.findById(appointment.getId()).map(
                newAppointment -> {
                    newAppointment.setCreatedAt(new Date());
                    newAppointment.setAdvisor(appointment.getAdvisor());
                    newAppointment.setAdvisee(appointment.getAdvisee());
                    newAppointment.setCourse(appointment.getCourse());
                    newAppointment.setAdvisingTimeslot(appointment.getAdvisingTimeslot());
                    return appointmentRepository.save(newAppointment);
                }
        ).orElseThrow(()-> new Exception("Appointment not found with id " + appointment.getId()));
        return appointment;
    }
}
