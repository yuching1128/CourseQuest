package com.vt.coursequest.controller;

import com.vt.coursequest.entity.User;
import com.vt.coursequest.interceptor.UserDetailsFromGoogle;
import com.vt.coursequest.service.OpenAIService;
import com.vt.coursequest.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/")
public class OpenAIController {

    @Autowired
    private UserDataService uds;

    @Autowired
    private HttpSession session;

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/requestrecommendation")
    public ResponseEntity<List<String>> askForRecommendation() throws Exception {
        UserDetailsFromGoogle userDetails = (UserDetailsFromGoogle) session.getAttribute("user_details");
        User user = uds.findOrCreateUser(userDetails);
        List<String> recCourses = new ArrayList<>();
        try{
            recCourses = openAIService.requestRecommendation(user.getConcentration(), user.getCourse());
        } catch (Exception e){
            log.error("Exception occured",e);
        }
        return new ResponseEntity<>(recCourses, HttpStatus.OK);
    }
}
