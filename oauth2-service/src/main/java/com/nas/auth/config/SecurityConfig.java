package com.nas.auth.config;


import com.nas.auth.security.AuthEntryPointJwt;
import com.nas.auth.security.UserAuthenticationFilter;
import com.nas.auth.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt pointJwt;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // for admin
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disabling csrf
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/v1/auth/**").permitAll()
                /*.antMatchers(HttpMethod.PUT, "/v1/customers/**").access("hasAuthority('ROLE_USER')")
                .antMatchers("/v1/customers/**", "/v1/order/**", "/products/**", "/v1/address/**").access("hasAuthority('ROLE_ADMIN')")*/
                .and()
                .exceptionHandling().authenticationEntryPoint(pointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // Expose authentication manager bean
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public UserAuthenticationFilter userAuthenticationFilter(){
        return new UserAuthenticationFilter();
    }
}
