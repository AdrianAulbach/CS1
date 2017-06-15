package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;

public class PatientEditView extends MainPageContent<PatientEditPresenter>
{
	private HorizontalLayout hlMainPanel = null;
	private VerticalLayout vlLeftPanel = null;
	private VerticalLayout vlRightPanel = null;
	
	private Panel pnPicture = null;
	private Button btnChangePicture = null;
	
	private EditableField efdName = null;
	private EditableField efdStreet = null;
	private EditableField efdCity = null;
	
	private Accordion acdStats = null;
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
		vlLeftPanel.setSizeFull();
//		vlLeftPanel.setHeight("100%");
//		vlLeftPanel.setWidth("50%");
		
		vlRightPanel = new VerticalLayout();
		vlRightPanel.setSizeFull();
//		vlRightPanel.setHeight("100%");
//		vlRightPanel.setWidth("50%");
		
		pnPicture = new Panel();
		pnPicture.setHeight("90px");
		pnPicture.setWidth("90px");
//		pnPicture.setContent(content);
		
		efdName = new EditableField("Name:","");
		efdStreet = new EditableField("Strasse","");
		efdCity = new EditableField("Stadt:","");
		

		acdStats = new Accordion();
//	    final Label label = new Label("Sessions");
//	    label.setWidth(100.0f, Unit.PERCENTAGE);
//	    layout.setMargin(true);
	    acdStats.setSizeFull();
	    acdStats.addTab(new VerticalLayout(new Label("Test 1")),"Sessions");
	    acdStats.addTab(new VerticalLayout(new Label("Test")),"Prescriptions");
	    acdStats.addTab(new VerticalLayout(new Label("Test")),"Records");
		
	    vlRightPanel.addComponent(acdStats);
	    
		vlLeftPanel.addComponent(pnPicture);
		vlLeftPanel.addComponent(efdName);
		vlLeftPanel.addComponent(efdStreet);
		vlLeftPanel.addComponent(efdCity);
		
		
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
		if(lId!=null); // TODO load patient by id
		
		String lName = aParams.getParam("name");
		if(lName!=null) efdName.updateDefault(lName);

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
		
	}
	
//  sample = new Accordion();
//  sample.setHeight(100.0f, Unit.PERCENTAGE);
//
//  for (int i = 1; i < 8; i++) {
//      final Label label = new Label(TabSheetSample.getLoremContent(), ContentMode.HTML);
//      label.setWidth(100.0f, Unit.PERCENTAGE);
//
//      final VerticalLayout layout = new VerticalLayout(label);
//      layout.setMargin(true);
//
//      sample.addTab(layout, "Tab " + i);
//  }
	
}
