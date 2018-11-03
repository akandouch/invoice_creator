package com.akandouch.invoicec.microservice.login.conf;

import com.akandouch.invoicec.microservice.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.time.Duration;

import static com.akandouch.invoicec.microservice.login.User.Role.ADMIN;
import static com.akandouch.invoicec.microservice.login.User.Role.USER;

@Configuration
@EnableMongoHttpSession(maxInactiveIntervalInSeconds = 300) //todo set it to 1h
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()

                .antMatchers(HttpMethod.DELETE, "/**").hasAnyAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.POST, "/invoice/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.POST, "/upload/**").hasAuthority(ADMIN.getAuthority())
//                .antMatchers(HttpMethod.GET, "/upload/**").hasAuthority(USER.getAuthority()) todo add visible flag & check in rest controller
                .antMatchers(HttpMethod.GET, "/invoice/**").hasAnyAuthority(ADMIN.getAuthority(), USER.getAuthority())
                .antMatchers(HttpMethod.POST, "/settings/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.GET, "/settings/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.POST, "/product/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.GET, "/product/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.POST, "/invoiceprofile/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.GET, "/invoiceprofile/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.GET, "/statistics/**").hasAuthority(ADMIN.getAuthority())
                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority(USER.getAuthority())
                .antMatchers(HttpMethod.POST, "/user/**").hasAuthority(USER.getAuthority())
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .requestCache()
                .requestCache(new NullRequestCache()).and()
                .httpBasic().and()
                .formLogin().disable()
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userRepository)
                .passwordEncoder(passwordEncoder)
        ;
    }

    @Bean
    public JdkMongoSessionConverter jdkMongoSessionConverter() {
        return new JdkMongoSessionConverter(Duration.ofMinutes(5));
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


}
