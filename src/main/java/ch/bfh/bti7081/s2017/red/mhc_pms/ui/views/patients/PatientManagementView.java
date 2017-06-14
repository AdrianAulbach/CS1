package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import com.vaadin.navigator.Navigator;

public class PatientManagementView extends MainPageContent<PatientManagementPresenter> 
{
	private Label lblTitle = null;
	
	private Button btnCreatePatient = null;
	private Button btnSearchPatient = null;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PatientManagementView(Navigator navigator)
    {
		super(navigator);
		initUI();
	}

	
	private void initUI()
	{
		lblTitle = new Label("Patient Management");
		
		btnSearchPatient = new Button("Search patients...");
		btnSearchPatient.addClickListener((e)->getNavigator().navigateTo(AppConstants.REF_URL_PATIENT_SEARCH_PAGE));
		btnSearchPatient.setWidth("300px");
		
		btnCreatePatient = new Button("Register new patient...");
		btnCreatePatient.addClickListener((e)->getNavigator().navigateTo(AppConstants.REF_URL_PATIENT_EDIT_PAGE));
		btnCreatePatient.setWidth("300px");
		
		addComponent(lblTitle);
		addComponent(btnSearchPatient);
		addComponent(btnCreatePatient);
	}


    @Override
    public void updateParams(PathParams aParams) {
        // TODO retreive path params here
    }
}
