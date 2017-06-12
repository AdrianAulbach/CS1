package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Bill;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.MainPageContent;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.PathParams;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by adrian on 21.05.17.
 */
public class BillingView extends MainPageContent {
    private static final Logger log = Logger.getRootLogger();

    private BillingService billingService = new BillingService();
    private BillingPresenter presenter;

    public BillingView(IUserSession session) {
        presenter = new BillingPresenter(this, session);

        Grid<Bill> billGrid = new Grid<>("Bills");
        Button addBill = new Button("Add bill");

        List<Bill> bills = billingService.getBills(); //billingService.findBillsByState(new Bill.ToBill(null)); //ToDo: change BillingState so null as context is no longer required

        billGrid.setItems(bills);
        billGrid.addColumn(Bill::getID).setCaption("ID");
        billGrid.addColumn(Bill::getAmount).setCaption("amount");
        billGrid.addColumn(Bill::getState).setCaption("status");

        this.addComponent(billGrid);
    }

	@Override
	public void updateParams(PathParams aParams)
    	{
		// TODO retreive path params here
	}
}
