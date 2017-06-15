package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserDetailPresenter extends PresenterBase<UserDetailView> {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    private final PasswordService passwordService;
    private UserService userService;
    private String userId = "";
    private User user = null;
    private boolean newUser = false;

    public UserDetailPresenter(UserDetailView view, UserService userService, PasswordService passwordService) {
        super(view);
        this.userService = userService;
        this.passwordService = passwordService;
    }


    public void enter(){
    if(getView().getId() != null){
            userId = getView().getId();
        }

        //if no user id is passed to the view, presume new user creation.
        if (userId.equals("")) {
            user = new User();
            //empty values loaded into UserDetailView fields, to avoid caching problems, where fields would remain
            //filled from last user editing.
            getView().setUserName("");
            getView().setPassword();
            getView().seteMail("");
            getView().setActive(false);
            newUser = true;

        } else {

            Long id = Long.parseLong(userId);
            user = userService.findUserById(id);

            //fill the user detail view, with the values of the referenced user
            getView().setUserName(user.getUsername());
            getView().setPassword();
            getView().seteMail(user.getEmail());
            getView().setActive(user.getActive());
            newUser = false;
        }
    }

    public void onInitialize() {
        enter();
    }

    public void save() {
        //check if username or password are not empty
        if (getView().getUserNameField().isEmpty() || getView().getPasswordField().isEmpty()) {
            getView().fieldsEmpty();
            return;
        }

        //check if user already exists ToDo disabled, because check causes user mutations to not work
        if(newUser){
            if(userService.getUserByUserName(getView().getUserNameField().getValue())!=null) {
                getView().showUserExistsMessage();
                return;
            }
        }


        UserEditViewModel viewModel = getView().getViewModel();
        user.setUsername(viewModel.getUserName());
        user.setSalt(passwordService.createSalt());

        //The password is only to be saved, when it has been explicetly altered by the user.
        //Persisting the password without it being altered, would cause the dummy value to be
        //hashed, salted and persisted, we don't want that.
        if (getView().isPasswordFieldDirty()) {
            user.setPasswordHash(passwordService.returnPasswordHashSalted(viewModel.getPassword(), user.getSalt()));
        }

        user.seteMail(viewModel.getEmail());
        user.setActive(viewModel.isActive());

        persistUser(user);
        getView().navigateToUserManagement();
    }

    public void cancel() {
        getView().navigateToUserManagement();
    }

    private void persistUser(User newUser) {
        userService.saveOrUpdateUser(newUser);
        log.debug("user persisted");
    }

    public void deleteUser(){
        try {
            userService.deleteUser(user.getId());
        } catch (NullPointerException e){
            getView().showNoUserMessage();
        }
        finally {
            getView().navigateToUserManagement();
        }

    }
}
