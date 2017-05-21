package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.InMemoyUserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rolf on 16/05/17.
 */
public class UserManagementView extends VerticalLayout {

    /** The Constant log. */
    static final Logger log = Logger.getRootLogger();


    private UserService userService = new InMemoyUserService();
    private UserManagementPresenter presenter;
    private Navigator navigator;

    public UserManagementView(Navigator navigator){
        presenter = new UserManagementPresenter(this);
        this.navigator = navigator;

        Grid<User> userGrid = new Grid("Users");
        Button createNewUser = new Button("Create New User");
        createNewUser.addClickListener(e -> {
            //ToDo chang panel to NewUserCreatePanel
        });

        TextField username = new TextField("Username");

        Button search = new Button("Search");
        search.addClickListener(e -> {
            List<User> userList = new ArrayList();
            userGrid.removeAllColumns();
            userList = userService.findUserByFilter(username.getValue());
            userGrid.setItems(userList);
            userGrid.addColumn(User::getUsername).setCaption("User Name");
            userGrid.addColumn(User::getEmail).setCaption("E-Mail");
            userGrid.addColumn(User::isAktive).setCaption("Aktive");
        });


        //ToDo implement user editing



        this.addComponent(createNewUser);
        this.addComponent(username);
        this.addComponent(search);
        this.addComponent(userGrid);
    }
}
