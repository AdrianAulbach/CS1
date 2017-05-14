package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;

import java.util.List;

/**
 * Definition of the user service functionalities.
 * 
 * @author Rolf Zurbrügg
 */
public interface UserService {

    /**
     * Finds a user with the given id.
     *
     * @param userId the user id
     * @return a user object or null
     */
    User findUserById(int userId);

    /**
     * Finds users which match the input filter
     * @param filter the filter criteria
     * @return a list of users or null
     */
    List<User> findUserByFilter(String filter);

    /**
     * Returns the user object corresponding to the user name
     * @param name the user name
     * @return a boolean, true if user exists
     */
    User getUserByUserName(String name) throws Exception;

    /**
     * Creates or updates a user
     * @param user a user object containing the delta
     */
    void createOrUpdateUser(User user);

    /**
     * deletes the user specified by the userId
     * @param userID the user id
     */
    void deleteUser(int userID);

    /**
     * checks if username and password match, returns true if there is a match
     * @param userName the user name
     * @param password the users password
     * @return
     */
    public boolean checkPassword(String userName, String password) throws Exception;



}
