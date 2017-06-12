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
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.MainPagePresenter;
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

	IUserSession mSession;
	
	MainPagePresenter mPresenter;

	/**
	 * Instantiates a new main view.
	 *
	 * @param aNavigator the a navigator
	 */
	public MainPage(IUserSession aSession)
	{
		mSession = aSession;
		mPresenter = new MainPagePresenter(this, mSession);
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
		mPresenter.navigateTo(event.getParameters());
	}
	
	public void setContent(Component aView)
	{
		getContentPanel().setContent(aView);
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
			// TODO constants
			hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.HOME_PAGE, "button_home.png", "Home", mSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.PATIENT_PAGE, "button_patients.png", "Manage Patients", mSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.TIMETABLE_PAGE, "button_timetable.png", "Timetable", mSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.USERS_PAGE, "button_manageuser.png", "Manage Users", mSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.CREATE_USER_PAGE, "button_createuser.png", "Create new User", mSession.getNavigator()));
			hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.BILLS_PAGE, "button_patients.png", "See bills", mSession.getNavigator()));
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
			btnLogOff.addClickListener(event -> mSession.getNavigator().navigateTo(""));
		}
		
		return btnLogOff;
	}

}
