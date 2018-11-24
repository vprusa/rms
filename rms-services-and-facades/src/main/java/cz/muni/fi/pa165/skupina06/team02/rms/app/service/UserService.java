package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 *
 *         An interface that defines a service access to the {@link User}
 *         entity.
 * 
 */
@Service
public interface UserService {

    /**
     * Register the given user with the given unencrypted password.
     * 
     * @param u
     * @param unencryptedPassword
     */
    void registerUser(User u, String unencryptedPassword);

    /**
     * Get all registered users
     *
     * @return
     */
    List<User> getAllUsers();

    /**
     * Try to authenticate a user. Return true only if the hashed password matches
     * the records.
     *
     * @param u
     * @param password
     * @return
     */
    boolean authenticate(User u, String password);

    /**
     * Check if the given user is lessee.
     *
     * @param u
     * @return
     */
    boolean isLessee(User u);

    /**
     * Finds user by its id
     * 
     * @param userId value
     * @return user
     */
    User findById(Long userId);

    /**
     * Finds user by its email
     *
     * @param email value
     * @return user
     */
    User findByEmail(String email);

}
