package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users;

import java.util.List;

import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import com.vaadin.navigator.Navigator;

/**
 * Created by Rolf on 16/05/17.
 */
public class UserManagementView extends MainPageContent<UserManagementPresenter> {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    private Grid<User> userGrid;
    private TextField txtFilter;
    private Long selectedUserID;
    private PathParams pp = new PathParams();


    public UserManagementView(Navigator navigator) {
        super(navigator);
        userGrid = new Grid("Users");
        Button createNewUser = new Button("Create New User");
        createNewUser.addClickListener(e -> {
            pp.addParam("id","");
            navigator.navigateTo(AppConstants.REF_URL_MAIN_PAGE + "/createuser"+pp.getParamString());
        });

        txtFilter = new TextField("Username");

        Button search = new Button("Search");
        search.addClickListener(e -> {
            presenter.onSearch();
            userGrid.removeAllColumns();
            userGrid.addColumn(User::getUsername).setCaption("User Name");
            userGrid.addColumn(User::getEmail).setCaption("E-Mail");
            userGrid.addColumn(User::getActive).setCaption("Active");
        });

        //ToDo implement user editing
        Button edit = new Button("Edit");
        edit.addClickListener(e -> {
            pp.addParam("id",selectedUserID.toString());
            getNavigator().navigateTo(AppConstants.REF_URL_MAIN_PAGE + "/createuser"+pp.getParamString());
        });

        //Grid config
        userGrid.addItemClickListener(e -> {
            User selectedUser = e.getItem();
            selectedUserID = selectedUser.getId();
        });

        userGrid.setSelectionMode(Grid.SelectionMode.SINGLE); //Alow only for single select on grid

        HorizontalLayout searchAndCreate = new HorizontalLayout();
        searchAndCreate.addComponent(txtFilter);
        searchAndCreate.addComponent(search);
        searchAndCreate.addComponent(createNewUser);
        searchAndCreate.setComponentAlignment(search, Alignment.BOTTOM_CENTER);
        searchAndCreate.setComponentAlignment(txtFilter, Alignment.BOTTOM_CENTER);
        searchAndCreate.setComponentAlignment(createNewUser, Alignment.BOTTOM_CENTER);

        this.addComponent(searchAndCreate);
        this.addComponent(userGrid);
        this.addComponent(edit);
    }

    @Override
    public void setPresenter(UserManagementPresenter presenter) {
        this.presenter = presenter;
        presenter.onInitialize();
    }

    public void setUsers(List<User> users) {
        userGrid.setItems(users);
    }

    public String getFilter() {
        return txtFilter.getValue();
    }

    public Long getSelectedUserID() {
        return selectedUserID;
    }

    public void setSelectedUserID(Long userID) {
        this.selectedUserID = userID;
    }

    @Override
    public void updateParams(PathParams aParams) {
    }


}

