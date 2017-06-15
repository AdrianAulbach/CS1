package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import org.apache.log4j.Logger;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs.IconButton;

public class PatientEditView extends MainPageContent<PatientEditPresenter>
{
	
	private static final Logger log = Logger.getRootLogger();
	
	private HorizontalLayout hlMainPanel = null;
	private VerticalLayout vlLeftPanel = null;
	private VerticalLayout vlRightPanel = null;
	
	private Panel pnPicture = null;
	private Button btnChangePicture = null;
	
	private EditableField efdFirstName = null;
	private EditableField efdLastName = null;
	private EditableField efdPhone = null;
	private EditableField efdMobile = null;
	private EditableField efdEmail = null;
	private EditableField efdStreet = null;
	private EditableField efdCity = null;
	
	private Accordion acdStats = null;
	
	private Button btnSave = null;
	
	private long selectedPatientId = -1;
//	private EditableField efdCountry = null;
	


	public PatientEditView(Navigator aNavigator)
	{
		super(aNavigator);
		initUI();
	}

	private void initUI()
	{
		hlMainPanel = new HorizontalLayout();
		hlMainPanel.setSizeFull();
		
		vlLeftPanel = new VerticalLayout();
		
		vlRightPanel = new VerticalLayout();
		vlRightPanel.setSizeFull();
		
		pnPicture = new Panel();
		pnPicture.setHeight("90px");
		pnPicture.setWidth("90px");
		
		efdFirstName = new EditableField("First Name:","");
		efdLastName = new EditableField("Last Name:","");
		efdStreet = new EditableField("Street:","");
		efdCity = new EditableField("Zip / City:","");
		efdPhone = new EditableField("Phone:","");
		efdMobile= new EditableField("Mobile Phone:","");
		efdEmail = new EditableField("E-Mail:","");
		

		acdStats = new Accordion();
//	    final Label label = new Label("Sessions");
//	    label.setWidth(100.0f, Unit.PERCENTAGE);
//	    layout.setMargin(true);
	    acdStats.setSizeFull();
	    acdStats.addTab(new VerticalLayout(new Label("Test 1")),"Sessions");
	    acdStats.addTab(new VerticalLayout(new Label("Test")),"Prescriptions");
	    acdStats.addTab(new VerticalLayout(new Label("Test")),"Records");
		
	    btnSave = new Button("Register / Update Patient");
	    btnSave.addClickListener((e)->
	    {
	    	try
			{
	    		presenter.saveOrUpdate();
	    		getNavigator().navigateTo(AppConstants.REF_URL_PATIENT_SEARCH_PAGE + new PathParams().addParam("loadall", "true").getParamString());
			}
			catch (Exception ex)
			{
				log.error("Update failed",ex);
			}
	    });
	    
	    vlRightPanel.addComponent(acdStats);
	    vlRightPanel.addComponent(new Label());
	    vlRightPanel.addComponent(btnSave);
	    vlRightPanel.setComponentAlignment(btnSave, Alignment.MIDDLE_RIGHT);
	    vlRightPanel.setMargin(true);
	    
		vlLeftPanel.addComponent(pnPicture);
		vlLeftPanel.addComponent(new Label());
		
		vlLeftPanel.addComponent(efdFirstName);
		vlLeftPanel.addComponent(efdLastName);
		vlLeftPanel.addComponent(efdStreet);
		vlLeftPanel.addComponent(efdCity);
		vlLeftPanel.addComponent(efdPhone);
		vlLeftPanel.addComponent(efdMobile);
		vlLeftPanel.addComponent(efdEmail);
		
		
		
		hlMainPanel.addComponent(vlLeftPanel);
		hlMainPanel.addComponent(vlRightPanel);
//		
		addComponent(hlMainPanel);
		
//		addComponent(new Label("Edit View"));
//		addComponent(new EditableField("Name:",""));
//		addComponent(new EditableField("Strasse:","bla 2"));
//		addComponent(new EditableField("Stadt:","bern"));
//		addComponent(new EditableField("...","test"));
	}
	

	@Override
	public void updateParams(PathParams aParams)
	{
		String lId = aParams.getParam("id");
		if(lId!=null)
		{
			selectedPatientId = Long.parseLong(lId);
			presenter.loadById(selectedPatientId);
			return;
		}
		
		String lName = aParams.getParam("name");
		if(lName!=null) efdFirstName.updateDefault(lName);

		String lStreet = aParams.getParam("street");
		if(lName!=null) efdStreet.updateDefault(lStreet);

		String lCity = aParams.getParam("city");
		if(lName!=null) efdCity.updateDefault(lCity);

	}

	private static class EditableField extends HorizontalLayout
	{
		private String mDefault = null;
		
		private TextField mText = null;
		private Button mButton = null;
		
		public EditableField(String aName, String aDefault)
		{
			mDefault = aDefault;
			
			mText = new TextField(aName);
			mText.setValue(aDefault);
			mButton = new Button("-"); // TODO resource
			mButton.addClickListener((e)->mText.setValue(mDefault));
			
			addComponent(mText);
			addComponent(mButton);
			setComponentAlignment(mButton, Alignment.BOTTOM_CENTER);
			mText.setWidth("100%");
		}
		
		public void updateDefault(String aDefault)
		{
			mDefault = aDefault;
			mText.setValue(mDefault);
		}
		
		public String getValue()
		{
			return mText.getValue();
		}
	}
	
	public PatientEditViewModel getViewModel()
	{
		PatientEditViewModel r = new PatientEditViewModel();
		
		if(selectedPatientId!=-1) r.setId(selectedPatientId);
		r.setFirstName(efdFirstName.getValue());
		r.setLastName(efdLastName.getValue());
		r.setStreet(efdStreet.getValue());
		r.setCity(efdCity.getValue());
		r.setPhone(efdPhone.getValue());
		r.setMobile(efdMobile.getValue());
		r.setEmail(efdEmail.getValue());
		return r;
	}
	
	public void setViewModel(PatientEditViewModel aModel)
	{
		efdFirstName.updateDefault(aModel.getFirstName());
		efdLastName.updateDefault(aModel.getLastName());
		efdStreet.updateDefault(aModel.getStreet());
		efdCity.updateDefault(aModel.getCity());
		efdPhone.updateDefault(aModel.getPhone());
		efdMobile.updateDefault(aModel.getMobile());
		efdEmail.updateDefault(aModel.getEmail());
	}
	
}
