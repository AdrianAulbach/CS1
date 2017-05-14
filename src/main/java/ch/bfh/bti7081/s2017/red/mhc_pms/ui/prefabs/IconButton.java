/**
 * File: ch.bfh.bti7081.s2017.red::IconButton.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;

public class IconButton extends Button
{
	private static final long serialVersionUID = 1L;

	private static final String BUTTON_RESOURCE = "buttons/";

	public IconButton(String aRelativeName, ClickListener aListener)
	{
		super();
//		setStyleName(ValoTheme.BUTTON_LINK);
		setIcon(new ThemeResource(BUTTON_RESOURCE + aRelativeName));

		addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		addStyleName(ValoTheme.BUTTON_BORDERLESS);
		
////		setSizeUndefined();
		setHeight("40px");
		setWidth("148px");
		if (aListener != null) addClickListener(aListener);
	}

}
