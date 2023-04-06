package com.vt.coursequest.controller;

import com.vt.coursequest.entity.AdvisingTimeslot;
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

    @ApiOperation("This service is used to get all timeslots for an user")
    @GetMapping("/api/user/{userId}/advising")
    public ResponseEntity<List<AdvisingTimeslot>> getTimeslotList(@PathVariable Integer userId) {
        return new ResponseEntity<>(ads.findAll(userId), HttpStatus.OK);
    }

    @ApiOperation("This service is used by an advisor to create an available timeslot for appointments")
    @PostMapping("/api/advising/add")
    public ResponseEntity<AdvisingTimeslot> addTimeslot(@RequestBody AdvisingTimeslot advisingTimeslot) {
        return new ResponseEntity<>(ads.createTimeslot(advisingTimeslot), HttpStatus.OK);
    }

    @ApiOperation("This service is used to update a timeslot for a specific user")
    @PutMapping("/api/advising/{timeslotId}")
    public ResponseEntity<AdvisingTimeslot> updateTimeslot(@PathVariable Integer timeslotId, @RequestBody AdvisingTimeslot timeslot) throws Exception {
        return new ResponseEntity<>(ads.updateTimeslot(timeslotId, timeslot), HttpStatus.OK);
    }

    @ApiOperation("This service is used to delete a timeslot for a specific user")
    @DeleteMapping("/api/advising/{timeslotId}")
    public void deleteTimeslot(@PathVariable Integer timeslotId) {
        ads.deleteTimeslot(timeslotId);
    }
}
