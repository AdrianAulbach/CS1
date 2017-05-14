package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

public class StartView extends VerticalLayout implements View
{


	private UserService userService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO outsource to constants file?
	public static final String REF_URL = "";
	Navigator mNavigator;

	public StartView(Navigator aNavigator)
	{
		userService = new InMemoyUserService();


		mNavigator = aNavigator;

		setSizeFull();

		// TODO member
		Button button = new Button("Go to Main View", (event) -> mNavigator.navigateTo(MainView.REF_URL));
		addComponent(button);
		setComponentAlignment(button, Alignment.TOP_RIGHT);

		// Username field
		TextField username = new TextField("Username");
		addComponent(username);
		setComponentAlignment(username, Alignment.MIDDLE_CENTER);

		// Password field
		PasswordField passwordField = new PasswordField("Password");
		addComponent(passwordField);
		setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);

		// Login Button
		Button loginButton = new Button("Login");
		loginButton.addClickListener(e  ->{
		// Check Login credential
			try {
				if(checkLoginCredentials(username.getValue(),passwordField.getValue()))
                {
                    mNavigator.navigateTo(MainView.REF_URL);
                }

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		addComponent(loginButton);
		setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
	}

	private boolean checkLoginCredentials(String username, String password) throws Exception {
		try {
			return userService.checkPassword(username,password);
		} catch (Exception e) {
			Notification.show("Wrong Username or Password", Notification.Type.ERROR_MESSAGE);
		} finally {
			return false;
		}
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		Notification.show("Welcome to MHC-PMS Application!");
	}
}