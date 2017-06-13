package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users;

import java.util.List;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.PresenterBase;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;

/**
 * Created by Rolf on 22/05/17.
 */
public class UserManagementPresenter extends PresenterBase<UserManagementView> {

    private final UserService userService;
    
    public UserManagementPresenter(UserManagementView view, UserService userService) {
        super(view);
        this.userService = userService;
    }

    public void onInitialize() {
        List<User> users = userService.findUserByFilter(null);
        getView().setUsers(users);
    }

    public void onSearch(){
        String filter = getView().getFilter();
        List<User> users = userService.findUserByFilter(filter);
        getView().setUsers(users);
    }

    public void onEdit(){

    }
}