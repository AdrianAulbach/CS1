package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MainPageContent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;

public class PatientView extends MainPageContent
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientView()
	{
		this.addComponent(new Label("Patient view."));
	}

	@Override
	public void updateParams(PathParams aParams)
	{
		// TODO retreive path params here
		
	}
}
