package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.DateTest;
import org.jeffklein.turfwars.codes.dataaccess.util.DateTimeUtil;
import org.jeffklein.turfwars.codes.dataaccess.util.ScriptRunnerWrapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;

/**
 * Testing hibernate/joda/jadira date persistence.
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class DateTestDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DateTestDAO dateTestDAO;

    @Autowired
    private DataSource dataSource;

    private DateTest testDateTest;

    private Integer testDateTimeId;

    @BeforeClass
    public void createTestTables() {
        Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/createTestTables.ddl", dataSource);
        DateTime dateTime = DateTimeUtil.createPersistableUtcDateTime(1997, 5, 4, 18, 1, 4);
        this.testDateTest = new DateTest();
        this.testDateTest.setTheDateTime(dateTime);
        System.out.println("original DateTime in @BeforeClass: " + testDateTest.getTheDateTime().toString());
    }

    @Test
    public void testInsertDateTime() {
        this.testDateTimeId = this.dateTestDAO.insertDateTest(this.testDateTest);
    }

    @Test(dependsOnMethods = "testInsertDateTime")
    public void testRetrieveDateAndVertifyIdenticalToOriginal() {
        DateTest dt = this.dateTestDAO.findById(this.testDateTimeId);
        Assert.assertNotNull(dt);
        Assert.assertEquals(dt, this.testDateTest);
        Assert.assertEquals(dt.getTheDateTime(), this.testDateTest.getTheDateTime());
    }
}
