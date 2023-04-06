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

    @Override
    public AdvisingTimeslot createTimeslot(AdvisingTimeslot advisingTimeslot) {
        return advisingRepository.save(advisingTimeslot);
    }

    @Override
    public AdvisingTimeslot updateTimeslot(Integer timeslotId, AdvisingTimeslot timeslot) throws Exception {
        return advisingRepository.findById(timeslotId)
                .map(newTimeslot -> {
                    newTimeslot.setTime(timeslot.getTime());
                    newTimeslot.setSubject(timeslot.getSubject());
                    return advisingRepository.save(newTimeslot);
        }).orElseThrow(() -> new Exception("Timeslot not found with id " + timeslotId));
    }

    @Override
    public void deleteTimeslot(Integer timeslotId) {
        advisingRepository.deleteById(timeslotId);
    }
}
