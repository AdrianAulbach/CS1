/**
 * File: ch.bfh.bti7081.s2017.red::MainPageContent.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;

/**
 * The abstract class MainPageContent. Used to describe content of the Main Page of the application.
 * All Main page contents can be updated on request of a new url, this makes the contents reusable and exchangeable.
 */
public abstract class MainPageContent extends VerticalLayout
{
	private static final long serialVersionUID = 1L;

	/**
	 * Update params from an existing param definition.
	 *
	 * @param params the params
	 */
	public abstract void updateParams(PathParams params);
	
	// TODO resetParams
}
