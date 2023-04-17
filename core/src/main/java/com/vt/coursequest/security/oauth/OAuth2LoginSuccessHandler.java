package com.vt.coursequest.security.oauth;

import com.vt.coursequest.entity.AuthenticationProvider;
import com.vt.coursequest.entity.User;
import com.vt.coursequest.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author: EugeneFeng
 * @date: 4/17/23 1:53 AM
 * @description: some desc
 */

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserDataService userDataService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getName();
        System.out.println("Current User's email is:" + email);

        Optional<User> user = userDataService.findUserByEmail(email);
        if (user.isPresent()) {
            //update existing user
            userDataService.updateNewUserOAuthLoginSuccess(user.get(), email, name, AuthenticationProvider.GOOGLE);
        }
        else {
            //register as a new user
            userDataService.createNewUserOAuthLoginSuccess(email, name, AuthenticationProvider.GOOGLE);
        }



        super.onAuthenticationSuccess(request, response, authentication);
    }
}
