package com.security.mysql.config;

import com.security.mysql.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/")
                .permitAll()
            .antMatchers("/registration")
                .permitAll()
            .antMatchers("/admin/**")
                .hasAuthority("ADMIN").anyRequest().authenticated()
                .and()
            .csrf().disable()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()
                .failureUrl("/login?error=true")
                .successHandler(myAuthenticationSuccessHandler)
                    .usernameParameter("user_name")
                    .passwordParameter("password")
//                .defaultSuccessUrl("/admin/home")
//                    .usernameParameter("user_name")
//                    .passwordParameter("password")
            .and()
            .logout()
                .logoutSuccessUrl("/login")
          .and().exceptionHandling()
            .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
