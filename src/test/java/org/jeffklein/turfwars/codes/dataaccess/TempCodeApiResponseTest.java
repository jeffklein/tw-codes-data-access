package org.jeffklein.turfwars.codes.dataaccess;

import org.jeffklein.turfwars.codes.client.TempCodeApiJsonResponse;
import org.jeffklein.turfwars.codes.client.TurfWarsApiClient;
import org.jeffklein.turfwars.codes.dataaccess.config.HibernateConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.config.SpringConfiguration;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeApiResponseDAO;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;

import org.jeffklein.turfwars.codes.dataaccess.service.TempCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * DAO test for TempCodeApiResponse JPA Entities
 */
@ContextConfiguration(classes = {SpringConfiguration.class, HibernateConfiguration.class})
public class TempCodeApiResponseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("turfWarsApiClient")
    private TurfWarsApiClient turfWarsApiClient;

    @Autowired
    @Qualifier("tempCodeApiResponseDAO")
    private TempCodeApiResponseDAO tempCodeApiResponseDAO;

    @Autowired
    @Qualifier("tempCodeService")
    private TempCodeService tempCodeService;

    @Test
    //@Transactional
    public void testSaveTempCodeApiResponse() {
        TempCodeApiJsonResponse jsonResponse = turfWarsApiClient.getTempCodeApiResponse();
        TempCodeApiResponse toPersist = new TempCodeApiResponse();
        toPersist.setNextUpdate(jsonResponse.getNextUpdate());
        toPersist.setTempCodes(copyTempCodesFromJsonResponse(jsonResponse.getTempCodes(), toPersist));
        toPersist.setTimestamp(jsonResponse.getTimestamp());
        tempCodeService.saveTempCodeApiResponse(toPersist);
    }

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
    }
}
