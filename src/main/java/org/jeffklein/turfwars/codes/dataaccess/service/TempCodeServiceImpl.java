package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeApiResponseDAO;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeDAO;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Entry point for using the Hibernate DAOs. All methods on the service are @Transactional.
 */
@Service("tempCodeService")
@Transactional
public class TempCodeServiceImpl implements TempCodeService {

    @Autowired
    @Qualifier("tempCodeApiResponseDAO")
    private TempCodeApiResponseDAO tempCodeApiResponseDAO;

    @Autowired
    @Qualifier("tempCodeDAO")
    private TempCodeDAO tempCodeDAO;

    @Override
    public Integer saveTempCodeApiResponse(TempCodeApiResponse response) {
        return tempCodeApiResponseDAO.saveTempCodeApiResponse(response);
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
