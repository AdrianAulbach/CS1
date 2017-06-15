package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Bill;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by adrian on 21.05.17.
 * 
 * TODO interface abstraction (InMemoryBillingService & BillingServiceImpl)
 */
public class InMemoryBillingService implements BillingService {
    private static List<Bill> bills = new LinkedList<Bill>();

    static {
        Bill bill1 = new Bill("B001", 1234, new Date());
        bill1.setState(bill1.getBillSent());
        bills.add(bill1);

        Bill bill2 = new Bill("B002", 333, new Date());
        bills.add(bill2);
    }

    @Override
    public List<Bill> getBills() {
        return bills;
    }

    @Override
    public void addBill(Bill bill) {
        bills.add(bill);
    }
}
