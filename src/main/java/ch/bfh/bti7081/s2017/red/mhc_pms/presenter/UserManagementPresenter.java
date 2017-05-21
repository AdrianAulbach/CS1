package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Sha1PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserServiceImpl;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.NewUserCreateView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserManagementView;

import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserManagementPresenter {
    /** The Constant log. */
    static final Logger log = Logger.getRootLogger();

    private NewUserCreateView view = null;
    private UserManagementView view2 = null;
    private PasswordService passwordService = new Sha1PasswordService();
    private UserService userService = new UserServiceImpl();

    public UserManagementPresenter(NewUserCreateView view){
        this.view = view;
    }

    public UserManagementPresenter(UserManagementView view2){
        this.view2 =view2;
    }

    public User createNewUser(String userName, String password, boolean active) {

        try {
            User newUser = new User();

            newUser.setUsername(view.getUserName());
            newUser.setActive(active);

            byte[] salt = passwordService.createSalt();
            newUser.setSalt(salt);
            log.debug("UserMan...Presenter set Salt: "+ salt);
            // crate password hash for user using password and hash.
            byte[] passwordHash = passwordService.returnPasswordHashSalted(view.getPassword(), salt);
            String base64hash = java.util.Base64.getEncoder().encodeToString(passwordHash);
            newUser.setPasswordHash(base64hash);

            //ToDo implement reset email token
            String resetEmailToken = "abc";

            userService.saveOrUpdateUser(newUser);
            
            return newUser;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
