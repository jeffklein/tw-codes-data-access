package org.jeffklein.turfwars.codes.dataaccess;

import org.jeffklein.turfwars.codes.client.TurfWarsApiClient;
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
 * Autowire this, beeyatch!
 */
@ContextConfiguration(classes = {SpringConfiguration.class, HibernateConfiguration.class})
public class TempCodeApiResponseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("turfWarsApiClient")
    private TurfWarsApiClient turfWarsApiClient;

    @Autowired
    @Qualifier("tempCodeApiResponseDAO")
    private TempCodeApiResponseDAO tempCodeApiResponseDAO;

    @Test
    public void testSaveTempCodeApiResponse() {
        org.jeffklein.turfwars.codes.client.TempCodeApiJsonResponse jsonResponse = turfWarsApiClient.getTempCodeApiResponse();
        TempCodeApiResponse toPersist = new TempCodeApiResponse(jsonResponse);
        tempCodeApiResponseDAO.saveTempCodeApiResponse(toPersist);
    }
}
