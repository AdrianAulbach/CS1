package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;

public class PatientEditView extends MainPageContent<PatientEditPresenter>
{

	public PatientEditView(Navigator aNavigator)
	{
		super(aNavigator);
		initUI();
	}

	private void initUI()
	{
		addComponent(new Label("Edit View"));
	}

	@Override
	public void updateParams(PathParams aParams)
	{
		// TODO Auto-generated method stub
		
	}

}
