package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing;

import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import org.apache.log4j.Logger;

/**
 * Created by adrian on 18.05.17.
 */
public class BillingPresenter {

    private static final Logger log = Logger.getRootLogger();

    private BillingView billingView;
    private BillingService billingService;

    public BillingPresenter(BillingView billingView, BillingService billingService) {
        this.billingView = billingView;
        this.billingService = billingService;
    }
}
