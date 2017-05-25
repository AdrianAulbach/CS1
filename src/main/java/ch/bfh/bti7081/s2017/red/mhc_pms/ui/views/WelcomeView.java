package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;

public class WelcomeView extends VerticalLayout
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WelcomeView(IUserSession aUserSession)
	{
		this.addComponent(new Label("Welcome view."));
	}
}
