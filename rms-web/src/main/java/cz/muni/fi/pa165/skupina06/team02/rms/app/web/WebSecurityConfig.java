package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import org.dozer.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserServiceImpl;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Vojtech Prusa
 *
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ RootWebContext.class })
@ComponentScan(basePackages = { "cz.muni.fi.pa165.skupina06.team02.rms.app.web",
        "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.assemblers",
        "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest" , "cz.muni.fi.pa165.skupina06.team02.rms.app.service" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO
    // https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
 
 
    // @Autowired
    // private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /*
     * @Autowired public WebSecurityConfig(UserDetailsService userDetailsService) {
     * super(); this.userDetailsService = userDetailsService; }
     */

    // @Autowired
    // private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    // private SimpleUrlAuthenticationFailureHandler myFailureHandler = new
    // SimpleUrlAuthenticationFailureHandler();

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user1").password("user1").authorities("USER_ROLE");
        // if(userDetailsService!=null)
        //auth.userDetailsService(userDetailsService);
       // auth.authenticationProvider(new CustomAuthenticationProvider());

    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        // builder.userDetailsService(userDetailsService);
        // if(userDetailsService!=null)
        // builder.userDetailsService(userDetailsService);
        // TODO switch encoders .. ISSUE: BCryptPasswordEncoder is too slow (TODO
        // optimalize)
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        // builder.inMemoryAuthentication().passwordEncoder(encoder)
        // .withUser("user").password(encoder.encode("password")).roles("USER").and()
        // .withUser("admin").password(encoder.encode("password")).roles("ADMIN");

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        builder.inMemoryAuthentication().passwordEncoder(encoder).withUser("user").password("{noop}password")
                .roles("USER").and().withUser("admin").password("{noop}password").roles("ADMIN");
       // builder.authenticationProvider(new CustomAuthenticationProvider());
        builder.authenticationProvider(customAuthenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http
                /*
                 * .authorizeRequests() //.antMatchers("/**").permitAll()
                 * .anyRequest().authenticated() .and() .formLogin() //.loginPage("/login")
                 * .permitAll() .and() .httpBasic() .and() .logout() .permitAll();
                 */

                /*
                 * .csrf().disable() .exceptionHandling() //
                 * .authenticationEntryPoint(restAuthenticationEntryPoint) .and()
                 * .authorizeRequests() .antMatchers("/rest/foos").authenticated()
                 * .antMatchers("/rest/admin/**").hasRole("ADMIN") .and() .formLogin()
                 * //.successHandler(mySuccessHandler) // .failureHandler(myFailureHandler)
                 * .and() .logout();
                 */
                //.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
        http.authorizeRequests().anyRequest().authenticated()
        .and()
        .httpBasic();
    }

}