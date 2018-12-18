package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.dozer.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingItemServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.ShoppingListServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserServiceImpl;
import cz.muni.fi.pa165.skupina06.team02.rms.app.web.samples.SampleDataLoadingFacadeImpl;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private CustomUserDetailsService userDetailsService;
    
    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        super();
        this.userDetailsService = userDetailsService;
    }

    final static Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate: " + authentication.getName());
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (email == null) {
            return null;
        }
        // This should never ever happen
       /*if (userDetailsService == null) {
            return null;
        }*/
        
        UserDetails u = userDetailsService.loadUserByUsername(email);//userService.findByEmail(email);
        log.info("u: " );
        log.info("up: " +u.getPassword());
        log.info("p: " +password);
        if (u == null || UserServiceImpl.validatePassword(password,u.getPassword())) {
           // List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            //authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
           // token.setAuthenticated(true);
            return token;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.info("authenticate: " + authentication.getName());
        //return authentication.equals(UsernamePasswordAuthenticationToken.class);
        return true;
    }
}