package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.HibernateUtil;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Bill;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 15.06.17.
 */
public class BillingServiceImpl implements BillingService {
    public BillingServiceImpl() {

    }

    @Override
    public List<Bill> getBills() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria crit = session.createCriteria(Bill.class);
            session.getTransaction().commit();

            return crit.list();
        } catch (HibernateException ex) {
            //getLogger().error("UserService", ex);
        }
        return new ArrayList<Bill>();
    }

    @Override
    public void addBill(Bill bill) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(bill);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            //getLogger().error("UserService", ex);
        }
    }
}
