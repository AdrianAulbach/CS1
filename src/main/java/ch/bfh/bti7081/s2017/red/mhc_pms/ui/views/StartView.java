package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class StartView extends VerticalLayout implements View
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Navigator mNavigator;

	public StartView(Navigator aNavigator)
	{
		mNavigator = aNavigator;

		setSizeFull();

		// TODO member
		Button button = new Button("Go to Main View", (event) -> mNavigator.navigateTo(MainView.REF_URL));
		addComponent(button);
		setComponentAlignment(button, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		Notification.show("Welcome to MHC-PMS Application!");
	}
}