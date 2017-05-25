package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import org.apache.log4j.Logger;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserDetailPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserDetailView extends VerticalLayout {

    private TextField userNameField;
    private PasswordField passwordField;
    private CheckBox active; // True if a user is active, false if a user is deactivated
    private UserDetailPresenter presenter;
    private UserService userService = null;

    /** The Constant log. */
    static final Logger log = Logger.getRootLogger();

    public UserDetailView(IUserSession session){

        presenter = new UserDetailPresenter(this, session);
        userService = session.getUserService(); // @Rolf: den user service kannst du neu aus der session nehmen
        
        this.addComponent(userNameField = new TextField("User Name"));
        this.addComponent(passwordField = new PasswordField("Password"));
        this.addComponent(active = new CheckBox("Aktive"));
        this.addComponent(new Button("Create User", e -> {
                presenter.createNewUser(getUserName(),getPassword(),stateUser());
                // userService.addUser(newUsesr); TODO migrate to presenter
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
