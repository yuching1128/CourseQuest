package com.vt.coursequest.config;

import com.vt.coursequest.security.oauth.CustomOAuth2User;
import com.vt.coursequest.security.oauth.CustomOAuth2UserService;
import com.vt.coursequest.security.oauth.OAuth2LoginSuccessHandler;
import com.vt.coursequest.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: EugeneFeng
 * @date: 4/14/23 10:28 AM
 * @description: some desc
 */

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/oauth2/**").permitAll()
//                .antMatchers("/").authenticated()
////                .antMatchers("/api/university/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .oauth2Login()
//                    .userInfoEndpoint().userService(oAuth2UserService)
//                    .and()
//                    .successHandler(oAuth2LoginSuccessHandler)
//                .and()
//                .logout().permitAll();
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/oauth2/**").permitAll()
//                .antMatchers("/").authenticated()
//                .antMatchers("/api/university/**").authenticated()
                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                    .loginPage()
//                        .usernameParameter()
//                        .permitAll()
//                        .defaultSuccessUrl("/")
//                        .failureForwardUrl("/fail_login")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.println("user name:" + authentication.getName());
//                        UrlPathHelper urlPathHelper = new UrlPathHelper();
//                        String contextPath = urlPathHelper.getContextPath(request);
//                        response.sendRedirect(contextPath);
//                    }
//                })
//                .and()
//                .oauth2Login()
//                    .userInfoEndpoint().userService(oAuth2UserService)
//                    .and()
//                    .successHandler(oAuth2LoginSuccessHandler)
//                .and()
//                .logout().permitAll()
                .and().csrf().disable();
        http.cors().disable();

    }

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
}
