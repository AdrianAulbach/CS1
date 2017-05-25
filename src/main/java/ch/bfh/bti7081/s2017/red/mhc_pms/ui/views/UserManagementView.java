package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views;

import java.util.List;

import org.apache.log4j.Logger;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.Strings;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserServiceImpl;

/**
 * Created by Rolf on 16/05/17.
 */
public class UserManagementView extends VerticalLayout {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    private UserManagementPresenter presenter;
    private Grid<User> userGrid;
    private TextField txtFilter;

    public UserManagementView(IUserSession session) {
        presenter = new UserManagementPresenter(this, session);

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
