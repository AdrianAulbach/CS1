package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;
import org.apache.log4j.Logger;

import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.ViewInjector;

public class MainPagePresenter extends PresenterBase<MainPage> {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();
    
    private final ViewInjector viewInjector;

    public MainPagePresenter(MainPage mainPage, ViewInjector viewInjector) {
        super(mainPage);
        this.viewInjector = viewInjector;
    }

    public void navigateTo(String aPath) 
    {
        String lPath = aPath;
        PathParams lParams = null;
        if (aPath.contains("?")) {
            lPath = aPath.substring(0, aPath.indexOf('?'));
            lParams = new PathParams(aPath);
        }

//        getView().setContent(viewInjector.getWelcomeView());

        if (lPath == null) {
            getView().setContent(new Label("Request could not be resolved."));
            return;
        } else {
            MainPageContent lContent = null;

            switch (lPath) {
                case AppConstants.EMPTY:
                case AppConstants.HOME_PAGE:
                    lContent = viewInjector.getWelcomeView();
                    break;
                case AppConstants.PATIENT_PAGE:
                    lContent = viewInjector.getPatientManagementView();
                    break;
                case AppConstants.PATIENT_EDIT_PAGE:
                    lContent = viewInjector.getPatientEditView();
                    break;
                case AppConstants.PATIENT_SEARCH_PAGE:
                    lContent = viewInjector.getPatientSearchView();
                    break;
                case AppConstants.TIMETABLE_PAGE:
                    lContent = viewInjector.getTimetableView();
                    break;
                case AppConstants.USERS_PAGE:
                    log.debug("Navigate to users management view");
                    lContent = viewInjector.getUserManagementView();
                    break;
                case AppConstants.CREATE_USER_PAGE:
                    lContent = viewInjector.getUserDetailView();
                    break;
                case AppConstants.BILLS_PAGE:
                    lContent = viewInjector.getBillingView();
                    break;
                default:
                    log.error("Request could not be resolved.");
                    getView().setContent(new Label("Request could not be resolved. Page was:" +lPath));
            }

            if (lParams != null) {
                lContent.updateParams(lParams);
            }
            getView().setContent(lContent);
            lContent.onEnter();
        }
    }
}
