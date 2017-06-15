package ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages;

import org.apache.log4j.Logger;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.AppConstants;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.prefabs.NavigationIconButton;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MvpView;

/**
 * The Class MainView.
 * 
 * @author Aleistar Markóczy
 */
@DesignRoot
public class MainPage extends CustomComponent implements View, MvpView<MainPagePresenter> {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Constant log.
     */
    static final Logger log = Logger.getRootLogger();

    /**
     * The VerticalLayout main panel.
     */
    private VerticalLayout vlMainPanel;

    /**
     * The VerticalLayout menu and content panel.
     */
    private VerticalLayout vlMenuAndContentPanel;

    /**
     * The Panel menu panel.
     */
    private Panel pnMenuPanel;

    /**
     * The HorizontalLayout navigation panel.
     */
    private HorizontalLayout hlNavigationPanel;

    /**
     * The Panel content panel.
     */
    private Panel pnContentPanel;

    /**
     * The Button log off.
     */
    private Button btnLogOff;

    MainPagePresenter presenter;
    Navigator navigator;

    /**
     * Instantiates a new main view.
     *
     * @param presenter
     */
    public MainPage(Navigator navigator) {
        this.navigator = navigator;
        initUI();
    }
    
    @Override
    public void setPresenter(MainPagePresenter presenter){
        this.presenter = presenter;
    }

    /**
     * Where the application's navigation is managed: on request the panel can
     * be created/loaded and displayed.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeEvent event) {
        log.info("User requested page: " + event.getParameters());
        presenter.navigateTo(event.getParameters());
    }

    public void setContent(Component aView) {
        getContentPanel().setContent(aView);
    }

    /*-------- UI Related Initializers --------*/
    private void initUI() {
        // Hierarchy:
        // 
        // Vertical Layout: Size Full  -> mainPanel
        //	     Vertical Layout: Size Full, expand -> menuAndContentPanel
        //	         Panel:"Menu": Height full width auto -> menuPanel
        //	             Horizontal Layout -> navigationPanel
        //	                 IconButtons: Welcome ...
        //	         Panel:"Content" -> contentPanel
        //	     Button:"Logout" -> logOffBtn
        //
        //
        //
    	setSizeFull();
    	setCompositionRoot(getMainPanel());
    }

    /**
     * Gets the main panel.
     *
     * @return the main panel
     */
    private VerticalLayout getMainPanel() {
        if (vlMainPanel == null) {
            vlMainPanel = new VerticalLayout();
            vlMainPanel.setSizeFull();
            vlMainPanel.addComponent(getMenuAndContentPanel());
            vlMainPanel.setExpandRatio(getMenuAndContentPanel(), 1.0f);
            vlMainPanel.addComponent(getLogOffButton());
        }

        return vlMainPanel;
    }

    /**
     * Gets the menu and content panel.
     *
     * @return the menu and content panel
     */
    private VerticalLayout getMenuAndContentPanel() {
        if (vlMenuAndContentPanel == null) {
            vlMenuAndContentPanel = new VerticalLayout();
            vlMenuAndContentPanel.setHeightUndefined();
            vlMenuAndContentPanel.addComponent(getMenuPanel());
            vlMenuAndContentPanel.addComponent(getContentPanel());
            vlMenuAndContentPanel.setExpandRatio(getContentPanel(), 1.0f);
        }

        return vlMenuAndContentPanel;
    }

    /**
     * Gets the menu panel.
     *
     * @return the menu panel
     */
    private Panel getMenuPanel() {
        if (pnMenuPanel == null) {
            pnMenuPanel = new Panel("Menu");
            pnMenuPanel.setContent(getNavigationPanel());
        }

        return pnMenuPanel;
    }

    /**
     * Gets the navigation panel.
     *
     * @return the navigation panel
     */
    private HorizontalLayout getNavigationPanel() {
        if (hlNavigationPanel == null) {
            hlNavigationPanel = new HorizontalLayout();
            hlNavigationPanel.setMargin(true);
            // TODO constants
            hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.HOME_PAGE, "button_home.png", "Home", navigator));
            hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.PATIENT_PAGE, "button_patients.png", "Manage Patients", navigator));
            hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.TIMETABLE_PAGE, "button_timetable.png", "Timetable", navigator));
            hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.USERS_PAGE, "button_manageuser.png", "Manage Users", navigator));
            hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.CREATE_USER_PAGE, "button_createuser.png", "Create new User", navigator));
            hlNavigationPanel.addComponent(new NavigationIconButton(AppConstants.BILLS_PAGE, "button_patients.png", "See bills", navigator));
        }

        return hlNavigationPanel;
    }

    /**
     * Gets the content panel.
     *
     * @return the content panel
     */
    private Panel getContentPanel() {
        if (pnContentPanel == null) {
            pnContentPanel = new Panel("Content");
            pnContentPanel.setHeightUndefined();
        }

        return pnContentPanel;
    }

    /**
     * Gets the log off button.
     *
     * @return the log off button
     */
    private Button getLogOffButton() {
        if (btnLogOff == null) {
            btnLogOff = new Button("Logout");
            btnLogOff.addClickListener(event -> presenter.navigateTo(""));
        }

        return btnLogOff;
    }
}
