package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserManagementView;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class StartPage extends VerticalLayout implements View
{

	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();


	private UserService mUserService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO outsource to constants file?
	public static final String REF_URL = "";
	Navigator mNavigator;

	public StartPage(Navigator aNavigator, UserService aUserService)
	{
		mNavigator = aNavigator;
		mUserService = aUserService;

		setSizeFull();

		// TODO member
		Button button = new Button("Go to Main View", (event) -> mNavigator.navigateTo(Strings.REF_URL_MAIN_PAGE));
		addComponent(button);
		setComponentAlignment(button, Alignment.TOP_RIGHT);

		VerticalLayout layout = new VerticalLayout();
		
		// Username field
		TextField username = new TextField("Username");
		layout.addComponent(username);

		// Sha1PasswordService field
		PasswordField passwordField = new PasswordField("Sha1PasswordService");
		layout.addComponent(passwordField);
		

		// Login Button
		Button loginButton = new Button("Login");
		loginButton.addClickListener(e  ->{
		// Check Login credential
			log.debug("clicked login");
			if(checkLoginCredentials(username.getValue(),passwordField.getValue()))
			{

				mNavigator.navigateTo(Strings.REF_URL_MAIN_PAGE);
			}

		});

		layout.addComponent(new Label("")); // Hacky but works ;-)
		layout.addComponent(loginButton);
		
		addComponent(layout);
	}

	private boolean checkLoginCredentials(String username, String password) {
		try {
			log.debug("checking password");
			return mUserService.checkPassword(username,password);
		} catch (Exception e) {
			log.error("Exception while checking password.", e);
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