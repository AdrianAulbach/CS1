/**
 * File: ch.bfh.bti7081.s2017.red::IconButton.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class IconButton extends Button
{
	// http://stackoverflow.com/questions/40847123/vaadin-using-an-image-as-a-button
	
	private static final long serialVersionUID = 1L;

	private static final String BUTTON_RESOURCE = "buttons/";

	public IconButton(String aRelativeName, ClickListener aListener)
	{
		super(new ThemeResource(BUTTON_RESOURCE + aRelativeName)); // TODO class resource!
		
//		setPrimaryStyleName("iconbutton");
		//		setStyleName(ValoTheme.BUTTON_LINK);
//		setIcon(new ThemeResource(BUTTON_RESOURCE + aRelativeName));

//		addStyleName(ValoTheme.B);
//		addStyleName(ValoTheme.BUTTON_BORDERLESS);
//		addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
//		addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		
////		setSizeUndefined();
//		setHeight("38px");
//		setWidth("146px");
//		addClickListener(aListener);
//		addStyleName("pointer");
		//
		if (aListener != null) addClickListener(aListener);
	}

}
