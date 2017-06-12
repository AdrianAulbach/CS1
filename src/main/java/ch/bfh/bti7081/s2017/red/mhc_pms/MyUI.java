package ch.bfh.bti7081.s2017.red.mhc_pms;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.SessionFactory;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.FileUtil;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or
 * some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI
{
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getRootLogger();

	private static Navigator navigator = null;
	
	public MyUI()
	{

		// Init log4j properties
		PropertyConfigurator.configure(FileUtil.getApplicationPath()+File.separator+"log4j.properties");
	}
	
	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		log.info("Session started for client "+vaadinRequest.getRemoteHost());
		
		IUserSession lSession = SessionFactory.createUserSession(this, vaadinRequest);
		
        getPage().setTitle("MHC-PMS Application");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new StartPage(lSession));
        navigator.addView(AppConstants.REF_URL_MAIN_PAGE, new MainPage(lSession));
        log.debug("All views established.");
	}
	
	
	protected void oldInit()
	{
		// Init log4j properties
		PropertyConfigurator.configure(FileUtil.getApplicationPath());
		
		Logger.getRootLogger().debug("Hello Debugger");
		
		
		final VerticalLayout layout = new VerticalLayout();

		final TextField name = new TextField();
		name.setCaption("Type your name here:");

		Button button = new Button("Click Me");
		button.addClickListener(e ->
		{
			layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
			// Log messages ausgeben
			log.debug("User has clicked button.");
//			log.warn("bad!");
//			log.error("even bader!");
//			log.fatal("KRITISCH!!!!");
		});

		layout.addComponents(name, button);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet
	{
		private static final long serialVersionUID = 1L;
	}
}
