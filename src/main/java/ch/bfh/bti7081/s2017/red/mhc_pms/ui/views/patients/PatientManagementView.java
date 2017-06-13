package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;
import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import com.vaadin.navigator.Navigator;

public class PatientManagementView extends MainPageContent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PatientManagementView(Navigator navigator) {
        super(navigator);
        this.addComponent(new Label("Patient view."));
    }

    @Override
    public void updateParams(PathParams aParams) {
        // TODO retreive path params here
    }
}
