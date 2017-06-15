package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.PatientService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;

public class PatientEditPresenter extends PresenterBase<PatientEditView>
{
	private PatientService mPatientService = null;

	public PatientEditPresenter(PatientEditView aView, PatientService aPatientService)
	{
		super(aView);
		mPatientService = aPatientService;
	}
}
