package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserServiceImpl;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPagePresenter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.Sha1PasswordService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import com.vaadin.navigator.Navigator;

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
        r.setBillingService(new BillingService());
        r.getNavigator();

        return r;
    }
}
