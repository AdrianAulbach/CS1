package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.HibernateUtil;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Default implementation of the UserService interface with Hibernate to persist
 * objects in a database.
 *
 * @author Samuel Egger
 */
public class UserServiceImpl extends ServiceBase implements UserService {
    
    private final PasswordService passwordService;
    
    public UserServiceImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User findUserById(long userId) {
        User result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = (User) session.get(User.class, userId);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("UserService", ex);
        }
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findUserByFilter(String filter) {
        List<User> result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Criteria crit = session.createCriteria(User.class);
            crit.add(Restrictions.like("username", filter, MatchMode.ANYWHERE));
            result = crit.list();
            
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("UserService", ex);
        }
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByUserName(String name) {
        User result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Criteria crit = session.createCriteria(User.class);
            crit.add(Restrictions.eq("username", name));
            result = (User) crit.uniqueResult();
            
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("UserService", ex);
        }
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdateUser(User user) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            getLogger().error("UserService", ex);
        }
    }
    
    /**
     * {@inheritDoc}
     */
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
        } catch (HibernateException ex) {
            getLogger().error("UserService", ex);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPassword(String userName, String password) {
        getLogger().debug("checking password for: " + userName);
        
        User userTestPassword = getUserByUserName(userName);
        if (userTestPassword == null) {
            getLogger().info("User '" + userName + "' was not found.");
            return false;
        }
        
        getLogger().debug("got user:" + userTestPassword.getUsername());
        
        byte[] userSalt = userTestPassword.getSalt();
        getLogger().debug("user salt " + userSalt);
        byte[] passwordHash = passwordService.returnPasswordHashSalted(password, userSalt).getBytes();
        String enteredPasswordHashBase64 = java.util.Base64.getEncoder().encodeToString(passwordHash);

        //test if the password matches the specified user
        if (userTestPassword.getPasswordHash().equals(enteredPasswordHashBase64)) {
            return true;
        }
        return false;
    }
}
