/**
 * File: ch.bfh.bti7081.s2017.red::SessionFactory.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.domain.session;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.Sha1PasswordService;

/**
 * A factory for creating Session objects.
 */
public class SessionFactory
{
	static
	{
		// ctor
	}
	
	/**
	 * Creates a new Session object.
	 *
	 * @return the user session
	 */
	public static IUserSession createUserSession(UI aUI, VaadinRequest aVaadinRequest)
	{
		UserSession r = new UserSession(aUI, aVaadinRequest);
		r.setUserService(new InMemoyUserService());
		r.setPasswordService(new Sha1PasswordService());
		r.setBillingService(new BillingService());
		// r.setPatientService(new PatientService()); TODO: PatientServiceImpl
		return r;
	}
}
