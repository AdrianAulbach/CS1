package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Bill;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adrian on 15.06.17.
 */
public interface BillingService {
    List<Bill> getBills();

    default List<Bill> findBillsByState(Bill.BillingState billingState) {
        return getBills().stream().filter(bill -> bill.isInState(billingState)).collect(Collectors.toList());
    }

    void addBill(Bill bill);

    default Bill getBillByID(String ID) {
        return getBills().stream().filter(bill -> bill.getID().equals(ID)).findFirst().orElse(null);
    }
}
