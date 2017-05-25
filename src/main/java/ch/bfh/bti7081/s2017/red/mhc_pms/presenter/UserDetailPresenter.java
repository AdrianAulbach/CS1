package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserDetailView;

import org.apache.log4j.Logger;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserDetailPresenter extends PresenterBase<UserDetailView> {
    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    private UserDetailView view = null;
    private PasswordService passwordService = null;
    private UserService userService = null;

    // @Rolf: Der Konstruktor nimmt jetzt nur noch eine User session weil die user
    //        session alle objekte verwaltet und ggf. wiederverwenden kann
    public UserDetailPresenter(UserDetailView view, IUserSession session) {
        super(view,session);
        
        // @Rolf: die session übernimmt die erstellung dieser objekte, falls du etwas ändern willst
        //        siehe SessionFactory.createUserSession
        this.view = view;
        passwordService = session.getPasswordService();
        userService = session.getUserService();
    }


    public void createNewUser(String userName, String password, boolean active) {

        try {
            User newUser = new User();

            newUser.setUsername(view.getUserName());
            newUser.setActive(active);

            byte[] salt = passwordService.createSalt();
            newUser.setSalt(salt);
            log.debug("UserMan...Presenter set Salt: " + salt);
            // crate password hash for user using password and hash.
            byte[] passwordHash = passwordService.returnPasswordHashSalted(view.getPassword(), salt);
            String base64hash = java.util.Base64.getEncoder().encodeToString(passwordHash);
            newUser.setPasswordHash(base64hash);

            //ToDo implement reset email token
            String resetEmailToken = "abc";

            userService.saveOrUpdateUser(newUser);

            persistNewUser(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void persistNewUser(User newUser){
        //ToDo safe new user into database
    }

    public void changeUserName(User user, String newName){
        //ToDo persist new user name on database
    }

    public void changeEmail(User user, String newEmail){
        //ToDo persist new e mail
    }

    public void changeActive(User user, boolean active){
        //ToDo persist new user state
    }
}
