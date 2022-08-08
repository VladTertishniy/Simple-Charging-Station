//package com.extrawest.simplechargingstationtertyshniy.security;
//
//import com.extrawest.simplechargingstationtertyshniy.filter.AuthenticationProcessFilter;
//import com.extrawest.simplechargingstationtertyshniy.filter.AuthenticationResponseFilter;
//import com.extrawest.simplechargingstationtertyshniy.filter.IpCheckFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final AuthenticationProvider authenticationProvider;
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoderBean passwordEncoder;
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/charge_points/**").permitAll()
////                .antMatchers("/locations/**").authenticated()
////                .antMatchers("/users/**").denyAll()
////                .antMatchers("/charging_transactions/**").denyAll()
////                .and().formLogin()
////                .and().httpBasic();
//
//        http.csrf().disable()/*.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()*/
//                .addFilterBefore(new IpCheckFilter(), BasicAuthenticationFilter.class)
//                .addFilterAt(new AuthenticationProcessFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new AuthenticationResponseFilter(), BasicAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/locations/**").authenticated()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().httpBasic()
//                .and().cors().configurationSource(request -> {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
//                config.setAllowedMethods(Collections.singletonList("POST"));
//                return config;
//        });
//
////        http.cors().configurationSource(request -> {
////            CorsConfiguration config = new CorsConfiguration();
////            config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
////            config.setAllowedMethods(Collections.singletonList("POST"));
////            return config;
////        });
//    }
//
//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    /*
//    3.1 Config users using inMemoryAuthentication.
//     */
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user")
////                .password("user")
////                .roles("BUYER");
////    }
//
////    @Bean
////    @Override
////    protected UserDetailsService userDetailsService() {
////        return new InMemoryUserDetailsManager(User.withUsername("user")
////                .password(passwordEncoder().encode("user"))
////                .roles("BUYER").build());
////    }
////
////    @Bean
////    protected InMemoryUserDetailsManager inMemoryUserDetailsManager() {
////        return new InMemoryUserDetailsManager(User.withUsername("user")
////                .password(passwordEncoder().encode("user"))
////                .roles("BUYER").build());
////    }
//
//    /*
//    3.2 Create users inside the DB (create local DB) as per JdbcUserDetailsManager class.
//     */
//
////    @Bean
////    protected UserDetailsManager userDetailsManager(DataSource dataSource) {
////        return new JdbcUserDetailsManager(dataSource);
////    }
////
////    @Autowired
////    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) throws Exception {
////        builder.jdbcAuthentication().dataSource(dataSource).withUser("user")
////                .password("user").roles("BUYER");
////    }
//
//    /*
//    4 Testing different types of password encoder
//     */
//
////    @Bean
////    protected PasswordEncoder passwordEncoder() {
////        return NoOpPasswordEncoder.getInstance();
////    }
//
////    @Bean
////    protected PasswordEncoder passwordEncoder() {
////        return new StandardPasswordEncoder();
////    }
//
////    @Bean
////    protected PasswordEncoder passwordEncoder() {
////        return new Pbkdf2PasswordEncoder();
////    }
//
////    @Bean
////    protected PasswordEncoder passwordEncoder() {
////        return new SCryptPasswordEncoder();
////    }
//
//
//}
