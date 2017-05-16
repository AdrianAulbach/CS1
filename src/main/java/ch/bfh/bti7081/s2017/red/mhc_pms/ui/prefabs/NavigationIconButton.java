package ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs;

import org.apache.log4j.Logger;

import com.vaadin.navigator.Navigator;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;

/**
 * The Class NavigationButton.
 */
public class NavigationIconButton extends IconButton
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant log. */
	static final Logger log = Logger.getRootLogger();

	/**
	 * Instantiates a new navigation button.
	 *
	 * @param aPath the relative path to navigate to
	 * @param aImage the reference image relative path
	 * @param aDescription the reference description
	 * @param aNavigator the reference navigator
	 */
	public NavigationIconButton(String aPath, String aImage, String aDescription, Navigator aNavigator)
	{
		super(aImage, aDescription, event->
		{
			log.debug("Click event received. Navigating to: " + aPath);

			// Navigate to a specific state
			aNavigator.navigateTo(Strings.REF_URL_MAIN_PAGE + "/" + aPath);
		});
	}
}
