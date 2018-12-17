package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Vojtech Prusa
 *
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({RootWebContext.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    // TODO https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
    
    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        builder.inMemoryAuthentication().passwordEncoder(encoder)
        .withUser("user").password(encoder.encode("password")).roles("USER").and()
        .withUser("admin").password(encoder.encode("password")).roles("ADMIN");
        // ISSUE: too slow
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //builder.inMemoryAuthentication().passwordEncoder(encoder)
        //.withUser("user").password("{noop}password").roles("USER").and()
        //.withUser("admin").password("{noop}password").roles("ADMIN");
        
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .authorizeRequests()
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                //.loginPage("/login")
                .permitAll()
                .and()
            .httpBasic()
                .and()
            .logout()
                .permitAll();
       /* .authorizeRequests()
        .anyRequest().authenticated()
        .and()
    .formLogin()
        .and()
    .httpBasic();*/
    }

}