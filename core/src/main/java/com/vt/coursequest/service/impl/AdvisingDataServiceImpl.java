package com.vt.coursequest.service.impl;

import com.vt.coursequest.dao.AdvisingRepository;
import com.vt.coursequest.entity.AdvisingTimeslot;
import com.vt.coursequest.service.AdvisingDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvisingDataServiceImpl implements AdvisingDataService {

    @Resource
    private AdvisingRepository advisingRepository;

    @Override
    public List<AdvisingTimeslot> findAll(Integer userid) {
        return advisingRepository.findByUserId(userid);
    }

    /**
     * This function is used to create a timeslot associated with an user
     *
     * @param advisingTimeslot the provided hour-long time (as Date format)
     * @return the timeslot for the user
     */
    @Override
    public AdvisingTimeslot createTimeslot(AdvisingTimeslot advisingTimeslot) {
        return advisingRepository.save(advisingTimeslot);
    }

}
