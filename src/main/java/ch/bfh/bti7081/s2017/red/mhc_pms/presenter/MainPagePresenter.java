package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import org.apache.log4j.Logger;

import com.vaadin.ui.Label;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPage;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MainPageContent;
import ch.bfh.bti7081.s2017.red.mhc_pms.util.PathParams;

public class MainPagePresenter extends PresenterBase<MainPage>
{
	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();
	
	MainPage mView;
	IUserSession mSession;
	
	public MainPagePresenter(MainPage aView, IUserSession aSession)
	{
		super(aView,aSession);
		mView = aView;
		mSession = aSession;
	}
	
	@Override
	public void navigateTo(String aPath)
	{
		String lPath = aPath;
		PathParams lParams = null;
		if(aPath.contains("?"))
		{
			lPath = aPath.substring(0,aPath.indexOf('?'));
			lParams = new PathParams(aPath);
		}
		
		mView.setContent(mSession.getWelcomeView());
		
		if (lPath == null)
		{
			mView.setContent(new Label("Request could not be resolved."));
			return;
		}
		else
		{
			MainPageContent lContent = null;
			
			switch (lPath)
			{
				case Strings.EMPTY:
				case Strings.HOME_PAGE:
					lContent = mSession.getWelcomeView();
					break;
				case Strings.PATIENT_PAGE:
					lContent = mSession.getPatientView();
					mView.setContent(mSession.getPatientView());
					break;
				case Strings.TIMETABLE_PAGE:
					lContent = mSession.getTimetableView();
					mView.setContent(mSession.getTimetableView());
					break;
				case Strings.USER_PAGE:
					lContent = mSession.getUserManagementView();
					mView.setContent(mSession.getUserManagementView());
					break;
				case Strings.CREATE_USER_PAGE:
					lContent = mSession.getUserDetailView();
					mView.setContent(mSession.getUserDetailView());
					break;
				case Strings.BILLS_PAGE:
					lContent = mSession.getBillingView();
					mView.setContent(mSession.getBillingView());
					break;
				default:
					log.error("Request could not be resolved.");
					return;
			}
			
			if(lParams!=null) lContent.updateParams(lParams);
			mView.setContent(lContent);
		}
	}
}
