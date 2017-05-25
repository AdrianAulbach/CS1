/**
 * File: ch.bfh.bti7081.s2017.red::UserSession.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.domain.session;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PatientService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PatientView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.TimetableView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserDetailView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserManagementView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.WelcomeView;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing.BillingView;

/**
 * The Class UserSession.
 */
public class UserSession implements IUserSession
{
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
	
	public UserSession(UI aUI, VaadinRequest aVaadinRequest)
	{
		// Create and register the views
		mNavigator = new Navigator(aUI,aUI);
		mStartPage = new StartPage(this);
		mMainPage = new MainPage(this);
        mNavigator.addView("", mStartPage);
        mNavigator.addView(Strings.REF_URL_MAIN_PAGE, mMainPage);
        
		mVaadinRequest = aVaadinRequest;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getNavigator()
	 */
	public Navigator getNavigator()
	{
		return mNavigator;
	}
	
	/**
	 * Gets the vaadin request.
	 *
	 * @return the vaadin request
	 */
	public VaadinRequest getVaadinRequest()
	{
		return mVaadinRequest;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getStartPage()
	 */
	public StartPage getStartPage()
	{
		return mStartPage;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getMainPage()
	 */
	public MainPage getMainPage()
	{
		return mMainPage;
	}

	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getPatientView()
	 */
	public PatientView getPatientView()
	{
		if(mPatientView == null)
		{
			createPatientView();
		}
		return mPatientView;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getTimetableView()
	 */
	public TimetableView getTimetableView()
	{
		if(mTimetableView == null)
		{
			createTimetableView();
		}
		return mTimetableView;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserDetailView()
	 */
	public UserDetailView getUserDetailView()
	{
		if(mUserDetailView == null)
		{
			createUserDetailView();
		}
		
		return mUserDetailView;
	}


	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserManagementView()
	 */
	public UserManagementView getUserManagementView()
	{
		if(mUserManagementView == null)
		{
			createUserManagementView();
		}
		return mUserManagementView;
	}

	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getWelcomeView()
	 */
	public WelcomeView getWelcomeView()
	{
		if(mWelcomeView == null)
		{
			createWelcomeView();
		}
		return mWelcomeView;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getWelcomeView()
	 */
	public BillingView getBillingView()
	{
		if(mBillingView  == null)
		{
			createBillingView();
		}
		return mBillingView;
	}

	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getUserService()
	 */
	public UserService getUserService()
	{
		return mUserService;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getPasswordService()
	 */
	public PasswordService getPasswordService()
	{
		return mPasswordService;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getPatientService()
	 */
	public PatientService getPatientService()
	{
		return mPatientService;
	}
	
	/* (non-Javadoc)
	 * @see ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession#getBillingService()
	 */
	public BillingService getBillingService()
	{
		return mBillingService;
	}
	
	/**
	 * Sets the user service.
	 *
	 * @param aUserService the new user service
	 */
	public void setUserService(UserService aUserService)
	{
		mUserService = aUserService;
	}
	
	/**
	 * Sets the password service.
	 *
	 * @param aPasswordService the new password service
	 */
	public void setPasswordService(PasswordService aPasswordService)
	{
		mPasswordService = aPasswordService;
	}
	
	/**
	 * Sets the patient service.
	 *
	 * @param aPatientService the new patient service
	 */
	public void setPatientService(PatientService aPatientService)
	{
		mPatientService = aPatientService;
	}
	
	/**
	 * Sets the billing service.
	 *
	 * @param aBillingService the new billing service
	 */
	public void setBillingService(BillingService aBillingService)
	{
		mBillingService = aBillingService;
	}
	
	private synchronized void createPatientView()
	{
		// second null check inside sync necessary to avoid double creation..
		if(mPatientView == null)
		{
			mPatientView = new PatientView();
		}
	}
	
	
	private synchronized void createTimetableView()
	{
		// second null check inside sync necessary to avoid double creation..
		if(mTimetableView == null)
		{
			mTimetableView = new TimetableView(this);
		}
	}
	
	
	private synchronized void createUserDetailView()
	{
		if(mUserDetailView == null)
		{
			mUserDetailView = new UserDetailView(this);
		}
	}
	
		
	private synchronized void createUserManagementView()
	{
		if(mUserManagementView == null)
		{
			mUserManagementView = new UserManagementView(this);
		}
	}


	private synchronized void createWelcomeView()
	{
		if(mWelcomeView == null)
		{
			mWelcomeView = new WelcomeView(this);
		}
	}
	

	private synchronized void createBillingView()
	{
		if(mBillingView == null)
		{
			mBillingView = new BillingView(this);
		}
	}
	
}
