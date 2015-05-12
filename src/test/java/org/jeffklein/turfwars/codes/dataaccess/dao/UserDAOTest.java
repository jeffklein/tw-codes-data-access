package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.Role;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.User;
import org.jeffklein.turfwars.codes.dataaccess.model.UserRole;
import org.jeffklein.turfwars.codes.dataaccess.service.TempCodeService;
import org.jeffklein.turfwars.codes.dataaccess.util.ScriptRunnerWrapper;
import org.jeffklein.turfwars.codes.dataaccess.util.TestFixtureHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.util.Set;

/**
 * CRUD tests for the UserDAO.
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class UserDAOTest extends AbstractTestNGSpringContextTests {

    private static Log LOG = LogFactory.getLog(UserDAOTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private TempCodeService tempCodeService;

    @BeforeClass
    public void resetTestSchemaBeforeRunningTests() {
        Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/create_tables.ddl", dataSource);
        this.roleDAO.createRole(TEST_ROLE_USER);
        this.roleDAO.createRole(TEST_ROLE_TESTER);
    }

/*    @AfterClass
    public void resetTestSchemaAfterRunningTests() {
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/create_tables.ddl", dataSource);
    }*/

    private Integer testUserId;
    private User testUser;

    private static final String TEST_USERNAME = "test_user";
    private static final String TEST_PASSWORD = "secret";
    private static final String TEST_EMAIL = "email@tw.com";
    private static final String TEST_TURFWARS_NAME = "KT";
    private static final String TEST_TIMEZONE_PREF = "PST";
    private static final Boolean TEST_HIDE_UNUSED_CODES_PREF = true;
    private static final DateTime TEST_LAST_LOGIN_DATE = new DateTime(System.currentTimeMillis());
    private static final String TEST_CHANGED_EMAIL = "mynew@email.com";
    private static final String TEST_ROLE_TESTER = "ROLE_TESTER";
    private static final String TEST_ROLE_USER = "ROLE_USER";

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
        Role tester = roleDAO.findRoleByName(TEST_ROLE_TESTER);
        UserRole userRoleTester = new UserRole(user, tester);
        user.addUserRole(userRoleTester);
        this.testUserId = userDAO.createUser(user);
        Assert.assertNotNull(this.testUserId);
        Assert.assertTrue(this.testUserId > 0);
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testRetrieveUserById() {
        User fromDbById = userDAO.findById(this.testUserId);
        Assert.assertNotNull(fromDbById);
        this.testUser = fromDbById;
        Assert.assertEquals(this.testUser.getTurfwarsName(), TEST_TURFWARS_NAME);
        Assert.assertEquals(this.testUser.getUsername(), TEST_USERNAME);
        //TODO: validate the rest of the fields

    }

    @Test(dependsOnMethods = "testRetrieveUserById")
    public void testRetrieveUserByUsername() {
        User fromDbByUsername = userDAO.findByUsername(TEST_USERNAME);
        Assert.assertNotNull(fromDbByUsername);
        Assert.assertEquals(fromDbByUsername.getId(), this.testUser.getId());
        Assert.assertEquals(fromDbByUsername.getId(), this.testUserId);
        Assert.assertEquals(fromDbByUsername.getTurfwarsName(), TEST_TURFWARS_NAME);
        Assert.assertEquals(fromDbByUsername.getUserRoles().size(), 1);
        //TODO: validate the rest of the fields
    }

    @Test(dependsOnMethods = "testRetrieveUserByUsername")
    public void testUpdateUser() {
        this.testUser.setEmail(TEST_CHANGED_EMAIL);
        this.testUser.addUserRole(new UserRole(this.testUser, new Role(TEST_ROLE_USER)));
        userDAO.updateUser(this.testUser);
        User fromDbByUsername = userDAO.findByUsername(TEST_USERNAME);
        Assert.assertNotNull(fromDbByUsername);
        Assert.assertEquals(fromDbByUsername.getId(), this.testUser.getId());
        Assert.assertEquals(fromDbByUsername.getId(), this.testUserId);
        Assert.assertEquals(fromDbByUsername.getTurfwarsName(), TEST_TURFWARS_NAME);
        Assert.assertEquals(fromDbByUsername.getEmail(), TEST_CHANGED_EMAIL);
        Assert.assertEquals(fromDbByUsername.getUserRoles().size(), 2);
        // TODO: validate the rest of the fields
    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testAssociatePunchedTempCodeWithUser() {
        Set<TempCode> batch = TestFixtureHelper.makeRandomTempCodeBatch(-100, true);
        tempCodeService.saveTempCodeBatch(batch);
        userDAO.associatePunchedTempCodeWithUser(batch.iterator().next(), testUser);
        User fromDbWithOnePunchedCode = userDAO.findById(this.testUser.getId());
        Assert.assertNotNull(fromDbWithOnePunchedCode);
        Assert.assertEquals(fromDbWithOnePunchedCode.getTempCodesAlreadyPunched().size(), 1);
    }

    //TODO: enable this test
    @Test(dependsOnMethods = "testAssociatePunchedTempCodeWithUser")
    public void testDeleteUser() {
        userDAO.deleteUser(this.testUser);
        User fromDbByUsername = userDAO.findByUsername(TEST_USERNAME);
        Assert.assertNull(fromDbByUsername); // gone from db
    }

    public void testTempCodesAlreadyPunchedDeletedWhenUserDeleted() {
        // user_temp_code should be empty after owning user deleted
    }
}
