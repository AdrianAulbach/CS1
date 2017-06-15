/**
 * File: ch.bfh.bti7081.s2017.red::ViewInjector.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import com.vaadin.navigator.Navigator;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing.BillingView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientEditView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientManagementView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientSearchView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.timetable.TimetableView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserDetailView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserManagementView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.welcome.WelcomeView;

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
    public PatientManagementView getPatientManagementView();


    /**
     * Gets the patient search view.
     *
     * @return the patient search view
     */
    public PatientSearchView getPatientSearchView();
    
    /**
     * Gets the patient edit view.
     *
     * @return the patient edit view
     */
    public PatientEditView getPatientEditView();
    
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
