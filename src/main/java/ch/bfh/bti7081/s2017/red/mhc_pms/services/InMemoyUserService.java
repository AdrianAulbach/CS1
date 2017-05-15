package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Sha1PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rolf Zurbrügg
 */
public class InMemoyUserService implements UserService {

    private final static List<User> users = new ArrayList<>();
    private static UserManagementPresenter userManagementPresenter = new UserManagementPresenter();
    private static PasswordService passwordService = new Sha1PasswordService();

    
    static {
        // initialize list with dummy data for testing
        User rolf = null;
        User admin = null;
        try {
            rolf = new User();
            rolf.setPasswordHash("e9mLE/mItY03LmCBw7NUtkVZmTXHURvXhMj6uYFJWbE="); // is password1 hashed with salt and base64

                    userManagementPresenter.createNewUser("Rolf", "password1", "rolf_237@hotmail.com", true);
            admin = userManagementPresenter.createNewUser("admin", "1", "support.mhcpms@redware.com", true);
            System.out.println("Rolfs Sha1PasswordService hash: "+rolf.getPasswordHash());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

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
    public User getUserByUserName(String name)   {

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
    public boolean checkPassword(String userName, String password) {

        User userTestPassword = getUserByUserName(userName);
        byte[] userSalt = userTestPassword.getSalt();
        String enteredPasswordHash = passwordService.returnPasswordHashSalted(password,userSalt).toString();

        //test if the password matches the specified user

        if(userTestPassword.getPasswordHash().equals(enteredPasswordHash)){
            return true;
        }
        return false;
    }

}
