package ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Rolf on 15/05/17.
 */
public class NewUserManagementPanel extends VerticalLayout {

    private TextField userNameField;
    private TextField passwordField;
    private CheckBox active; // True if a user is active, false if a user is deactivated
    private UserManagementPresenter presenter;
    private UserService userService = new InMemoyUserService();

    public NewUserManagementPanel(){
        presenter = new UserManagementPresenter(this);

        this.addComponent(userNameField = new TextField("User Name"));
        this.addComponent(passwordField = new TextField("Password"));
        this.addComponent(active = new CheckBox("Aktive"));
        this.addComponent(new Button("Create User", e -> {
            try {
                User newUsesr = presenter.createNewUser(getUserName(),getPassword(),stateUser());
                userService.addUser(newUsesr);
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            } catch (InvalidKeySpecException e1) {
                e1.printStackTrace();
            }

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
