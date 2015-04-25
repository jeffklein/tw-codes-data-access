package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;
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

/**
 * DAO test for TempCodeApiResponse JPA Entities CRUD operations.
 * Well most of CRUD anyway- I skipped update tests as this entity
 * should never be updated.
 */
@ContextConfiguration(classes = {HibernateConfiguration.class})
public class TempCodeApiResponseDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TempCodeApiResponseDAO tempCodeApiResponseDAO;

    @Autowired
    private DataSource dataSource;

    private Integer apiResponseId;

    private TempCodeApiResponse respFromDb;

    @BeforeClass
    public void resetTestSchemaBeforeRunningTests() {
        junit.framework.Assert.assertNotNull(dataSource);
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/reset_db.ddl", dataSource);
    }

    @AfterClass
    public void resetTestSchemaAfterRunningTests() {
        ScriptRunnerWrapper.runScriptFromClasspath("/org/jeffklein/turfwars/codes/dataaccess/sql/reset_db.ddl", dataSource);
    }

    @Test
    public void testCreateTempCodeApiResponse() {
        TempCodeApiResponse toPersist = TestFixtureHelper.createTempCodeApiResponse();
        this.apiResponseId = tempCodeApiResponseDAO.createTempCodeApiResponse(toPersist);
    }

    @Test(dependsOnMethods = "testCreateTempCodeApiResponse")
    public void testRetrieveTempCodeApiResponse() {
        this.respFromDb = tempCodeApiResponseDAO.findById(this.apiResponseId);
        Assert.assertNotNull(respFromDb);
        Assert.assertNotNull(respFromDb.getTimestamp());
        Assert.assertNotNull(respFromDb.getNextUpdate());

        Assert.assertEquals(respFromDb.getTempCodes().size(), 5);
        for (TempCode tempCode : respFromDb.getTempCodes()) {
            Assert.assertNotNull(tempCode);
            Assert.assertNotNull(tempCode.getApiResponseId());
            Assert.assertNotNull(tempCode.getCode());
            Assert.assertNotNull(tempCode.getExpires());
            Assert.assertNotNull(tempCode.getId());
            Assert.assertEquals(tempCode.getTempCodeApiResponse().getId(), tempCode.getApiResponseId());
        }
    }

    @Test(dependsOnMethods = "testRetrieveTempCodeApiResponse")
    public void testDeleteTempCodeApiResponse() {
        Assert.assertNotNull(this.respFromDb);
        tempCodeApiResponseDAO.deleteTempCodeApiResponse(respFromDb);

        // attempt retrieve to prove that it is gone from db
        TempCodeApiResponse recentlyDeleted = tempCodeApiResponseDAO.findById(apiResponseId);
        Assert.assertNull(recentlyDeleted);
    }

/* TODO: I might need this code in the backend app itself later, but it obviously does not belong here!
  private Set<TempCode> copyTempCodesFromJsonResponse(Set<org.jeffklein.turfwars.codes.client.TempCode> jsonCodes, TempCodeApiResponse apiResponse) {
        Set<TempCode> dbCodes = new HashSet<TempCode>();
        for (org.jeffklein.turfwars.codes.client.TempCode jsonCode : jsonCodes) {
            TempCode dbCode = new TempCode();
            dbCode.setCode(jsonCode.getCode());
            dbCode.setExpires(jsonCode.getExpires());
            dbCode.setTempCodeApiResponse(apiResponse);
            //dbCode.setApiResponseId(apiResponse.getId());
            dbCodes.add(dbCode);
        }
        return dbCodes;
    }*/
}
