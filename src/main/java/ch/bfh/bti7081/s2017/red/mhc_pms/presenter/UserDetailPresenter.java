package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserDetailView;
import ch.bfh.bti7081.s2017.red.mhc_pms.viewModel.UserEditViewModel;
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



    public UserDetailPresenter(UserDetailView view, IUserSession session) {
        super(view, session);
        this.view = view;
        passwordService = session.getPasswordService();
        userService = session.getUserService();
    }


    public void persistUser(User newUser) {
        //ToDo user into database
        userService.saveOrUpdateUser(newUser);
        log.debug("user persisted");
    }

    public void enter() {


        userId = ""; //ToDo get user id from params

        //if no user id is passed to the view, presume new user creation.
        if(userId.equals("")){
            user = new User();
        //empty values loaded into UserDetailView fields, to avoid caching problems, where fields would remain
        //filled from last user editing.
        getView().setUserName("");
        getView().setPassword();
        getView().seteMail("");
        getView().setActive(false);

        }else {

        Long id = Long.parseLong(userId);
        user = userService.findUserById(id);

        //fill the user detail view, with the values of the referenced user
        getView().setUserName(user.getUsername());
        getView().setPassword();
        getView().seteMail(user.getEmail());
        getView().setActive(user.getState());
        }

        getView().getSave().setVisible(true);
        getView().getSave().setEnabled(true);

    }

    public void save() {
        //check if username or password are not empty
        if (getView().getUserNameField().isEmpty() || getView().getPasswordField().isEmpty()) {
            getView().fieldsEmpty();
            return;
        }

        UserEditViewModel viewModel = view.getViewModel();
        user.setUsername(viewModel.getUserName());
        user.setSalt(passwordService.createSalt());

        //The password is only to be saved, when it has been explicetly altered by the user.
        //Persisting the password without it being altered, would cause the dummy value to be
        //hashed, salted and persisted, we don't want that.
        if(getView().isPasswordFieldDirty()){
            user.setPasswordHash(passwordService.returnPasswordHashSalted(viewModel.getPassword(),user.getSalt()));
        }

        user.seteMail(viewModel.getEmail());
        user.setActive(viewModel.isActive());

        persistUser(user);
        getView().navigateToUserManagement();
    }

    public void cancel(){
        getView().navigateToUserManagement();
    }

}
