package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.util.PathParams;

public class WelcomeView extends MainPageContent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeView(IUserSession aUserSession)
	{
		this.addComponent(new Label("Welcome view."));
	}
	
	@Override
	public void updateParams(PathParams aParams)
	{
		// TODO retreive path params here
		
	}
}
