/**
 * File: ch.bfh.bti7081.s2017.red::IconButton.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;

public class IconButton extends Button
{
	// http://stackoverflow.com/questions/40847123/vaadin-using-an-image-as-a-button
	
	private static final long serialVersionUID = 1L;

	public IconButton(String aImage, String aDescription, ClickListener aListener)
	{
		super(new ThemeResource(AppConstants.BUTTON_RESOURCE_PATH + aImage));
		if(aDescription!=null) setDescription(aDescription);
		
		addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		if (aListener != null) addClickListener(aListener);
	}

}
