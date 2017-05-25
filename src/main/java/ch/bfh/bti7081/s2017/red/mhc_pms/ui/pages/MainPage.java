/**
 * File: MHC_PMS::MainView.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import org.apache.log4j.Logger;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs.NavigationIconButton;

/**
 * The Class MainView.
 */
@DesignRoot
public class MainPage extends VerticalLayout implements View
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();

	/** The VerticalLayout main panel. */
	private VerticalLayout vlMainPanel;
	
	/** The VerticalLayout menu and content panel. */
	private VerticalLayout vlMenuAndContentPanel;
	
	/** The Panel menu panel. */
	private Panel pnMenuPanel;
	
	/** The HorizontalLayout navigation panel. */
	private HorizontalLayout hlNavigationPanel;
	
	/** The Panel content panel. */
	private Panel pnContentPanel;
	
	/** The Button log off. */
	private Button btnLogOff;

	IUserSession mUserSession;

	/**
	 * Instantiates a new main view.
	 *
	 * @param aNavigator the a navigator
	 */
	public MainPage(IUserSession aUserSession)
	{
		mUserSession = aUserSession;
		initUI();
	}
	
	/**
	 * Where the application's navigation is managed: on request the panel can be created/loaded and
	 * displayed.
	 *
	 * @param event the event
	 */
	@Override
	public void enter(ViewChangeEvent event)
	{
		log.info("User requested page: " + event.getParameters());
		if (event.getParameters() == null)
		{
			getContentPanel().setContent(new Label("Request could not be resolved."));
			return;
		}
		else
		{
			switch (event.getParameters())
			{
				case "":
				case "home":
					getContentPanel().setContent(mUserSession.getWelcomeView());
					break;
				case "patients":
					getContentPanel().setContent(mUserSession.getPatientView());
					break;
				case "timetable":
					getContentPanel().setContent(mUserSession.getTimetableView());
					break;
				case "users":
					getContentPanel().setContent(mUserSession.getUserManagementView());
					break;
				case "createuser":
					getContentPanel().setContent(mUserSession.getUserDetailView());
					break;
				case "bills":
					getContentPanel().setContent(mUserSession.getBillingView());
					break;
				case "dummy":
					getContentPanel().setContent(new Label("Hello Nävigeischön!"));
					break;
				default:
					log.error("Request could not be resolved.");
					break;
			}

			// equalPanel.setContent(new ContentViewer(event.getParameters()));
		}
	}
	
	/*-------- UI Related Initializers --------*/
	
	private void initUI()
	{
		// Hierarchy:
		// 
		// Vertical Layout: Size Full  -> mainPanel
		//	     Vertical Layout: Size Full, expand -> menuAndContentPanel
		//	         Panel:"Menu": Height full width auto -> menuPanel
		//	             Horizontal Layout -> navigationPanel
		//	                 IconButtons: Welcome ...
		//	         Panel:"Content" -> contentPanel
		//	     Button:"Logout" -> logOffBtn
		//
		//
		//
		addComponent(getMainPanel());
		setSizeFull();
		setExpandRatio(getMainPanel(), 1.0f);	
	}
	
	/**
	 * Gets the main panel.
	 *
	 * @return the main panel
	 */
	private VerticalLayout getMainPanel()
	{
		if(vlMainPanel==null)
		{
			vlMainPanel = new VerticalLayout();
			vlMainPanel.setSizeFull();
			vlMainPanel.addComponent(getMenuAndContentPanel());
			vlMainPanel.setExpandRatio(getMenuAndContentPanel(), 1.0f);
			vlMainPanel.addComponent(getLogOffButton());
		}
		
		return vlMainPanel;
	}

	/**
	 * Gets the menu and content panel.
	 *
	 * @return the menu and content panel
	 */
	private VerticalLayout getMenuAndContentPanel()
	{
		if(vlMenuAndContentPanel==null)
		{
			vlMenuAndContentPanel = new VerticalLayout();
			vlMenuAndContentPanel.addComponent(getMenuPanel());
			vlMenuAndContentPanel.addComponent(getContentPanel());
			vlMenuAndContentPanel.setExpandRatio(getContentPanel(), 1.0f);
			vlMenuAndContentPanel.setHeight("100%");
		}
		
		return vlMenuAndContentPanel;
	}

	/**
	 * Gets the menu panel.
	 *
	 * @return the menu panel
	 */
	private Panel getMenuPanel()
	{
		if(pnMenuPanel==null)
		{
			pnMenuPanel = new Panel("Menu");
			pnMenuPanel.setContent(getNavigationPanel());
		}
		
		return pnMenuPanel;
	}

	/**
	 * Gets the navigation panel.
	 *
	 * @return the navigation panel
	 */
	private HorizontalLayout getNavigationPanel()
	{
		if(hlNavigationPanel==null)
		{
			hlNavigationPanel = new HorizontalLayout();
			hlNavigationPanel.setMargin(true);
			hlNavigationPanel.addComponent(new NavigationIconButton("home", "button_home.png", "Home", mUserSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton("patients", "button_patients.png", "Manage Patients", mUserSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton("timetable", "button_timetable.png", "Timetable", mUserSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton("users", "button_manageuser.png", "Manage Users", mUserSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton("createuser", "button_createuser.png", "Create new User", mUserSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton("bills", "button_patients.png", "See bills", mUserSession.getNavigator()));
		}
		
		return hlNavigationPanel;
	}

	/**
	 * Gets the content panel.
	 *
	 * @return the content panel
	 */
	private Panel getContentPanel()
	{
		if(pnContentPanel==null)
		{
			pnContentPanel = new Panel("Content");
			pnContentPanel.setSizeFull();
			pnContentPanel.setHeight("100%");
		}
		
		return pnContentPanel;
	}

	/**
	 * Gets the log off button.
	 *
	 * @return the log off button
	 */
	private Button getLogOffButton()
	{
		if(btnLogOff==null)
		{
			btnLogOff = new Button("Logout");
			btnLogOff.addClickListener(event -> mUserSession.getNavigator().navigateTo(""));
		}
		
		return btnLogOff;
	}

}
