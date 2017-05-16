/**
 * File: MHC_PMS::MainView.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels.*;
import org.apache.log4j.Logger;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs.IconButton;

/**
 * The Class MainView.
 */
@DesignRoot
public class MainView extends VerticalLayout implements View
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();

	/** The Constant REF_URL. */
	// TODO outsource to constants file?
	public static final String REF_URL = "main";

	/**
	 * The listener interface for receiving buttonNavigation events.
	 * The class that is interested in processing a buttonNavigation
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addButtonNavigationListener<code> method. When
	 * the buttonNavigation event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ButtonNavigationEvent
	 */
	// Menu navigation button listener
	class ButtonNavigationListener implements ClickListener
	{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The m menu item. */
		String mMenuItem;

		/**
		 * Instantiates a new button navigation listener.
		 *
		 * @param aMenuItem the a menu item
		 */
		public ButtonNavigationListener(String aMenuItem)
		{
			this.mMenuItem = aMenuItem;
		}

		/* (non-Javadoc)
		 * @see com.vaadin.event.MouseEvents.ClickListener#click(com.vaadin.event.MouseEvents.ClickEvent)
		 */
		@Override
		public void click(ClickEvent event)
		{
			log.debug("Click event received. Menu item: " + mMenuItem);

			// Navigate to a specific state
			mNavigator.navigateTo(MainView.REF_URL + "/" + mMenuItem);
		}
	}

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

	/** The member navigator. */
	Navigator mNavigator;

	/**
	 * Instantiates a new main view.
	 *
	 * @param aNavigator the a navigator
	 */
	public MainView(Navigator aNavigator)
	{
		mNavigator = aNavigator;
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
				case "welcome":
					getContentPanel().setContent(new WelcomePanel());
					break;
				case "patients":
					getContentPanel().setContent(new PatientPanel());
					break;
				case "timetable":
					getContentPanel().setContent(new TimetablePanel());
					break;
				case "users":
					getContentPanel().setContent(new UserManagementPanel(mNavigator));
					break;
				case "newUsers":
					getContentPanel().setContent(new NewUserCreatePanel(mNavigator));
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
			hlNavigationPanel.addComponent(new IconButton("button_welcome.png", new ButtonNavigationListener("welcome")));
			hlNavigationPanel.addComponent(new IconButton("button_patients.png", new ButtonNavigationListener("patients")));
			hlNavigationPanel.addComponent(new IconButton("button_timetable.png", new ButtonNavigationListener("timetable")));
			hlNavigationPanel.addComponent(new IconButton("button_timetable.png", new ButtonNavigationListener("users")));
			hlNavigationPanel.addComponent(new IconButton("button_timetable.png", new ButtonNavigationListener("newUsers")));
			
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
			btnLogOff.addClickListener(event -> mNavigator.navigateTo(""));
		}
		
		return btnLogOff;
	}

}
