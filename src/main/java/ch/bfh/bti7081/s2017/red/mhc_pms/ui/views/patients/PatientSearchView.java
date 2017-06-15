package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import java.util.List;

import org.apache.log4j.Logger;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Patient;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;

public class PatientSearchView extends MainPageContent<PatientSearchPresenter>
{

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    private Grid<Patient> patientGrid;
    private TextField txtFilter;
    private Long selectedPatientID;
    private PathParams pp = new PathParams();

	public PatientSearchView(Navigator aNavigator)
	{
		super(aNavigator);
		initUI();
	}

	private void initUI()
	{
		patientGrid = new Grid("Patients");
        Button createNewUser = new Button("Register New Patient");
        createNewUser.addClickListener(e -> {
            getNavigator().navigateTo(AppConstants.REF_URL_PATIENT_EDIT_PAGE);
        });

        txtFilter = new TextField("Patient");

        Button search = new Button("Search");
        search.addClickListener(e -> {
            presenter.onSearch();
            patientGrid.removeAllColumns();
            patientGrid.addColumn(Patient::getFirstName).setCaption("First Name");
            patientGrid.addColumn(Patient::getLastName).setCaption("Last Name");
            patientGrid.addColumn(Patient::getStreet).setCaption("Street");
        });

        //ToDo implement user editing
        Button edit = new Button("Edit");
        edit.addClickListener(e -> 
        {
        	if(selectedPatientID!=null && !selectedPatientID.equals(""))
        	{
        		pp.addParam("id",selectedPatientID.toString());
        		getNavigator().navigateTo(AppConstants.REF_URL_PATIENT_EDIT_PAGE+pp.getParamString());
        	}
        	else
        	{
        		log.warn("Nothing selected");
        		Notification.show("Nothing selected");
        	}
        });

        //Grid config
        patientGrid.addItemClickListener(e -> {
            Patient selectedPatient = e.getItem();
            selectedPatientID = selectedPatient.getId();
        });

        patientGrid.setSelectionMode(Grid.SelectionMode.SINGLE); //Alow only for single select on grid

        HorizontalLayout searchAndCreate = new HorizontalLayout();
        searchAndCreate.addComponent(txtFilter);
        searchAndCreate.addComponent(search);
        searchAndCreate.addComponent(createNewUser);
        searchAndCreate.setComponentAlignment(search, Alignment.BOTTOM_CENTER);
        searchAndCreate.setComponentAlignment(txtFilter, Alignment.BOTTOM_CENTER);
        searchAndCreate.setComponentAlignment(createNewUser, Alignment.BOTTOM_CENTER);

        this.addComponent(searchAndCreate);
        this.addComponent(patientGrid);
        this.addComponent(edit);
	}
	
    public void setPatients(List<Patient> aPatients) {
        patientGrid.setItems(aPatients);
    }

    public String getFilter() {
        return txtFilter.getValue();
    }

    public Long getSelectedPatientID() {
        return selectedPatientID;
    }

    public void setSelectedPatientID(Long patID) {
        this.selectedPatientID = patID;
    }

	@Override
	public void updateParams(PathParams aParams)
	{
		String loadAll = aParams.getParam("loadall");
		if(loadAll!=null)
		{
			txtFilter.setValue("");
			presenter.onSearch();
			patientGrid.removeAllColumns();
            patientGrid.addColumn(Patient::getFirstName).setCaption("First Name");
            patientGrid.addColumn(Patient::getLastName).setCaption("Last Name");
            patientGrid.addColumn(Patient::getStreet).setCaption("Street");
		}
	}

}
