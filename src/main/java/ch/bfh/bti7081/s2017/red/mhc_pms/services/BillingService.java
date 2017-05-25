package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.billing.Bill;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by adrian on 21.05.17.
 * 
 * TODO interface abstraction (BillingService & BillingServiceImpl)
 */
public class BillingService {
    private static List<Bill> bills = new LinkedList<Bill>();

    static {
        Bill bill1 = new Bill("B001", 1234, new Date());
        bill1.setState(bill1.getBillSent());
        bills.add(bill1);

        Bill bill2 = new Bill("B002", 333, new Date());
        bills.add(bill2);
    }

    public static List<Bill> getBills() {
        return bills;
    }

    public static List<Bill> findBillsByState(Bill.BillingState billingState) {
        return bills.stream().filter(bill -> bill.isInState(billingState)).collect(Collectors.toList());
    }

    public static void addBill(Bill bill) {
        bills.add(bill);
    }

    public static Bill getBillByID(String ID) {
        return bills.stream().filter(bill -> bill.getID().equals(ID)).findFirst().orElse(null);
    }
}
