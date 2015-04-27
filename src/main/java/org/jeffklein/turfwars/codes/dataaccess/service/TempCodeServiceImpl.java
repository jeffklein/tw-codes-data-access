package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.dao.SequenceDAO;
import org.jeffklein.turfwars.codes.dataaccess.dao.TempCodeDAO;
import org.jeffklein.turfwars.codes.dataaccess.model.Sequence;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Entry point for using the Hibernate DAOs. All methods on the service are @Transactional.
 */
@Service("tempCodeService")
@Transactional
public class TempCodeServiceImpl implements TempCodeService {

    @Autowired
    private TempCodeDAO tempCodeDAO;

    @Autowired
    private SequenceDAO sequenceDAO;

    @Override
    public Integer saveTempCodeBatch(Set<TempCode> batch) {
        Integer batchId = sequenceDAO.getNextId(Sequence.TW_TEMP_CODE_BATCH_ID);
        int count = 0;
        for (TempCode code : batch) {
            String tempCode = code.getCode();
            TempCode ifExists = tempCodeDAO.findByCode(tempCode);
            if (ifExists == null) {
                code.setBatchId(batchId);
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
