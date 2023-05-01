package com.vt.coursequest.service.impl;

import com.vt.coursequest.config.OpenAIConfig;
import com.vt.coursequest.dao.CourseRepository;
import com.vt.coursequest.entity.Course;
import com.vt.coursequest.entity.OpenAIRequest;
import com.vt.coursequest.entity.OpenAIResponse;
import com.vt.coursequest.service.OpenAIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OpenAIServiceImpl implements OpenAIService {

    @Resource
    private CourseRepository courseRepo;

    private static RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Course> requestRecommendation(String concentration, Set<Course> courses) throws Exception {
        StringBuilder question = new StringBuilder();
        if(!Objects.isNull(courses)){
            question.append("Recommend me courses given I already took ")
                    .append(String.join(", ", courses
                            .stream().map(Course::getName).collect(Collectors.toList())
                            .toString()
                            .replaceFirst("\\[","")
                            .replaceFirst("]","")));
        }
        if(!Objects.isNull(concentration)){
            question.append(" in the concentration ")
                    .append(concentration);
        }
        log.info(question.toString());
        String responseFromOpenAI = this.getRecommendationFromOpenAI(question.toString());
        List<String> coursesRecom = Arrays.asList(responseFromOpenAI.split("\n\n")[0].split(", ")).stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
        List<Course> coursesAvailable = courseRepo.getAvailableCourses(coursesRecom);
        return coursesAvailable;
    }

    public String getRecommendationFromOpenAI(String question){
        ResponseEntity<OpenAIResponse> response = restTemplate.postForEntity(
                                                    OpenAIConfig.URL,
                                                    this.buildHttpEntity(question),
                                                    OpenAIResponse.class);
        log.info(response.getBody().toString());
        return response.getBody().getChoices().get(0).getText();

    }

    private OpenAIRequest getHttpRequest(String question){
        OpenAIRequest request = new OpenAIRequest();
        request.setMaxTokens(OpenAIConfig.MAX_TOKEN);
        request.setTemperature(OpenAIConfig.TEMPERATURE);
        request.setModel(OpenAIConfig.MODEL);
        request.setPrompt(question);
        request.setTopP(OpenAIConfig.TOP_P);
        return request;
    }

    private HttpEntity<OpenAIRequest> buildHttpEntity(String question){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(OpenAIConfig.MEDIA_TYPE));
        headers.add(OpenAIConfig.AUTHORIZATION, OpenAIConfig.BEARER + OpenAIConfig.API_KEY);
        return new HttpEntity<>(getHttpRequest(question), headers);
    }
}
