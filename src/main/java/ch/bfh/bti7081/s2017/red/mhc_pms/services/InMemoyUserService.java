package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Sha1PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels.NewUserManagementPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rolf Zurbrügg
 */
public class InMemoyUserService implements UserService {

    private final static List<User> users = new ArrayList<>();
    private static UserManagementPresenter userManagementPresenter = new UserManagementPresenter(new NewUserManagementPanel());
    private static PasswordService passwordService = new Sha1PasswordService();
    
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
        byte[] passwordHash = passwordService.returnPasswordHashSalted(password,userSalt);
        String enteredPasswordHashBase64 = java.util.Base64.getEncoder().encodeToString(passwordHash);


        //test if the password matches the specified user

        if(userTestPassword.getPasswordHash().equals(enteredPasswordHashBase64)){
            return true;
        }
        return false;
    }

    @Override
    public void addUser(User newUser) {
        users.add(newUser);
    }


}
