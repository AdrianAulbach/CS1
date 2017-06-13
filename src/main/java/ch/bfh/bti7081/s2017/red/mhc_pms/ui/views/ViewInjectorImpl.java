package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.welcome.WelcomeView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.timetable.TimetableView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPagePresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPagePresenter;
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
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserDetailPresenter;

/**
 * Default implementation of the ViewInjector interface.
 *
 * @author Aleistar Markóczy, Samuel Egger
 */
public class ViewInjectorImpl implements ViewInjector {
    // Navigation and HTTP request

    private Navigator mNavigator = null;
    private VaadinRequest mVaadinRequest = null;

    // Pages: Init on constructor
    private StartPage mStartPage = null;
    private MainPage mMainPage = null;

    // Views: Lazy initialization
    private PatientView mPatientView = null;
    private TimetableView mTimetableView = null;
    private UserDetailView mUserDetailView = null;
    private UserManagementView mUserManagementView = null;
    private WelcomeView mWelcomeView = null;
    private BillingView mBillingView = null;

    // Services: Must be assigned by SessionFactory
    private UserService mUserService = null;
    private PasswordService mPasswordService = null;
    private PatientService mPatientService = null;
    private BillingService mBillingService = null;

    public ViewInjectorImpl(UI ui, VaadinRequest vaadinRequest) {
        mNavigator = new Navigator(ui, ui);
        mNavigator.addView("", getStartPage());
        mNavigator.addView(AppConstants.REF_URL_MAIN_PAGE, getMainPage());
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getNavigator()
     */
    public Navigator getNavigator() {
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
    public PatientView getPatientView() {
        if (mPatientView == null) {
            createPatientView();
        }
        return mPatientView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getTimetableView()
     */
    public TimetableView getTimetableView() {
        if (mTimetableView == null) {
            createTimetableView();
        }
        return mTimetableView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserDetailView()
     */
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
    public UserManagementView getUserManagementView() {
        if (mUserManagementView == null) {

        }
        return mUserManagementView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getWelcomeView()
     */
    public WelcomeView getWelcomeView() {
        if (mWelcomeView == null) {
            createWelcomeView();
        }
        return mWelcomeView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getWelcomeView()
     */
    public BillingView getBillingView() {
        if (mBillingView == null) {
            createBillingView();
        }
        return mBillingView;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserService()
     */
    public UserService getUserService() {
        return mUserService;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getPasswordService()
     */
    public PasswordService getPasswordService() {
        return mPasswordService;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getPatientService()
     */
    public PatientService getPatientService() {
        return mPatientService;
    }

    /* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getBillingService()
     */
    public BillingService getBillingService() {
        return mBillingService;
    }

    /**
     * Sets the user service.
     *
     * @param aUserService the new user service
     */
    public void setUserService(UserService aUserService) {
        mUserService = aUserService;
    }

    /**
     * Sets the password service.
     *
     * @param aPasswordService the new password service
     */
    public void setPasswordService(PasswordService aPasswordService) {
        mPasswordService = aPasswordService;
    }

    /**
     * Sets the patient service.
     *
     * @param aPatientService the new patient service
     */
    public void setPatientService(PatientService aPatientService) {
        mPatientService = aPatientService;
    }

    /**
     * Sets the billing service.
     *
     * @param aBillingService the new billing service
     */
    public void setBillingService(BillingService aBillingService) {
        mBillingService = aBillingService;
    }

    private synchronized void createPatientView() {
        // second null check inside sync necessary to avoid double creation..
        if (mPatientView == null) {
            mPatientView = new PatientView();
        }
    }

    private synchronized void createTimetableView() {
        // second null check inside sync necessary to avoid double creation..
        if (mTimetableView == null) {
//            mTimetableView = new TimetableView(this);
        }
    }

    private synchronized void createWelcomeView() {
        if (mWelcomeView == null) {
//            mWelcomeView = new WelcomeView(this);
        }
    }

    private synchronized void createBillingView() {
        if (mBillingView == null) {
//            mBillingView = new BillingView(this);
        }
    }

}
