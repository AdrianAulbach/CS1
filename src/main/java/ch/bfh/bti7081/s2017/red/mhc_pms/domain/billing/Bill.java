package ch.bfh.bti7081.s2017.red.mhc_pms.domain.billing;

import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by adrian on 18.05.17.
 */
public class Bill {
    private int amount = 0;
    private int billNumber = 0;
    private Date facturationDate;
    private Date dueDate = null;
    private String billID;

    private BillingState currentState;
    private BillingState toBill = new ToBill(this);
    private BillingState billSent = new BillSent(this);
    private BillingState billPaid = new BillPaid(this);
    private BillingState billOverdue = new BillOverdue(this);
    private BillingState engageRussianMafia = new EngageRussianMafia(this);

    public Bill(String ID, int amount, Date dueDate) {
        this.billID = ID;
        this.amount = amount;
        this.dueDate = dueDate;
        facturationDate = new Date();
        currentState = getToBill();
    }


    public void setState(BillingState state) {
        this.currentState = state;
    }
    public BillingState getState() {
        return currentState;
    }
    public boolean isInState(BillingState billingState) {
        return currentState.getClass() == billingState.getClass();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBillNumber() {
        return billNumber;
    }
    public void increaseBillNumber() {
        billNumber++;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getID() {
        return billID;
    }

    public BillingState getToBill() {
        return toBill;
    }

    public BillingState getBillSent() {
        return billSent;
    }

    public BillingState getBillPaid() {
        return billPaid;
    }

    public BillingState getBillOverdue() {
        return billOverdue;
    }

    public BillingState getEngageRussianMafia() {
        return engageRussianMafia;
    }

    public interface BillingState {
        void sendBill() throws InvalidStateOrderException;

        void receivePayment(int amount) throws InvalidStateOrderException;

        void checkStatus() throws InvalidStateOrderException;
    }

    public static class ToBill implements BillingState {
        private Bill context;

        public ToBill(Bill context) {
            this.context = context;
        }

        public void sendBill() {
            context.setState(context.getBillSent());
        }

        public void receivePayment(int amount) throws InvalidStateOrderException{
            throw new InvalidStateOrderException("Payment must not be received before bill is sent");
        }

        public void checkStatus() {
            //Status doesn't change before bill is sent -> don't do anything at this point
        }

        public String toString() {
            return "to bill";
        }
    }
    public class BillSent implements BillingState {
        private Bill context;
        public BillSent(Bill context) {
            this.context = context;
        }

        @Override
        public void sendBill() throws InvalidStateOrderException{
            throw new InvalidStateOrderException("Bill has already been sent");
        }

        @Override
        public void receivePayment(int amount) {
            if(amount >= context.getAmount()) {
                context.setState(context.getBillPaid());
            }

            //ToDo: handle case that amount is not sufficient
        }

        @Override
        public void checkStatus() throws InvalidStateOrderException {
            //ToDo
        }

        @Override
        public String toString() {
            return "bill sent";
        }
    }
    public class BillPaid implements BillingState {
        private Bill context;
        public BillPaid(Bill context) {
            this.context = context;
        }

        @Override
        public void sendBill() throws InvalidStateOrderException {
            throw new InvalidStateOrderException("Bill is already paid and therefore must not be paid again");
        }

        @Override
        public void receivePayment(int amount) throws InvalidStateOrderException {
            throw new InvalidStateOrderException("Bill was already paid before. Let's hope the client doesn't find out. (It would be kind to pay him back.)");
        }

        @Override
        public void checkStatus() throws InvalidStateOrderException {
            //Bill is paid and the status no longer changes
        }

        @Override
        public String toString() {
            return "bill paid";
        }
    }
    public class BillOverdue implements BillingState {
        private Bill context;
        public BillOverdue(Bill context) {
            this.context = context;
        }

        @Override
        public void sendBill() throws InvalidStateOrderException {
            context.setState(context.getBillSent());
            context.increaseBillNumber();
            //ToDo: set new due date
            //ToDo: ev. increase bill by reminder fee
        }

        @Override
        public void receivePayment(int amount) throws InvalidStateOrderException {
            context.setState(context.getBillPaid());
        }

        @Override
        public void checkStatus() throws InvalidStateOrderException {
            //Bill is already overdue, status doesn't have to be checked
        }

        @Override
        public String toString() {
            return "bill overdue";
        }
    }
    public class EngageRussianMafia implements BillingState {
        private Bill context;

        public EngageRussianMafia(Bill context) {
            this.context = context;
        }

        @Override
        public void sendBill() throws InvalidStateOrderException {
            throw new InvalidStateOrderException("Russian mafia is already going after the client.");
        }

        @Override
        public void receivePayment(int amount) throws InvalidStateOrderException {
            if(amount >= context.getAmount()) {
                context.setState(context.getBillPaid());
            }
        }

        @Override
        public void checkStatus() throws InvalidStateOrderException {
            //Do nothing
        }

        @Override
        public String toString() {
            return "russian mafia engaged";
        }
    }

    public static class InvalidStateOrderException extends Exception {
        public InvalidStateOrderException(String message) {
            super(message);
        }
    }
}
