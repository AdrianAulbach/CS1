package ch.bfh.bti7081.s2017.red.mhc_pms.ui.panels;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.UserService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ch.bfh.bti7081.s2017.red.mhc_pms.presenter.UserManagementPresenter;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Rolf on 16/05/17.
 */
public class UserManagementPanel extends VerticalLayout {

    private UserManagementPresenter presenter;

    public UserManagementPanel(){
        presenter = new UserManagementPresenter(this);

        Button createNewUser = new Button("Create New User");
        createNewUser.addClickListener(e -> {
            //ToDo chang panel to NewUserManagementPanel
        });

        this.addComponent(createNewUser);
    }
}
