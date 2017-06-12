package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;

import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.StartPagePresenter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

public class StartPage extends VerticalLayout implements View
{

	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();


	private IUserSession mUserSession;
	private StartPagePresenter presenter;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StartPage(IUserSession aUserSession)
	{
		mUserSession = aUserSession;
		presenter = new StartPagePresenter(this, aUserSession);

		setSizeFull();

		// TODO member
		Button button = new Button("Go to Main View", (event) -> mUserSession.getNavigator().navigateTo(AppConstants.REF_URL_MAIN_PAGE));
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
			if(presenter.checkLogin(username.getValue(),passwordField.getValue()))
			{
				// TODO StartPagePresenter
				mUserSession.getNavigator().navigateTo(AppConstants.REF_URL_MAIN_PAGE);
			}else{
				Notification.show("Wrong Username or Password", Notification.Type.ERROR_MESSAGE);
			}

		});

		layout.addComponent(new Label("")); // Hacky but works ;-)
		layout.addComponent(loginButton);
		
		addComponent(layout);
	}



	@Override
	public void enter(ViewChangeEvent event)
	{
		Notification.show("Welcome to MHC-PMS Application!");
	}
}