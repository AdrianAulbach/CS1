package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.welcome.WelcomeView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.timetable.TimetableView;
import com.vaadin.navigator.Navigator;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PatientService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserDetailView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserManagementView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing.BillingView;

/**
 * Defines methods to get injected views.
 * 
 * @author Aleistar Markóczy, Samuel Egger
 */
public interface ViewInjector {

    /**
     * Gets the navigator.
     *
     * @return the navigator
     */
    public Navigator getNavigator();

    /**
     * Gets the start page.
     *
     * @return the start page
     */
    public StartPage getStartPage();

    /**
     * Gets the main page.
     *
     * @return the main page
     */
    public MainPage getMainPage();

    /**
     * Gets the patient view.
     *
     * @return the patient view
     */
    public PatientView getPatientView();

    /**
     * Gets the timetable view.
     *
     * @return the timetable view
     */
    public TimetableView getTimetableView();

    /**
     * Gets the user detail view.
     *
     * @return the user detail view
     */
    public UserDetailView getUserDetailView();

    /**
     * Gets the user management view.
     *
     * @return the user management view
     */
    public UserManagementView getUserManagementView();

    /**
     * Gets the welcome view.
     *
     * @return the welcome view
     */
    public WelcomeView getWelcomeView();

    /**
     * Gets the billing view.
     *
     * @return the billing view
     */
    public BillingView getBillingView();
}
