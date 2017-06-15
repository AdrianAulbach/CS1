package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.*;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoryBillingService;

/**
 * A factory for creating the ViewInjector class.
 *
 * @author Aleistar Markóczy, Samuel Egger
 */
public class ViewInjectorFactory {

    /**
     * Creates a new ViewInjector object.
     *
     * @param ui
     * @param vaadinRequest
     * @return the view injector
     */
    public static ViewInjector createViewInjector(UI ui, VaadinRequest vaadinRequest) {
        ViewInjectorImpl r = new ViewInjectorImpl(ui, vaadinRequest);

        // Manual dependency injection
        
        PasswordService passwordSerivce = new Sha1PasswordService();
        r.setPasswordService(passwordSerivce);
        r.setUserService(new UserServiceImpl(passwordSerivce));
        r.setBillingService(new InMemoryBillingService());
        r.getNavigator();

        return r;
    }
}
