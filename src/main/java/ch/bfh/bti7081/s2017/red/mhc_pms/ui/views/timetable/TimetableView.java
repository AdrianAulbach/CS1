package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.timetable;

import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;
import com.vaadin.navigator.Navigator;

public class TimetableView extends MainPageContent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TimetableView(Navigator navigator) {
        super(navigator);
        this.addComponent(new Label("Timetable view."));
    }

    @Override
    public void updateParams(PathParams aParams) {
        // TODO retreive path params here
    }
}
