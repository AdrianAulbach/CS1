package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.Sha1PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserServiceImpl;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserDetailView;

import org.apache.log4j.Logger;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserDetailPresenter extends PresenterBase<UserDetailView> {
    /** The Constant log. */
    static final Logger log = Logger.getRootLogger();

    private UserDetailView view = null;
    private PasswordService passwordService = new Sha1PasswordService();
    private UserService userService = new UserServiceImpl();

    public UserDetailPresenter(UserDetailView view){
        super(view);
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
