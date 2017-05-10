package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rolf Zurbrügg
 */
public class InMemoyUserService implements UserService {

    private final static List<User> users = new ArrayList<>();
    
    static {
        // initialize list with dummy data for testing
        User user = new User();
        user.setId(5l);
        users.add(user);
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public User findUserById(int userId) {
        throw new UnsupportedOperationException();
    }
    
}
