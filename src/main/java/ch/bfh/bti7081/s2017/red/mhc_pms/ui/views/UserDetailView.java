package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.navigator.ViewChangeListener;
import org.apache.log4j.Logger;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserDetailPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserDetailView extends VerticalLayout {

    private TextField userNameField;
    private boolean userNameFieldDirty = false;
    private PasswordField passwordField;
    private boolean passwordFieldDirty = false;
    private TextField eMailField;
    private boolean eMailFieldDiry = false;
    private CheckBox active;
    private boolean stateDirty = false;
    private UserDetailPresenter presenter;
    private UserService userService = null;
    private Button save;
    private Button cancel;

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    public UserDetailView(IUserSession session) {

        presenter = new UserDetailPresenter(this, session);
        userService = session.getUserService(); // @Rolf: den user service kannst du neu aus der session nehmen

        this.addComponent(userNameField = new TextField("User Name"));
        userNameField.addValueChangeListener(e -> {
            userNameFieldDirty = true;
            save.setEnabled(true);
        });

        this.addComponent(passwordField = new PasswordField("Password"));
        passwordField.addValueChangeListener(e -> {
            passwordFieldDirty = true;
            save.setEnabled(true);
        });

        this.addComponent(eMailField = new TextField("E-Mail"));
        eMailField.addValueChangeListener(e -> {
            eMailFieldDiry = true;
            save.setEnabled(true);
        });

        this.addComponent(active = new CheckBox("State"));
        active.addValueChangeListener(e -> {
            stateDirty = true;
            save.setEnabled(true);
        });


        this.addComponent(save = new Button("Save", e -> {
            presenter.save();
        }));

        this.addComponent(cancel = new Button("Cancel", e -> {
            presenter.cancel();
        }));

        ViewChangeListener.ViewChangeEvent event = null; //ToDo get proper event
        presenter.enter(event);
    }

    public String getUserName() {
        return userNameField.getValue();
    }

    public void setUserName(String userName) {
        this.userNameField.setValue(userName);
    }

    public String getPassword() {
        return passwordField.getValue();
    }

    public void setPassword() {
        this.passwordField.setValue("dummy value");
    }

    public String geteMail() {
        return eMailField.getValue();
    }

    public void seteMail(String eMail) {
        this.eMailField.setValue(eMail);
    }

    public boolean getActiveVal() {
        return active.getValue();
    }

    public void setActive(boolean state) {
        this.active.setValue(state);
    }

    public boolean isUserNameFieldDirty() {
        return userNameFieldDirty;
    }

    public void setUserNameFieldDirty(boolean userNameFieldDirty) {
        this.userNameFieldDirty = userNameFieldDirty;
    }

    public boolean isPasswordFieldDirty() {
        return passwordFieldDirty;
    }

    public void setPasswordFieldDirty(boolean passwordFieldDirty) {
        this.passwordFieldDirty = passwordFieldDirty;
    }

    public boolean iseMailFieldDiry() {
        return eMailFieldDiry;
    }

    public void seteMailFieldDiry(boolean eMailFieldDiry) {
        this.eMailFieldDiry = eMailFieldDiry;
    }

    public boolean isStateDirty() {
        return stateDirty;
    }

    public void setStateDirty(boolean stateDirty) {
        this.stateDirty = stateDirty;
    }

    public TextField getUserNameField() {
        return userNameField;
    }

    public void setUserNameField(TextField userNameField) {
        this.userNameField = userNameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public TextField geteMailField() {
        return eMailField;
    }

    public void seteMailField(TextField eMailField) {
        this.eMailField = eMailField;
    }


    public CheckBox getActive() {
        return active;
    }

    public void setActive(CheckBox active) {
        this.active = active;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public void fieldsEmpty() {
        Notification.show("Username or Password may not be empty");
    }

    public void userCreated() {
        Notification.show("User Created");
    }

    public void userSaved() {
        Notification.show("User Saved");
    }

    public void navigateToUserManagement(){
        presenter.navigateTo(Strings.REF_URL_MAIN_PAGE + "/users");
    }

}
