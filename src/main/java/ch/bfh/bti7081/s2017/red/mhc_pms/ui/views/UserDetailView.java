package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.util.PathParams;

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
public class UserDetailView extends MainPageContent {

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
        
        // @Rolf: Dieser Button zeigt das beispiel einer navigation
        //
        this.addComponent(new Button("Example: Navigation with parameters", e -> {
            navigateWithParamsExample();
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
        presenter.navigateTo(Strings.REF_URL_USERS_PAGE);
    }
    
	//
	// @Rolf: Hier ein beispiel wie die neue navigation funktioniert, ich mache es
	//        mit einem dummy wert den du nachher löschen kannst, hier die schrittweise
	//        Erklärung:
	//
    private String dummy = null;
    public void navigateWithParamsExample()
    {
    	// 1. Erstelle eine instanz der pathparams klasse (leerer konstruktor)
    	PathParams pp = new PathParams();
    	
    	// 2. Füge den Wert hinzu mit der methode addParam(key,value)
    	pp.addParam(Strings.PARAM_NAME_DUMMY, "tegscht");
    	// > NB: Ist schön wenn man die parameter namen als generelle konstante ablegt (z.B. in der Strings klasse)
    	
    	// 3. Konvertiere die PathParams klasse in einen pfad (in diesem Beispiel navigieren wir wieder hier her)
    	presenter.navigateTo(Strings.REF_URL_CREATE_USER_PAGE + pp.getParamString());
    }
    
	@Override
	public void updateParams(PathParams aParams)
	{
		// 4. Nutze nun die updateParams methode um die parameter wieder zu holen
		dummy = aParams.getParam(Strings.PARAM_NAME_DUMMY);
		log.debug("Dummy value is: " + dummy);
		// NB: Falls du in eine andere Klasse navigierst, benutzt du dort die updateParams methode um die Parameter zu laden
		dummy = null;
		// 5. Siehe selbst: Gehe zur UserDetailView und drücke den button "Example: Navigation with parameters"
	}
}
