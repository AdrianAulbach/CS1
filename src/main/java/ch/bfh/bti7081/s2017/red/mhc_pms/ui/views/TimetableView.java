package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;

public class TimetableView extends MainPageContent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimetableView(IUserSession aUserSession)
	{
		this.addComponent(new Label("Timetable view."));
	}
	
	@Override
	public void updateParams(PathParams aParams)
	{
		// TODO retreive path params here
		
	}
}
