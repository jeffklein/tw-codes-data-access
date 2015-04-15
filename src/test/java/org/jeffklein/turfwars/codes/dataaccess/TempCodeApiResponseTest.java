package org.jeffklein.turfwars.codes.dataaccess;

import org.jeffklein.turfwars.codes.client.TempCodeApiClient;
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

    @Autowired
    private TempCodeApiClient apiClient;

    @Test
    public void testSaveTempCodeApiResponse() {
        TempCodeApiResponse response = (org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse) apiClient.getTempCodeApiResponse();
        // persist that shit.
    }
}
