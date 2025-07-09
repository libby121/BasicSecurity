package com.example.basicSecurity.configuration;

import com.example.basicSecurity.service.MyUserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebConfig {

    @Autowired
    private MyUserServiceDetails detailsService;

    @Autowired
    private JwtFilter jWTfilter;

      public WebConfig() {

     }

     /*
     customizing the security filters.
     every request is set to be authenticated.
     csrf is disabled since we make http stateless since-
     memory is not involved. the server won't recognise
     user by session.
      */
    @Bean
    public SecurityFilterChain filter(HttpSecurity httpsec) throws Exception {
        httpsec.csrf(customizer -> customizer.disable());
        httpsec.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                addFilterBefore(jWTfilter, UsernamePasswordAuthenticationFilter.class);
        httpsec.authorizeHttpRequests(requst->
                requst.requestMatchers("login","register").permitAll()
                .anyRequest().authenticated());
        httpsec.httpBasic(Customizer.withDefaults());


        return httpsec.build();



    }

    @Bean
    public AuthenticationProvider authp (){
        DaoAuthenticationProvider dao =new DaoAuthenticationProvider();
         dao.setPasswordEncoder(new BCryptPasswordEncoder(15));


        dao.setUserDetailsService(detailsService);
        return dao;
    }


    @Bean
    public AuthenticationManager manager (AuthenticationConfiguration config)  {
        try {
            return config.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

