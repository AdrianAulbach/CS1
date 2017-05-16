package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels.UserManagementPanel;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class StartView extends VerticalLayout implements View
{

	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();


	private UserService userService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO outsource to constants file?
	public static final String REF_URL = "";
	Navigator mNavigator;

	public StartView(Navigator aNavigator, UserService userService)
	{
		mNavigator = aNavigator;
		this.userService = userService;

		setSizeFull();

		// TODO member
		Button button = new Button("Go to Main View", (event) -> mNavigator.navigateTo(MainView.REF_URL));
		addComponent(button);
		setComponentAlignment(button, Alignment.TOP_RIGHT);

		// Username field
		TextField username = new TextField("Username");
		addComponent(username);
		setComponentAlignment(username, Alignment.MIDDLE_CENTER);

		// Sha1PasswordService field
		PasswordField passwordField = new PasswordField("Sha1PasswordService");
		addComponent(passwordField);
		setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);

		// Login Button
		Button loginButton = new Button("Login");
		loginButton.addClickListener(e  ->{
		// Check Login credential
			log.debug("clicked login");
			if(checkLoginCredentials(username.getValue(),passwordField.getValue()))
			{

				mNavigator.navigateTo(MainView.REF_URL);
			}

		});

		addComponent(loginButton);
		setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
	}

	private boolean checkLoginCredentials(String username, String password) {
		try {
			log.debug("checking password");
			return userService.checkPassword(username,password);
		} catch (Exception e) {
			log.error(e);
			Notification.show("Wrong Username or Password", Notification.Type.ERROR_MESSAGE);
			return false;
		}
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		Notification.show("Welcome to MHC-PMS Application!");
	}
}