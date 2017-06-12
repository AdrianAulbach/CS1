package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users;

import java.util.List;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.PresenterBase;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users.UserManagementView;

/**
 * Created by Rolf on 22/05/17.
 */
public class UserManagementPresenter extends PresenterBase<UserManagementView> {

    private final UserService userService;

    // @Rolf: Der Konstruktor nimmt jetzt nur noch eine User session weil die user
    //        session alle objekte verwaltet und ggf. wiederverwenden kann
    public UserManagementPresenter(UserManagementView view,IUserSession session) {
        super(view, session);
        this.userService = session.getUserService();
    }

    @Override
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