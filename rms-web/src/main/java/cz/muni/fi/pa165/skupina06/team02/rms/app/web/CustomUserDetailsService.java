package cz.muni.fi.pa165.skupina06.team02.rms.app.web;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserServiceImpl;

/**
 * @author Vojtech Prusa
 *
 */
@Service 
//("userDetailsService")
//@Transactional
@ComponentScan(basePackageClasses= {cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserServiceImpl.class})
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl usersService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    /*    User user = new User();
        user.setEmail("test");
        user.setPassword("test");*/
      User user = usersService.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorityList = //user.getRoles()
                    Collections.singletonList("USER_ROLE").stream()
                    .map(role -> new SimpleGrantedAuthority(role/*.getRole()*/))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(email, user.getPassword(), true/*user.isEnabled()*/, true, true, true, authorityList);
        }
        return null;
    }
}