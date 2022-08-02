package com.extrawest.simplechargingstationtertyshniy.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/charge_points/**").permitAll()
//                .antMatchers("/locations/**").authenticated()
//                .antMatchers("/users/**").denyAll()
//                .antMatchers("/charging_transactions/**").denyAll()
//                .and().formLogin()
//                .and().httpBasic();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    3.1 Config users using inMemoryAuthentication.
     */

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .roles("BUYER");
//    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("BUYER").build());
//    }
//
//    @Bean
//    protected InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        return new InMemoryUserDetailsManager(User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("BUYER").build());
//    }

    /*
    3.2 Create users inside the DB (create local DB) as per JdbcUserDetailsManager class.
     */

//    @Bean
//    protected UserDetailsManager userDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Autowired
//    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) throws Exception {
//        builder.jdbcAuthentication().dataSource(dataSource).withUser("user")
//                .password("user").roles("BUYER");
//    }

    /*
    4 Testing different types of password encoder
     */

//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new StandardPasswordEncoder();
//    }

//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder();
//    }

//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new SCryptPasswordEncoder();
//    }


}
