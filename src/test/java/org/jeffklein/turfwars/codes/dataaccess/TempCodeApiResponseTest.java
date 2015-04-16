package org.jeffklein.turfwars.codes.dataaccess;

import org.jeffklein.turfwars.codes.client.TempCodeApiClient;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeApiResponseDAO;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeApiResponseDAOImpl;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jeff
 * Date: 4/14/15
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class TempCodeApiResponseTest {

    private TempCodeApiClient tempCodeApiClient = new TempCodeApiClient();;

    private TempCodeApiResponseDAO responseDAO = new TempCodeApiResponseDAOImpl();

    @Test
    public void testSaveTempCodeApiResponse() {
        org.jeffklein.turfwars.codes.client.TempCodeApiResponse jsonResponse = tempCodeApiClient.getTempCodeApiResponse();
        TempCodeApiResponse toPersist = new TempCodeApiResponse(jsonResponse);
        responseDAO.saveTempCodeApiResponse(toPersist);
    }
}
