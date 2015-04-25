package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;

import java.util.List;

/**
 * Method signatures for the TempCodeDAO.
 * For the most part this will be a "write-only" DAO,
 * but I added some finder methods just in case.
 */
public interface TempCodeDAO {
    Integer saveTempCode(TempCode tempCode);

    List<TempCode> findAll();

    TempCode findById(Integer id);

    TempCode findByCode(String code);

    List<TempCode> findByApiResponseId(Integer apiResponseId);
}
