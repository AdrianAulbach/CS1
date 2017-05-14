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
        User rolf = new User("Rolf", "password1", "rolf_237@hotmail.com", true);
        User admin = new User("admin", "1", "support.mhcpms@redware.com", true);

        users.add(rolf);
        users.add(admin);
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public User findUserById(int userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findUserByFilter(String filter) {
        return null;
    }

    @Override
    public User getUserByUserName(String name) throws Exception  {

        try {
            for(User u: users){
                if(u.getUsername().equals(name)){
                    return u;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new User();
        }
    }

    @Override
    public void createOrUpdateUser(User user) {

    }

    @Override
    public void deleteUser(int userID) {

    }

    @Override
    public boolean checkPassword(String userName, String password) throws Exception {

        User userTestPassword = getUserByUserName(userName);

        //test if the password matches the specified user
        // Todo implemnt hash
        String enteredPasswordHash = password;
        if(userTestPassword.getPasswordHash().equals(enteredPasswordHash)){
            return true;
        }
        return false;
    }

}
