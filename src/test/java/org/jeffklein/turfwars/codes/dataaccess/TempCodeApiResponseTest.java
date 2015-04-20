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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DAO test for TempCodeApiResponse JPA Entities
 */
@ContextConfiguration(classes = {SpringConfiguration.class, HibernateConfiguration.class})
public class TempCodeApiResponseTest extends AbstractTestNGSpringContextTests {

//    @Autowired
//    @Qualifier("turfWarsApiClient")
//    private TurfWarsApiClient turfWarsApiClient;

//    @Autowired
//    @Qualifier("tempCodeApiResponseDAO")
//    private TempCodeApiResponseDAO tempCodeApiResponseDAO;

    @Autowired
    @Qualifier("tempCodeService")
    private TempCodeService tempCodeService;

    @Test
    public void testSaveTempCodeApiResponseWithService() {
        //TempCodeApiJsonResponse jsonResponse = turfWarsApiClient.getTempCodeApiResponse();
        TempCodeApiResponse toPersist = new TempCodeApiResponse();
        toPersist.setNextUpdate(new Date(System.currentTimeMillis()));
        toPersist.setTimestamp(new Date(System.currentTimeMillis()));
        toPersist.setTempCodes(makeTestTempCodeData(toPersist));
        tempCodeService.saveTempCodeApiResponse(toPersist);
    }

    private Set<TempCode> makeTestTempCodeData(TempCodeApiResponse apiResponse) {
        Set<TempCode> codes = new HashSet<TempCode>();
        for (int i = 0 ; i < 5 ; i++) {
            TempCode code = new TempCode();
            code.setCode("-123-45"+i);
            code.setExpires(new Date(System.currentTimeMillis()));
            code.setTempCodeApiResponse(apiResponse);
            codes.add(code);
        }
        return codes;
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
