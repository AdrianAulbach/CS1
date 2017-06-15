package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import org.apache.log4j.Logger;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;
import com.vaadin.navigator.Navigator;

/**
 * Created by Rolf on 15/05/17.
 */
public class UserDetailView extends MainPageContent<UserDetailPresenter> {

    private TextField userNameField;
    private PasswordField passwordField;
    private boolean passwordFieldDirty;
    private TextField eMailField;
    private CheckBox active;
    private Button save;
    private Button cancel;
    private Button delete;
    private String id="";
    private PathParams pp = new PathParams();

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    public UserDetailView(Navigator navigator) {
        super(navigator);

        //getId();
        //presenter.onInitialize();
        this.addComponent(userNameField = new TextField("User Name"));
        this.addComponent(passwordField = new PasswordField("Password"));

        passwordField.addValueChangeListener(e -> {
            passwordFieldDirty = true;
        });

        this.addComponent(eMailField = new TextField("E-Mail"));
        this.addComponent(active = new CheckBox("Active"));
        this.addComponent(save = new Button("Save", e -> {
            presenter.save();
        }));

        this.addComponent(cancel = new Button("Cancel", e -> {
            presenter.cancel();
        }));

        this.addComponent(delete = new Button("Delete", e->{
            presenter.deleteUser();

        }));
    }

    @Override
    public void setPresenter(UserDetailPresenter presenter) {
        this.presenter = presenter;
        presenter.onInitialize();
    }

    public void setUserName(String userName) {
        this.userNameField.setValue(userName);
    }

    public String getPassword() {
        return passwordField.getValue();
    }

    public void setPassword() {
        this.passwordField.setValue("dummy value");
        setPasswordFieldDirty(false); //loading the dummy value is not allowed to cause the password field, to be marked as dirty
    }

    public void seteMail(String eMail) {
        this.eMailField.setValue(eMail);
    }

    public void setActive(boolean state) {
        this.active.setValue(state);
    }

    public TextField getUserNameField() {
        return userNameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getSave() {
        return save;
    }

    public void fieldsEmpty() {
        Notification.show("Username or Password may not be empty");
    }

    public UserEditViewModel getViewModel() {
        UserEditViewModel viewModel = new UserEditViewModel();
        viewModel.setUserName(userNameField.getValue());
        viewModel.setPassword(passwordField.getValue());
        viewModel.setEmail(eMailField.getValue());
        viewModel.setActive(active.getValue());
        return viewModel;
    }

    public void navigateToUserManagement() {
        getNavigator().navigateTo(AppConstants.REF_URL_MAIN_PAGE + "/users");
    }

    public boolean isPasswordFieldDirty() {
        return passwordFieldDirty;
    }

    public void setPasswordFieldDirty(boolean passwordFieldDirty) {
        this.passwordFieldDirty = passwordFieldDirty;
    }

    @Override
    public void updateParams(PathParams params) {
        id = params.getParam("id");
    }

    @Override
    public void onEnter(){presenter.enter();}

    public String  getId(){
        return id;
    }

    public void showUserExistsMessage(){
        Notification.show("Username is already taken please enter a different user name");
    }

    public void showNoUserMessage(){
        Notification.show("Please select a User", Notification.Type.ERROR_MESSAGE);
    }


}
