package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import org.apache.log4j.Logger;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.controls.NavigatorUI;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels.PatientPanel;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels.TimetablePanel;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels.WelcomePanel;

@DesignRoot
public class MainView extends VerticalLayout implements View
{
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getRootLogger();

	// TODO outsource to constants file?
	public static final String MAIN_VIEW = "main";
	
	// Menu navigation button listener
	class ButtonNavigationListener implements ClickListener
	{
		private static final long serialVersionUID = 1L;
		String mMenuItem;

		public ButtonNavigationListener(String aMenuItem)
		{
			this.mMenuItem = aMenuItem;
		}

		@Override
		public void buttonClick(ClickEvent event)
		{
			// Navigate to a specific state
			mNavigator.navigateTo(NavigatorUI.MAIN_VIEW + "/" + mMenuItem);
		}
	}

	VerticalLayout menuContent;
	Panel equalPanel;
	Button logout;

	Navigator mNavigator;

	public MainView(Navigator aNavigator)
	{
		Design.read(this);

		mNavigator = aNavigator;
		menuContent.addComponent(new Button("Welcome", new ButtonNavigationListener("welcome")));
		menuContent.addComponent(new Button("Patients", new ButtonNavigationListener("patients")));
		menuContent.addComponent(new Button("Timetable", new ButtonNavigationListener("timetable")));
		menuContent.addComponent(new Button("Dummy", new ButtonNavigationListener("dummy")));

		// Allow going back to the start
		logout.addClickListener(event -> mNavigator.navigateTo(""));
	}
	

	@Override
	public void enter(ViewChangeEvent event)
	{
		log.info("User requested page: "+event.getParameters());
		if (event.getParameters() == null)
		{
			equalPanel.setContent(new Label("Request could not be resolved."));
			return;
		}
		else
		{
			switch(event.getParameters())
			{
				case "":
				case "welcome":
					equalPanel.setContent(new WelcomePanel());
					break;
				case "patients":
					equalPanel.setContent(new PatientPanel());
					break;
				case "timetable":
					equalPanel.setContent(new TimetablePanel());
					break;
				case "dummy":
					equalPanel.setContent(new Label("Hello Nävigeischön!"));
					break;
				default:
					log.error("Request could not be resolved.");
					break;
			}
			
//			equalPanel.setContent(new ContentViewer(event.getParameters()));
		}
	}

}
