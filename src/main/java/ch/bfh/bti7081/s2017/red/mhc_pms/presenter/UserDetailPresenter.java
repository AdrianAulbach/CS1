package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserDetailView;
import com.vaadin.navigator.ViewChangeListener;
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
    private String userId = "";
    private User user = null;
    private String params;


    // @Rolf: Der Konstruktor nimmt jetzt nur noch eine User session weil die user
    //        session alle objekte verwaltet und ggf. wiederverwenden kann
    public UserDetailPresenter(UserDetailView view, IUserSession session) {
        super(view, session);

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

            persistUser(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void persistUser(User newUser) {
        //ToDo user into database
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {

        params = event.getParameters();
        userId = ""; //ToDo get user id from params

        if (!userId.equals("")) {
            Long id = Long.parseLong(userId);
            user = userService.findUserById(id);
            getView().setUserName(user.getUsername());
            getView().setUserNameFieldDirty(false);
            getView().setPassword();
            getView().setPasswordFieldDirty(false);
            getView().seteMail(user.getEmail());
            getView().seteMailFieldDiry(false);
            getView().setActive(user.getState());
            getView().setStateDirty(false);
        }else{
            user = new User();
        }
    }

    public void save(){
        if (!getView().getUserNameField().isEmpty() && getView().isUserNameFieldDirty()) setUserName();
        if (!getView().geteMailField().isEmpty() && getView().iseMailFieldDiry()) setUserEmail();
        if (!getView().getPasswordField().isEmpty() && getView().isPasswordFieldDirty()) setUserPasssword();
        if (getView().isStateDirty()) setUserState();
    }

    public void setUserName(){
        user.setUsername(getView().getUserName());
    }

    public void setUserPasssword(){
        byte[] salt = passwordService.createSalt();
        user.setSalt(salt);
        byte[] passwordHash = passwordService.returnPasswordHashSalted(getView().getPassword(), salt);
        String base64hash = java.util.Base64.getEncoder().encodeToString(passwordHash);
        user.setPasswordHash(base64hash);
    }

    public void setUserEmail(){
        user.seteMail(getView().geteMail());
    }

    public void setUserState(){
        user.setActive(getView().getActiveVal());
    }
}
