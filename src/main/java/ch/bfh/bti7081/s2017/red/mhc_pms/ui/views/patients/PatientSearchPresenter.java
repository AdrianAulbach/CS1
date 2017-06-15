package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import java.util.List;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Patient;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PatientService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;

public class PatientSearchPresenter extends PresenterBase<PatientSearchView>
{
	private PatientService mPatientService = null; 

	public PatientSearchPresenter(PatientSearchView aView, PatientService aPatientService)
	{
		super(aView);
		mPatientService = aPatientService;
	}

    public void onInitialize() {
        List<Patient> patients= mPatientService.findPatientsByFilter(null);
        getView().setPatients(patients);
    }

    public void onSearch(){
        String filter = getView().getFilter();
        List<Patient> patients = mPatientService.findPatientsByFilter(filter);
        getView().setPatients(patients);
    }
}
