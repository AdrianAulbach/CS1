package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Bill;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.pages.MainPageContent;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * Created by adrian on 21.05.17.
 */
public class BillingView extends MainPageContent {

    private static final Logger log = Logger.getRootLogger();
    private Grid<Bill> billGrid;

    private BillingPresenter presenter;

    public BillingView(Navigator navigator) {
        super(navigator);
        billGrid = new Grid<>("Bills");
        Button addBill = new Button("Add bill");

        billGrid.addColumn(Bill::getID).setCaption("ID");
        billGrid.addColumn(Bill::getAmount).setCaption("amount");
        billGrid.addColumn(Bill::getState).setCaption("status");

        this.addComponent(billGrid);
    }

    public void setPresenter(BillingPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void updateParams(PathParams aParams) {
        // TODO retreive path params here
    }

    public void setBills(List<Bill> bills) {
        billGrid.setItems(bills);
    }
}
