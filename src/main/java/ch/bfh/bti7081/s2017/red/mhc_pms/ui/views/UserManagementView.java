package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserServiceImpl;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Rolf on 16/05/17.
 */
public class UserManagementView extends VerticalLayout {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();


    private UserService userService = new UserServiceImpl();
    private UserManagementPresenter presenter;
    private Navigator navigator;
    private Grid<User> userGrid;
    private TextField txtFilter;

    public UserManagementView(Navigator navigator) {
        presenter = new UserManagementPresenter(this, userService, navigator);
        this.navigator = navigator;

        userGrid = new Grid("Users");
        Button createNewUser = new Button("Create New User");
        createNewUser.addClickListener(e -> {
            //ToDo change panel to empty UserDetail View
            presenter.navigateTo(Strings.REF_URL_MAIN_PAGE+ "/createuser");
        });

        txtFilter = new TextField("Username");

        Button search = new Button("Search");
        search.addClickListener(e -> {
            presenter.onSearch();
            userGrid.removeAllColumns();
            userGrid.addColumn(User::getUsername).setCaption("User Name");
            userGrid.addColumn(User::getEmail).setCaption("E-Mail");
        });


        //ToDo implement user editing


        this.addComponent(createNewUser);
        this.addComponent(txtFilter);
        this.addComponent(search);
        this.addComponent(userGrid);

        presenter.onInitialize();
    }

    public void setUsers(List<User> users){
        userGrid.setItems(users);
    }

    public String getFilter(){
        return txtFilter.getValue();
    }
}
