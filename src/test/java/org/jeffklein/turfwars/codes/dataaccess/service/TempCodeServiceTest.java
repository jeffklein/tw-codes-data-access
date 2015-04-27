package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.util.ScriptRunnerWrapper;
import org.jeffklein.turfwars.codes.dataaccess.util.TestFixtureHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.util.Set;

/**
 * DAO test for TempCodeApiResponse JPA Entities CRUD operations.
 * Well most of CRUD anyway- I skipped update tests as this entity
 * should never be updated.
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class TempCodeServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TempCodeService tempCodeService;

    @Autowired
    private DataSource dataSource;

    @BeforeClass
    public void resetTestSchemaBeforeRunningTests() {
        Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/reset_db.ddl", dataSource);
    }

    @AfterClass
    public void resetTestSchemaAfterRunningTests() {
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/reset_db.ddl", dataSource);
    }

    @Test
    public void testInsertTempCodeBatch() {
        Set<TempCode> tempCodeBatch = TestFixtureHelper.makeRandomTempCodeBatch(10);
        Assert.assertEquals(tempCodeBatch.size(), 5);
        Integer numInserted = tempCodeService.saveTempCodeBatch(tempCodeBatch);
        Assert.assertEquals((int) numInserted, tempCodeBatch.size());
    }

    /**
     * Rerunning the insertion above to ensure we don't wind up with duplicates.
     */
    @Test(dependsOnMethods = "testInsertTempCodeBatch")
    public void testInsertTempCodeBatchAgain() {
        testInsertTempCodeBatch();
    }
}
