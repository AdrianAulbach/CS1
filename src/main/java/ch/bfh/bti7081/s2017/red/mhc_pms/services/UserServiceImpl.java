package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 * Default implementation of the UserService interface with Hibernate
 * to persist objects in a database.
 * 
 * @author Samuel Egger
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findUserById(long userId) {
        User result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = (User) session.get(User.class, userId);
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO: Log4j
        }
        return result;
    }

    @Override
    public List<User> findUserByFilter(String filter) {
        return new ArrayList<User>();
        // ToDo throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByUserName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdateUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(long userId) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            // TODO: Log4j
        }
    }

    @Override
    public boolean checkPassword(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
