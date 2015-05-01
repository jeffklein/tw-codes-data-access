package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
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
import java.util.List;
import java.util.Set;

/**
 * Tests for the TempCode Data Access Object
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class TempCodeDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TempCodeDAO tempCodeDAO;

    @Autowired
    private DataSource dataSource;

    private TempCode randomTempCode = TestFixtureHelper.makeRandomTempCode();

    private int randomTempCodeId;

    @BeforeClass
    public void resetTestSchemaBeforeRunningTests() {
        Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/reset_db.ddl", dataSource);
    }

//    @AfterClass
//    public void resetTestSchemaAfterRunningTests() {
//        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/reset_db.ddl", dataSource);
//    }

    @Test
    public void testInsertTempCode() {
        this.randomTempCodeId = tempCodeDAO.saveTempCode(randomTempCode);
    }

    /**
     * Rerunning the insertion above to ensure we don't wind up with duplicates.
     * This test should cause a DuplicateKeyException (ConstraintViolationException).
     * It will fail if the exception is not thrown.
     */
    @Test(dependsOnMethods = "testInsertTempCode", expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
    public void testInsertTempCodeAgain() {
        testInsertTempCode();
    }

    @Test(dependsOnMethods = "testInsertTempCode")
    public void testFindById() {
        TempCode fromDb = tempCodeDAO.findById(this.randomTempCodeId);
        Assert.assertNotNull(fromDb);
        Assert.assertEquals(fromDb, randomTempCode);
    }

    @Test(dependsOnMethods = "testInsertTempCode")
    public void testFindAll() {
        List<TempCode> allCodes = this.tempCodeDAO.findAll();
        Assert.assertNotNull(allCodes);
        Assert.assertEquals(allCodes.size(), 1);
        Assert.assertEquals(allCodes.get(0), this.randomTempCode);
    }

    @Test(dependsOnMethods = "testInsertTempCode")
    public void testFindByCode() {
        TempCode code = this.tempCodeDAO.findByCode(this.randomTempCode.getCode());
        Assert.assertEquals(code, randomTempCode);
    }

    @Test(dependsOnMethods = "testInsertTempCode")
    public void testInsertBatchOfTempCodes() {
        Set<TempCode> batch = TestFixtureHelper.makeRandomTempCodeBatch(5);
        for (TempCode code : batch) {
            String tempCode = code.getCode();
            TempCode ifExists = tempCodeDAO.findByCode(tempCode);
            if (ifExists == null) {
                tempCodeDAO.saveTempCode(code);
            }
        }
        List<TempCode> allCodes = this.tempCodeDAO.findAll();
        Assert.assertEquals(allCodes.size(), 6);
    }

    @Test(dependsOnMethods = "testInsertBatchOfTempCodes")
    public void testFindAllInBatch() {
        List<TempCode> allInOrigBatch = this.tempCodeDAO.findAllInBatch(1);
        Assert.assertEquals(allInOrigBatch.size(), 1);
        List<TempCode> allInNewBatch = this.tempCodeDAO.findAllInBatch(5);
        Assert.assertEquals(allInNewBatch.size(), 5);
    }

    @Test
    public void testTrimMillisFromSeconds() {
        DateTime dt = new DateTime(System.currentTimeMillis());
        long origMillis = dt.getMillis();
        DateTime roundedToClosestSecond = TempCode.trimMillisToSecond(dt);
        Assert.assertTrue(roundedToClosestSecond.getMillis() < origMillis);
    }


}