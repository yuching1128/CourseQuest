package com.vt.coursequest.controller;

import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.entity.Appointment;
import com.vt.coursequest.service.AdvisingDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Gary
 * @date: 4/3/23
 * @description: some desc
 */

@RestController
public class AdvisingController {
    @Autowired
    private AdvisingDataService ads;

    @ApiOperation("This service is used to get all timeslots for an advisor")
    @GetMapping("/api/advisor/{advisorId}/all")
    public ResponseEntity<List<AdvisingTimeslot>> getTimeslotList(@PathVariable Integer advisorId) {
        return new ResponseEntity<>(ads.findAdvisingTimeslotsByAdvisorId(advisorId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all free advising timeslots from all advisors")
    @GetMapping("/api/advising/free")
    public ResponseEntity<List<AdvisingTimeslot>> getAllFreeTimeslotList() {
        return new ResponseEntity<>(ads.findFreeAdvisingTimeslots(), HttpStatus.OK);
    }

    @ApiOperation("This service is used by an advisor to create an available timeslot for appointments")
    @PostMapping("/api/advising/timeslot/add")
    public ResponseEntity<AdvisingTimeslot> addTimeslot(@RequestBody AdvisingTimeslot advisingTimeslot) {
        return new ResponseEntity<>(ads.createTimeslot(advisingTimeslot), HttpStatus.OK);
    }

    @ApiOperation("This service is used to update a timeslot for a specific advisor")
    @PutMapping("/api/advising/{timeslotId}/edit")
    public ResponseEntity<AdvisingTimeslot> updateTimeslot(@PathVariable Integer timeslotId, @RequestBody AdvisingTimeslot timeslot) throws Exception {
        return new ResponseEntity<>(ads.updateTimeslot(timeslotId, timeslot), HttpStatus.OK);
    }

    @ApiOperation("This service is used to delete a timeslot for a specific advisor")
    @DeleteMapping("/api/advising/{timeslotId}/delete")
    public void deleteTimeslot(@PathVariable Integer timeslotId) {
        ads.deleteTimeslot(timeslotId);
    }

    @ApiOperation("This service is used to get all appointments for an advisor")
    @GetMapping("/api/advising/advisor/{advisorId}/appointments")
    public ResponseEntity<List<Appointment>> getAdvisorAppointments(@PathVariable Integer advisorId) {
        return new ResponseEntity<>(ads.findAppointmentsByAdvisor(advisorId), HttpStatus.OK);
    }

    @ApiOperation("This service is used to get all appointments for an advisee")
    @GetMapping("/api/advising/advisee/{adviseeId}/appointments")
    public ResponseEntity<List<Appointment>> getAdviseeAppointments(@PathVariable Integer adviseeId) {
        return new ResponseEntity<>(ads.findAppointmentsByAdvisee(adviseeId), HttpStatus.OK);
    }

    @ApiOperation("This service is used by an advisee to book an appointment with an advisor at a timeslot")
    @PostMapping("/api/advising/book")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(ads.createAppointment(appointment), HttpStatus.OK);
    }

    @ApiOperation("This service is used to update an appointment")
    @PutMapping("/api/advising/book")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) throws Exception {
        return new ResponseEntity<>(ads.updateAppointment(appointment), HttpStatus.OK);
    }

    @ApiOperation("This service is used to delete an appointment")
    @DeleteMapping("/api/advising/book")
    public void deleteAppointment(@RequestBody Appointment appointment) {
        ads.deleteAppointment(appointment);
    }

    @ApiOperation("This service is used to cancel an appointment")
    @PutMapping("/api/advising/book/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<>(ads.cancelAppointment(appointment), HttpStatus.OK);
    }

}
