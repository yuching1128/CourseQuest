package com.vt.coursequest.controller;

import com.google.gson.Gson;
import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.entity.Appointment;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.interceptor.UserDetailsFromGoogle;
import com.vt.coursequest.service.AdvisingDataService;
import com.vt.coursequest.service.UserDataService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author: Gary
 * @date: 4/3/23
 * @description: Controller for advising functionality
 */

@RestController
@Slf4j
public class AdvisingController {
    @Autowired
    private AdvisingDataService ads;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserDataService uds;

    @ApiOperation("This service is used to get all timeslots (free, scheduled, expired) for an advisor")
    @GetMapping("/api/advising/all")
    public ResponseEntity<List<AdvisingTimeslot>> getTimeslotsForAdvisor() {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(ads.findAdvisingTimeslotsByAdvisorId(user.getId()), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get free timeslots from a course (excluding self)")
    @GetMapping("/api/advising/course/{courseId}/free")
    public ResponseEntity<List<Object[]>> getFreeTimeslotsForCourse(@PathVariable Integer courseId) {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(ads.findFreeAdvisingTimeslotsForCourse(courseId, user.getId()), HttpStatus.OK);
    }

    @ApiOperation("This service is used by an advisor to create an available timeslot for appointments")
    @PostMapping("/api/advising/timeslot/add")
    public ResponseEntity<AdvisingTimeslot> addTimeslot(@RequestBody Date timeslot) {
        AdvisingTimeslot advisingTimeslotResponse = new AdvisingTimeslot();
        try {
            UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
            System.out.println("HI!!");
            User user = uds.findOrCreateUser(userDetails);
            advisingTimeslotResponse = ads.createTimeslot(timeslot, user);
        } catch(Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(advisingTimeslotResponse, HttpStatus.OK);
    }

    @ApiOperation("This service is used to delete a timeslot for a specific advisor")
    @DeleteMapping("/api/advising/timeslot/{timeslotId}/delete")
    public void deleteTimeslot(@PathVariable Integer timeslotId) {
        ads.deleteTimeslot(timeslotId);
    }

    @ApiOperation("This service is used to get a single appointment")
    @GetMapping("/api/advising/appointment/{appointmentId}")
    public ResponseEntity<Optional<Appointment>> getAppointment(@PathVariable Integer appointmentId) {
        return new ResponseEntity<>(ads.findAppointmentById(appointmentId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all appointments for an advisor")
    @GetMapping("/api/advising/advisor/appointments")
    public ResponseEntity<List<Appointment>> getAdvisorAppointments() {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(ads.findAppointmentsByAdvisor(user.getId()), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all appointments for an advisee")
    @GetMapping("/api/advising/advisee/appointments")
    public ResponseEntity<List<Appointment>> getAdviseeAppointments() {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(ads.findAppointmentsByAdvisee(user.getId()), HttpStatus.OK);
    }

    @ApiOperation("This service is used by an advisee to book an appointment with an advisor at a timeslot")
    @PostMapping("/api/advising/book")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        return new ResponseEntity<>(ads.createAppointment(appointment, user), HttpStatus.OK);
    }

    @ApiOperation("This service is used to cancel an appointment")
    @PutMapping("/api/advising/appointment/{appointmentId}/cancel")
    public void cancelAppointment(@PathVariable Integer appointmentId) {
        ads.cancelAppointment(appointmentId);
    }

//    UNUSED OPERATION
//    @ApiOperation("This service is used to delete an appointment")
//    @DeleteMapping("/api/advising/appointment/{appointmentId}/delete")
//    public void deleteAppointment(@PathVariable Integer appointmentId) {
//        ads.deleteAppointment(appointmentId);
//    }


}
