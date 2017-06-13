package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.PatientService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;

/**
 *
 * @author Samuel Egger
 */
public class PatientManagementPresenter extends PresenterBase<PatientManagementView>{
    
    private final PatientService patientService;
    
    public PatientManagementPresenter(PatientManagementView view, PatientService patientService) {
        super(view);
        this.patientService = patientService;
    }
}
