package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import org.apache.derby.vti.Restriction.AND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
        "cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest", "cz.muni.fi.pa165.skupina06.team02.rms.app.service" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // TODO
    // https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
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

        /*http.csrf().disable().exceptionHandling() //
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        http.authorizeRequests().antMatchers("/rest/**").authenticated();
        http.formLogin().successHandler(mySuccessHandler).failureHandler(myFailureHandler).permitAll();
        http.logout().permitAll();
*/
        // .authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
       // http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().formLogin().permitAll().and()
       // .logout().permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.formLogin().permitAll().and().logout().permitAll();
    }

}