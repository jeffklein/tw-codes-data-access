package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeDAO;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Entry point for using the Hibernate DAOs. All methods on the service are @Transactional.
 */
@Service("tempCodeService")
@Transactional
public class TempCodeServiceImpl implements TempCodeService {

    @Autowired
    private TempCodeDAO tempCodeDAO;

    @Override
    public Integer saveTempCodeBatch(Set<TempCode> batch) {
        int count = 0;
        for (TempCode code : batch) {
            String tempCode = code.getCode();
            TempCode ifExists = tempCodeDAO.findByCode(tempCode);
            if (ifExists == null) {
                tempCodeDAO.saveTempCode(code);
                count++;
            }
        }
        return count;
    }

    @Override
    public Integer countAllTempCodes() {
        return tempCodeDAO.findAll().size();
    }
}
