package org.jeffklein.turfwars.codes.dataaccess.dao;

import junit.framework.Assert;
import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.config.SpringConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * CRUD tests for the UserDAO.
 */
@ContextConfiguration(classes = {SpringConfiguration.class, HibernateConfiguration.class})
public class UserDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    private Integer testUserId;
    private User testUser;

    private static final String TEST_USERNAME = "test_user";
    private static final String TEST_PASSWORD = "secret";
    private static final String TEST_EMAIL = "email@tw.com";
    private static final String TEST_TURFWARS_NAME = "KT";
    private static final String TEST_TIMEZONE_PREF = "PST";
    private static final Boolean TEST_HIDE_UNUSED_CODES_PREF = true;
    private static final Date TEST_LAST_LOGIN_DATE = new Date(System.currentTimeMillis());
    private static final String TEST_CHANGED_EMAIL = "mynew@email.com";

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername(TEST_USERNAME);
        user.setPassword(TEST_PASSWORD);
        user.setHideUsedCodesPref(TEST_HIDE_UNUSED_CODES_PREF);
        user.setLastLogin(TEST_LAST_LOGIN_DATE);
        user.setTurfwarsName(TEST_TURFWARS_NAME);
        user.setEmail(TEST_EMAIL);
        user.setTimezonePref(TEST_TIMEZONE_PREF);
        this.testUserId = userDAO.createUser(user);
        Assert.assertNotNull(this.testUserId);
        Assert.assertTrue(this.testUserId > 0);
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testRetrieveUserById() {
        User fromDbById = userDAO.findById(this.testUserId);
        Assert.assertNotNull(fromDbById);
        this.testUser = fromDbById;
        Assert.assertEquals(TEST_TURFWARS_NAME, this.testUser.getTurfwarsName());
        Assert.assertEquals(TEST_USERNAME, this.testUser.getUsername());
        //TODO: validate the rest of the fields

    }

    @Test(dependsOnMethods = "testRetrieveUserById")
    public void testRetrieveUserByUsername() {
        User fromDbByUsername = userDAO.findByUsername(TEST_USERNAME);
        Assert.assertNotNull(fromDbByUsername);
        Assert.assertEquals(this.testUser.getId(), fromDbByUsername.getId());
        Assert.assertEquals(this.testUserId, fromDbByUsername.getId());
        Assert.assertEquals(TEST_TURFWARS_NAME, fromDbByUsername.getTurfwarsName());
        //TODO: validate the rest of the fields
    }

    @Test(dependsOnMethods = "testRetrieveUserByUsername")
    public void testUpdateUser() {
        this.testUser.setEmail(TEST_CHANGED_EMAIL);
        userDAO.updateUser(this.testUser);
        User fromDbByUsername = userDAO.findByUsername(TEST_USERNAME);
        Assert.assertNotNull(fromDbByUsername);
        Assert.assertEquals(this.testUser.getId(), fromDbByUsername.getId());
        Assert.assertEquals(this.testUserId, fromDbByUsername.getId());
        Assert.assertEquals(TEST_TURFWARS_NAME, fromDbByUsername.getTurfwarsName());
        Assert.assertEquals(TEST_CHANGED_EMAIL, fromDbByUsername.getEmail());
        // TODO: validate the rest of the fields
    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        userDAO.deleteUser(this.testUser);
        User fromDbByUsername = userDAO.findByUsername(TEST_USERNAME);
        Assert.assertNull(fromDbByUsername); // gone from db
    }
}
