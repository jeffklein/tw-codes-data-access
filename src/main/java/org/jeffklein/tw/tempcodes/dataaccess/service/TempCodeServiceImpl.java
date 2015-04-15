package org.jeffklein.tw.tempcodes.dataaccess.service;

import org.jeffklein.tw.tempcodes.dataaccess.dao.TempCodeApiResponseDAO;
import org.jeffklein.tw.tempcodes.dataaccess.dao.TempCodeDAO;
import org.jeffklein.tw.tempcodes.dataaccess.model.TempCode;
import org.jeffklein.tw.tempcodes.dataaccess.model.TempCodeApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Entry point for using the Hibernate DAOs.
 */
public class TempCodeServiceImpl implements TempCodeService {

    @Autowired
    private TempCodeApiResponseDAO tempCodeApiResponseDAO;

    @Autowired
    private TempCodeDAO tempCodeDAO;

    @Override
    public void bulkInsertNewTempCodes(TempCodeApiResponse response) {


    }

    /*
     * Assumption: both of these finders will be sorted by expiration date
     */
    @Override
    public List<TempCode> findAllTempCodes() {
        return null;
    }

    @Override
    public List<TempCode> findAllUnexpiredTempCodes() {
        return null;
    }
}
