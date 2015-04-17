package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeApiResponseDAO;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeDAO;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;
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
    public void saveTempCodeApiResponse(TempCodeApiResponse response) {
        tempCodeApiResponseDAO.saveTempCodeApiResponse(response);
    }

    /*
     * Assumption: both of these finders will be sorted by expiration date
     */
    @Override
    public List<TempCode> findAllTempCodes() {
        return tempCodeDAO.findAll();
    }

    @Override
    public List<TempCode> findAllUnexpiredTempCodes() {
        throw new RuntimeException("not implemented yet!");
    }
}