package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Rolf on 15/05/17.
 */
public class NewUserCreateView extends VerticalLayout {

    private TextField userNameField;
    private PasswordField passwordField;
    private CheckBox active; // True if a user is active, false if a user is deactivated
    private UserManagementPresenter presenter;
    private UserService userService = new InMemoyUserService();
    private Navigator navigator;


    /** The Constant log. */
    static final Logger log = Logger.getRootLogger();

    public NewUserCreateView(Navigator navigator){

        presenter = new UserManagementPresenter(this);
        this.navigator = navigator;

        this.addComponent(userNameField = new TextField("User Name"));
        this.addComponent(passwordField = new PasswordField("Password"));
        this.addComponent(active = new CheckBox("Aktive"));
        this.addComponent(new Button("Create User", e -> {
                User newUsesr = presenter.createNewUser(getUserName(),getPassword(),stateUser());
                // userService.addUser(newUsesr); TODO
                Notification.show("User created");
                log.debug("User created");

        }));
    }

    public String getUserName(){
        return userNameField.getValue();
    }
    public String getPassword(){
         return passwordField.getValue();
     }
    public boolean stateUser(){
        return active.getValue();
    }
}
