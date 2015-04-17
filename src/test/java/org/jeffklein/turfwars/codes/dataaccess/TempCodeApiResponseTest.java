package org.jeffklein.turfwars.codes.dataaccess;

import org.jeffklein.turfwars.codes.client.TempCodeApiClient;
import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.config.SpringConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeApiResponseDAO;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jeff
 * Date: 4/14/15
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(classes = {SpringConfiguration.class, HibernateConfiguration.class})
public class TempCodeApiResponseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("tempCodeApiClient")
    private TempCodeApiClient tempCodeApiClient;

    @Autowired
    @Qualifier("tempCodeApiResponseDAO")
    private TempCodeApiResponseDAO responseDAO;

    @Test
    public void testSaveTempCodeApiResponse() {
        org.jeffklein.turfwars.codes.client.TempCodeApiJsonResponse jsonResponse = tempCodeApiClient.getTempCodeApiResponse();
        TempCodeApiResponse toPersist = new TempCodeApiResponse(jsonResponse);
        responseDAO.saveTempCodeApiResponse(toPersist);
    }
}
