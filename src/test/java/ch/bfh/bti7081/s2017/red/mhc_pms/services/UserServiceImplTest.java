package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.common.utils.HibernateUtil;
import ch.bfh.bti7081.s2017.red.mhc_pms.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Samuel Egger
 */
public class UserServiceImplTest {

    private static PasswordService passwordService = null;
    private static List<User> testUsers = new ArrayList<>();
    
    public UserServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        passwordService = new Sha1PasswordService();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Create test data.
     */
    @Before
    public void setUp() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User userRolf = new User();
        userRolf.setUsername("rolf-ch");
        userRolf.seteMail("rolf@mhc-pms.ch");
        userRolf.setSalt(passwordService.createSalt());
        userRolf.setPasswordHash(passwordService.returnPasswordHashSalted("rolf.123", userRolf.getSalt()));
        session.save(userRolf);
        testUsers.add(userRolf);

        User adrian = new User();
        adrian.setUsername("adrian");
        adrian.seteMail("adrian@mhc-pms.ch");
        adrian.setSalt(passwordService.createSalt());
        adrian.setPasswordHash(passwordService.returnPasswordHashSalted("adrian.123", adrian.getSalt()));
        session.save(adrian);
        testUsers.add(adrian);
        
        session.getTransaction().commit();
    }

    /**
     * Clear test data.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of findUserById method, of class UserServiceImpl.
     */
    @Test
    public void testFindUserById() {
        System.out.println("findUserById");
        long userId = testUsers.get(0).getId();
        UserServiceImpl instance = new UserServiceImpl(passwordService);
        User expResult = testUsers.get(0);
        User result = instance.findUserById(userId);
        assertEquals(expResult.getEmail(), result.getEmail());
    }

    /**
     * Test of findUserByFilter method, of class UserServiceImpl.
     */
    @Test
    public void testFindUserByFilter() {
        System.out.println("findUserByFilter");
        String filter = "adrian";
        UserServiceImpl instance = new UserServiceImpl(passwordService);
        List<User> result = instance.findUserByFilter(filter);
        assertEquals(1, result.size());
        assertEquals("adrian", result.get(0).getUsername());
    }

    /**
     * Test of getUserByUserName method, of class UserServiceImpl.
     */
    @Test
    public void testGetUserByUserName() {
        System.out.println("getUserByUserName");
        String name = "";
        UserServiceImpl instance = new UserServiceImpl(passwordService);
        User expResult = null;
        User result = instance.getUserByUserName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateUser method, of class UserServiceImpl.
     */
    @Test
    public void testSaveOrUpdateUser() {
        System.out.println("saveOrUpdateUser");
        User user = null;
        UserServiceImpl instance = new UserServiceImpl(passwordService);
        instance.saveOrUpdateUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UserServiceImpl.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        long userId = 0L;
        UserServiceImpl instance = new UserServiceImpl(passwordService);
        instance.deleteUser(userId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLoginValid method, of class UserServiceImpl.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("isLoginValid");
        String userName = "";
        String password = "";
        UserServiceImpl instance = new UserServiceImpl(passwordService);
        boolean expResult = false;
        boolean result = instance.isLoginValid(userName, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
