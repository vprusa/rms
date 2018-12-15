package cz.muni.fi.pa165.skupina06.team02.rms.app.facade;

import java.util.Collection;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.UserDTO;


/**
 * @author Vojtech Prusa
 *
 * Facade interface for User
 * 
 * @UserFacadeImpl
 * 
 */
public interface UserFacade {
	
	/**
	 * Find user by id
	 * 
	 * @param userId value
	 * @return UserDTO instance
	 */
	UserDTO findUserById(Long userId);

	/**
	 * Find user by email
	 * 
	 * @param email of user
	 * @return UserDTO instance
	 */
	UserDTO findUserByEmail(String email);
	
	/**
	 * Register the given user with the given unencrypted password.
     *
	 * @param UserDTO instance
	 * @param password instance
	 */
	void registerUser(UserDTO u, String password);

	/**
	 * Get all registered users
	 * 
	 * @return collection of all UserDTO
	 */
	Collection<UserDTO> getAllUsers();

	/**
	 * Try to authenticate a user. Return true only if the hashed password matches the records.
	 * 
	 * @param UserAuthenticateDTO instance 
	 * @return boolean true on success
	 */
	boolean authenticate(UserAuthenticateDTO u);
	
	boolean deleteUser(Long userId);

}
