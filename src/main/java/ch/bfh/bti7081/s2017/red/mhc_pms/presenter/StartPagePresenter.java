package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.StartPage;
import com.vaadin.ui.Notification;
import org.apache.log4j.Logger;

/**
 * Created by Rolf on 30/05/17.
 */
public class StartPagePresenter extends PresenterBase<StartPage> {

    private final UserService userService;
    static final Logger log = Logger.getRootLogger();

    public StartPagePresenter(StartPage components, IUserSession session) {
        super(components, session);
        this.userService = session.getUserService();
    }

    public boolean checkLogin(String username, String password){
        try {
            log.debug("checking password");
            return userService.checkPassword(username,password);
        } catch (Exception e) {
            log.error("Exception while checking password.", e);
            return false;
        }
    }

}
