package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.Sequence;
import org.jeffklein.turfwars.codes.dataaccess.util.ScriptRunnerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sql.DataSource;

/**
 * Test for the SequenceDAO
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class SequenceDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    private DataSource dataSource;

    @BeforeClass
    public void resetTestSchemaBeforeRunningTests() {
        Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/create_tables.ddl", dataSource);
    }

    @AfterClass
    public void resetTestSchemaAfterRunningTests() {
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/create_tables.ddl", dataSource);
    }

    @Test
    public void testCreateSequence() {
        Sequence sequence = sequenceDAO.createOrUpdateSequence("test");
        Assert.assertNotNull(sequence);
        Assert.assertEquals((int) sequence.getId(), 1);
    }

    /**
     * next_id column should have the default value '1'
     */
    @Test(dependsOnMethods = "testCreateSequence")
    public void testIncrementNextId() {
        Integer nextId = sequenceDAO.getNextId("test");
        Assert.assertNotNull(nextId);
        Assert.assertEquals((int) nextId, 1);
        sequenceDAO.incrementNextId("test");
    }

    /**
     * next_id column should be incremented to '2' this time around.
     */
    @Test(dependsOnMethods = "testIncrementNextId")
    public void testIncrementNextIdAgain() {
        Integer nextId = sequenceDAO.getNextId("test");
        Assert.assertNotNull(nextId);
        Assert.assertEquals((int) nextId, 2);
        sequenceDAO.incrementNextId("test");
    }

    /**
     * next_id column should be incremented to '3' this time around.
     */
    @Test(dependsOnMethods = "testIncrementNextIdAgain")
    public void testIncrementNextIdThirdTime() {
        Integer nextId = sequenceDAO.getNextId("test");
        Assert.assertNotNull(nextId);
        Assert.assertEquals((int) nextId, 3);
    }
}
