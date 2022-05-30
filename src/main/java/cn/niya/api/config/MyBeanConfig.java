package cn.niya.api.config;

import cn.niya.api.handlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * configuration class
 * something config need to be used
 */

@Configuration
//open security function
@EnableWebSecurity
//open autowired to use permission control
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MyBeanConfig {


    /**
     * encoder is not valid
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authentication() {
        return authentication -> authentication;
    }

    ;

    /**
     * security config
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // user login filter
        http
                .formLogin()
                // login success and return some message ex user info
                .successHandler(new SuccessHandler())
                // login failed and return some valid message ex account data wrong
                .failureHandler(new FailHandler())
        ;


        // filter some api and open some open api
        http
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .anyRequest().authenticated();

        // when some exception happened , will go to this function
        http.exceptionHandling().accessDeniedHandler(new DeniedHandler());

        // when user is not login will go to this function
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationNotLoginHandler());

        // user logout function
        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                // logout successfully , return some success message ex "logout success"
                .logoutSuccessHandler(new LogOutSuccessHandler());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        // take jwt to security , leave jwt to security
        http.addFilter(new JwtFilter(authentication()));

        // close csrf  ,
        http.csrf().disable();


        return http.build();
    }


}
