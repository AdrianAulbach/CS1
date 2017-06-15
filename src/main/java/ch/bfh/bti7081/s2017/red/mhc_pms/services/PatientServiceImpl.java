package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.HibernateUtil;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Patient;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Default implementation of the PatientService interface with Hibernate to
 * persist objects against a database.
 *
 * @author Samuel Egger
 */
public class PatientServiceImpl extends ServiceBase implements PatientService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient findPatientById(long patientId) {
        Patient result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = (Patient) session.get(Patient.class, patientId);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("PatientService", ex);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Patient> findPatientsByFilter(String filter) {
        List<Patient> result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Criteria crit = session.createCriteria(Patient.class);
            crit.add(Restrictions.disjunction()
                    .add(Restrictions.like("firstName", filter, MatchMode.ANYWHERE))
                    .add(Restrictions.like("lastName", filter, MatchMode.ANYWHERE))
            );
            result = crit.list();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("PatientService", ex);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdatePatient(Patient patient) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(patient);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("PatientService", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePatient(long patientId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Patient patient = (Patient) session.get(Patient.class, patientId);
            if (patient != null) {
                session.delete(patient);
            }
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("PatientService", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyPatient(long patientId, String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
