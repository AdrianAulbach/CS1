package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;

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
}
