package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;

import java.util.List;

/**
 * Method signatures for the TempCodeDAO.
 */
public interface TempCodeDAO {
    Integer saveTempCode(TempCode tempCode);

    List<TempCode> findAll();

    List<TempCode> findAllInBatch(Integer batchId);

    TempCode findById(Integer id);

    TempCode findByCode(String code);

}
