package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Patient;
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
	
	public void saveOrUpdate()
	{
		PatientEditViewModel model = getView().getViewModel();
		
		Patient p = new Patient();
		p.setFirstName(model.getFirstName());
		p.setLastName(model.getLastName());
		p.setStreet(model.getStreet());
		p.setCity(model.getCity());
		p.setPhone(model.getPhone());
		p.setMobile(model.getMobile());
		p.setEmail(model.getEmail());
		
		mPatientService.saveOrUpdatePatient(p);
	}
}
