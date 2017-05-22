package ch.bfh.bti7081.s2017.red.mhc_pms.presenter;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.UserManagementView;

/**
 * Created by Rolf on 22/05/17.
 */
public class UserManagementPresenter extends PresenterBase<UserManagementView> {

    private final UserService userService;

    public UserManagementPresenter(UserManagementView view, UserService userService) {
        super(view);
        this.userService = userService;
    }
}
