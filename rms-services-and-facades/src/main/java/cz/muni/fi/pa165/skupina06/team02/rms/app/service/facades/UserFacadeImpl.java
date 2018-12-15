package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.UserService;

import java.util.Collection;

/**
 * @author Vojtech Prusa
 *
 *         User Facade
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BeanMappingService beanMappingService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade#findUserById(java
     * .lang.Long)
     */
    @Override
    public UserDTO findUserById(Long userId) {
        User user = userService.findById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade#findUserByEmail(
     * java.lang.String)
     */
    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userService.findByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade#registerUser(cz.
     * muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO, java.lang.String)
     */
    @Override
    public void registerUser(UserDTO userDTO, String unencryptedPassword) {
        User userEntity = beanMappingService.mapTo(userDTO, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        userDTO.setId(userEntity.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade#getAllUsers()
     */
    @Override
    public Collection<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.getAllUsers(), UserDTO.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cz.muni.fi.pa165.skupina06.team02.rms.app.facade.UserFacade#authenticate(cz.
     * muni.fi.pa165.skupina06.team02.rms.app.dto.UserAuthenticateDTO)
     */
    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        return userService.authenticate(userService.findById(u.getUserId()), u.getPassword());
    }

    @Override
    public boolean deleteUser(Long userId) {
        return false;
    }

}
