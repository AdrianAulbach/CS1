package ch.bfh.bti7081.s2017.red.mhc_pms.presenter.billing;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.session.IUserSession;
import ch.bfh.bti7081.s2017.red.mhc_pms.services.BillingService;
import ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.billing.BillingView;
import org.apache.log4j.Logger;


/**
 * Created by adrian on 18.05.17.
 */
public class BillingPresenter {
    private static final Logger log = Logger.getRootLogger();

    private BillingView billingView;
    private BillingService billingService;

    public BillingPresenter(BillingView billingView, IUserSession session) {
        this.billingView = billingView;
        this.billingService = session.getBillingService(); //@Adrian: der Billing service wird neu von der Session verwaltet
    }



}
