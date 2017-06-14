package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import org.apache.log4j.Logger;

/**
 * Created by Rolf on 30/05/17.
 */
public class StartPagePresenter extends PresenterBase<StartPage> {

    private final UserService userService;
    static final Logger log = Logger.getRootLogger();

    public StartPagePresenter(StartPage startPage, UserService userService) {
        super(startPage);
        this.userService = userService;
    }

    public boolean checkLogin(String username, String password) {
        try {
            log.debug("Checking password");
            return userService.isLoginValid(username, password);
        } catch (Exception e) {
            log.error("Exception while checking password.", e);
            return false;
        }
    }

}
