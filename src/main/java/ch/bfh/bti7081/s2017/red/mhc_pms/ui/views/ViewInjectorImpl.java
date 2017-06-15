package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPagePresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPagePresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing.BillingPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing.BillingView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientEditPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientEditView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientManagementView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientSearchPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients.PatientSearchView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.timetable.TimetableView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserDetailPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserDetailView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserManagementView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.welcome.WelcomeView;

/**
 * Default implementation of the ViewInjector interface.
 *
 * @author Aleistar Markóczy, Samuel Egger
 */
public final class ViewInjectorImpl implements ViewInjector {

    // Navigation and HTTP request
    private Navigator mNavigator;
    private final VaadinRequest mVaadinRequest;

    // Pages: Init on constructor
    private StartPage mStartPage = null;
    private MainPage mMainPage = null;

    // Views: Lazy initialization
    private PatientManagementView mPatientView = null;
    private PatientSearchView mPatientSearchView = null;
    private PatientEditView mPatientEditView = null;
    private TimetableView mTimetableView = null;
    private UserDetailView mUserDetailView = null;
    private UserManagementView mUserManagementView = null;
    private WelcomeView mWelcomeView = null;
    private BillingView mBillingView = null;

    // Services: Must be assigned by ViewInjectorFactory
    private UserService mUserService = null;
    private PasswordService mPasswordService = null;
    private PatientService mPatientService = null;
    private BillingService mBillingService = null;

    private final UI mUi;

    public ViewInjectorImpl(UI ui, VaadinRequest vaadinRequest) {
        mUi=ui;
        mVaadinRequest=vaadinRequest;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getNavigator()
     */
    @Override
    public Navigator getNavigator() {
        if(mNavigator == null){
            mNavigator = new Navigator(mUi, mUi);
            mNavigator.addView("", getStartPage());
            mNavigator.addView(AppConstants.REF_URL_MAIN_PAGE, getMainPage());
        }
        return mNavigator;
    }

    /**
     * Gets the vaadin request.
     *
     * @return the vaadin request
     */
    public VaadinRequest getVaadinRequest() {
        return mVaadinRequest;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getStartPage()
     */
    @Override
    public StartPage getStartPage() {
        if (mStartPage == null) {
            mStartPage = new StartPage(mNavigator);
            mStartPage.setPresenter(new StartPagePresenter(mStartPage, mUserService));
        }
        return mStartPage;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getMainPage()
     */
    @Override
    public MainPage getMainPage() {
        if (mMainPage == null) {
            mMainPage = new MainPage(mNavigator);
            mMainPage.setPresenter(new MainPagePresenter(mMainPage, this));
        }
        return mMainPage;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getPatientView()
     */
    @Override
    public PatientManagementView getPatientManagementView() {
        if (mPatientView == null) {
            mPatientView = new PatientManagementView(mNavigator);
            mPatientView.setPresenter(new PatientManagementPresenter(mPatientView, mPatientService));
        }
        return mPatientView;
    }
    

    @Override
    public PatientEditView getPatientEditView() {
        if (mPatientEditView == null) {
        	mPatientEditView = new PatientEditView(mNavigator);
        	mPatientEditView.setPresenter(new PatientEditPresenter(mPatientEditView, mPatientService));
        }
        return mPatientEditView;
    }
    

    @Override
    public PatientSearchView getPatientSearchView() {
        if (mPatientSearchView == null) {
        	mPatientSearchView = new PatientSearchView(mNavigator);
        	mPatientSearchView.setPresenter(new PatientSearchPresenter(mPatientSearchView, mPatientService));
        }
        return mPatientSearchView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getTimetableView()
     */
    @Override
    public TimetableView getTimetableView() {
        if (mTimetableView == null) {
            mTimetableView = new TimetableView(mNavigator);
            // TODO: Set presenter
        }
        return mTimetableView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserDetailView()
     */
    @Override
    public UserDetailView getUserDetailView() {
        if (mUserDetailView == null) {
            mUserDetailView = new UserDetailView(mNavigator);
            mUserDetailView.setPresenter(new UserDetailPresenter(mUserDetailView, mUserService, mPasswordService));
        }
        return mUserDetailView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserManagementView()
     */
    @Override
    public UserManagementView getUserManagementView() {
        if (mUserManagementView == null) {
            mUserManagementView = new UserManagementView(mNavigator);
            mUserManagementView.setPresenter(new UserManagementPresenter(mUserManagementView, mUserService));
        }
        return mUserManagementView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getWelcomeView()
     */
    @Override
    public WelcomeView getWelcomeView() {
        if (mWelcomeView == null) {
            mWelcomeView = new WelcomeView(mNavigator);
            // TODO: Set presenter
        }
        return mWelcomeView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getWelcomeView()
     */
    @Override
    public BillingView getBillingView() {
        if (mBillingView == null) {
            mBillingView = new BillingView(mNavigator);
            mBillingView.setPresenter(new BillingPresenter(mBillingView, mBillingService));
        }
        return mBillingView;
    }
    /**
     * Sets the user service.
     *
     * @param aUserService the user service
     */
    public void setUserService(UserService aUserService) {
        mUserService = aUserService;
    }

    /**
     * Sets the password service.
     *
     * @param aPasswordService the password service
     */
    public void setPasswordService(PasswordService aPasswordService) {
        mPasswordService = aPasswordService;
    }

    /**
     * Sets the patient service.
     *
     * @param aPatientService the patient service
     */
    public void setPatientService(PatientService aPatientService) {
        mPatientService = aPatientService;
    }

    /**
     * Sets the billing service.
     *
     * @param aBillingService the billing service
     */
    public void setBillingService(BillingService aBillingService) {
        mBillingService = aBillingService;
    }
}
