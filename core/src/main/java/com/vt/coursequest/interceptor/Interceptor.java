package com.vt.coursequest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info(request.getRequestURI());

        if(!request.getRequestURI().contains("/api")){
            log.info("Request is from frontend");
            return true;
        }

        if (request.getHeader("Authorization") == null){
            response.addHeader("Interceptor", "No Authorization Details");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            log.info("No Authorization Details");
            log.info("No Valid Authorization");
            return false;
        }
        else if (!request.getHeader("Authorization").isBlank() && isAuthorizedUser(request)) {
            response.addHeader("Interceptor", "Authorization OK");
            log.info("Valid Authorization");
            log.info(request.getHeader("Authorization"));
            return true;
        } else {
            response.addHeader("Interceptor", "Invalid Authorization Details");
            log.info("Invalid Authorization Details");
            log.info(request.getHeader("Authorization"));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
//        isAuthorizedUser(request);
//        return true;

    }

    private boolean isAuthorizedUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String[] parts = token.split(" ");
        if(parts.length != 2 || !parts[0].contains("Bearer")){
            return false;
        }
        final String userInfoUri = "https://www.googleapis.com/oauth2/v3/userinfo";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+parts[1]);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<GoogleAPIResponse> googleAPIResp = restTemplate.exchange(userInfoUri, HttpMethod.GET, requestEntity, GoogleAPIResponse.class);
            log.info(String.valueOf(googleAPIResp));
            if(!googleAPIResp.getStatusCode().equals(HttpStatus.OK)){
                return false;
            }
            UserDetailsFromGoogle userDetails = new UserDetailsFromGoogle();
            userDetails.setEmail(googleAPIResp.getBody().getEmail());
            userDetails.setFirstName(googleAPIResp.getBody().getGiven_name());
            userDetails.setLastName(googleAPIResp.getBody().getFamily_name());
            userDetails.setSub(googleAPIResp.getBody().getSub());
            userDetails.setHd(googleAPIResp.getBody().getHd());
            request.getSession().setAttribute("user_details",userDetails);
            return true;
        } catch (HttpClientErrorException e){
            log.error("Http Client Error Exception Occurred: "+ e.getMessage());
            return false;
        } catch (Exception e){
            log.error("Exception Occurred: "+ e.getMessage());
            return false;
        }

//        UserDetailsFromGoogle userDetails = new UserDetailsFromGoogle();
//        userDetails.setEmail("user2@gmail.com");
//        userDetails.setEmail("user2");
//        userDetails.setLastName("He");
//        userDetails.setFirstName("Wen");
//        userDetails.setSub("sub");
//        request.getSession().setAttribute("user_details", userDetails);
//        return true;
    }

}
