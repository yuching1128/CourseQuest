package com.vt.coursequest.dao;

import com.vt.coursequest.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * This class is for getting data based on query from Appointment table from the database
 *
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByAdviseeId(int adviseeId);
    List<Appointment> findByAdvisorId(int advisorId);

    @Query("SELECT a FROM Appointment a")
    List<Appointment> getAllAppointments();

}
