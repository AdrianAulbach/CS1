package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.users;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MainPageContent;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;

/**
 * Created by Rolf on 16/05/17.
 */
public class UserManagementView extends MainPageContent {

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    private UserManagementPresenter presenter;
    private Grid<User> userGrid;
    private TextField txtFilter;
    private Long selectedUserID;

    public UserManagementView(IUserSession session) {
        presenter = new UserManagementPresenter(this, session);

        userGrid = new Grid("Users");
        Button createNewUser = new Button("Create New User");
        createNewUser.addClickListener(e -> {
            presenter.navigateTo(AppConstants.REF_URL_MAIN_PAGE+ "/createuser");
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

        Button edit = new Button("Edit");
        edit.setDisableOnClick(true); //deaktivate button set getActiveVal false
        edit.addClickListener(e -> {
           //ToDo change to UserDetailView and hand over the selected user ID
            presenter.navigateTo(AppConstants.REF_URL_MAIN_PAGE+ "/createuser");
        });

        //Grid config
        userGrid.addSelectionListener(e -> {
            //set selectedUserID to the id of the selected user
            Optional<User> selectedUser =e.getFirstSelectedItem();
            selectedUserID = selectedUser.get().getId();
            edit.setDisableOnClick(true);

        });

        userGrid.setSelectionMode(Grid.SelectionMode.SINGLE); //Alow only for single select on grid

        this.addComponent(createNewUser);
        this.addComponent(txtFilter);
        this.addComponent(search);
        this.addComponent(userGrid);
        this.addComponent(edit);

        presenter.onInitialize();
    }

    public void setUsers(List<User> users){
        userGrid.setItems(users);
    }

    public String getFilter()
    {
        return txtFilter.getValue();
    }

    public Long getSelectedUserID(){
        return selectedUserID;
    }

    public void setSelectedUserID(Long userID){
        this.selectedUserID = userID;
    }
    
	@Override
	public void updateParams(PathParams aParams)
	{
		// TODO retreive path params here
		
	}
}
