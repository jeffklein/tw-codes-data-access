package org.jeffklein.turfwars.codes.dataaccess.dao;

import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.Role;
import org.jeffklein.turfwars.codes.dataaccess.util.ScriptRunnerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by jklein on 5/9/15.
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class RoleDAOTest extends AbstractTestNGSpringContextTests {

    private static Log LOG = LogFactory.getLog(RoleDAOTest.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RoleDAO roleDAO;

    public static final String TEST_ROLE_1 = "first-role";
    public static final String TEST_ROLE_2 = "second-role";
    public static final String TEST_ROLE_3 = "third-role";
    public static final String TEST_UPDATED_ROLE_1 = "updated-first-role";

    @BeforeClass
    public void resetTestSchemaBeforeRunningTests() {
        Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/create_tables.ddl", dataSource);
    }

    @Test
    public void testCreateFirstRole() {
        Integer firstRoleId = this.roleDAO.createRole(TEST_ROLE_1);
        Assert.assertNotNull(firstRoleId);
        Assert.assertTrue(firstRoleId > 0);
        Integer secondRoleId = this.roleDAO.createRole(TEST_ROLE_2);
        Assert.assertNotNull(secondRoleId);
        Assert.assertTrue(secondRoleId > firstRoleId);
        Integer thirdRoleId = this.roleDAO.createRole(TEST_ROLE_3);
        Assert.assertNotNull(thirdRoleId);
        Assert.assertTrue(thirdRoleId > secondRoleId);
    }

    @Test(dependsOnMethods = "testCreateFirstRole", expectedExceptions = {org.hibernate.exception.ConstraintViolationException.class})
    public void testInsertFirstRoleAgainFails() {
        this.roleDAO.createRole(TEST_ROLE_1);
    }

    @Test(dependsOnMethods = "testCreateFirstRole")
    public void testFindByRoleName() {
        Role role1 = this.roleDAO.findRoleByName(TEST_ROLE_1);
        Assert.assertNotNull(role1);
        Assert.assertEquals(TEST_ROLE_1, role1.getRoleName());
        Role role2 = this.roleDAO.findRoleByName(TEST_ROLE_2);
        Assert.assertNotNull(role2);
        Assert.assertEquals(TEST_ROLE_2, role2.getRoleName());
        Role role3 = this.roleDAO.findRoleByName(TEST_ROLE_3);
        Assert.assertNotNull(role3);
        Assert.assertEquals(TEST_ROLE_3, role3.getRoleName());
        Role bogusRole = this.roleDAO.findRoleByName("doesn't exist");
        Assert.assertNull(bogusRole);
    }

    @Test(dependsOnMethods = "testCreateFirstRole")
    public void testFindAllRoles() {
        List<Role> allRoles = this.roleDAO.findAllRoles();
        Assert.assertNotNull(allRoles);
        Assert.assertEquals(3, allRoles.size());
    }

    @Test(dependsOnMethods = "testCreateFirstRole")
    public void testUpdateRole() {
        Role firstRole = this.roleDAO.findRoleByName(TEST_ROLE_1);
        Assert.assertNotNull(firstRole);
        firstRole.setRoleName(TEST_UPDATED_ROLE_1);
        this.roleDAO.updateRole(firstRole);
        firstRole = this.roleDAO.findRoleByName(TEST_ROLE_1);
        Assert.assertNull(firstRole);
        Role updatedRole = this.roleDAO.findRoleByName(TEST_UPDATED_ROLE_1);
        Assert.assertNotNull(updatedRole);
        Assert.assertEquals(TEST_UPDATED_ROLE_1, updatedRole.getRoleName());
    }

    @Test(dependsOnMethods = "testUpdateRole")
    public void testDeleteRole() {
        Role secondRole = this.roleDAO.findRoleByName(TEST_ROLE_2);
        Assert.assertNotNull(secondRole);
        this.roleDAO.deleteRole(secondRole);
        secondRole = this.roleDAO.findRoleByName(TEST_ROLE_2);
        Assert.assertNotNull(secondRole);
    }
}
