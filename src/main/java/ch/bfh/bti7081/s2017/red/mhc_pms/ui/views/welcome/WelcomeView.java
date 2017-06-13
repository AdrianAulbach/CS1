package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.welcome;

import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;

public class WelcomeView extends MainPageContent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WelcomeView() {
        this.addComponent(new Label("Welcome view."));
    }

    @Override
    public void updateParams(PathParams aParams) {
        // TODO retreive path params here
    }
}
