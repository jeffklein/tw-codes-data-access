package org.jeffklein.tw.tempcodes.dao;

import org.jeffklein.tw.tempcodes.dao.model.TempCode;

import java.util.List;

/**
 * Method signatures for the TempCodeDAO.
 * For the most part this will be a "write-only" DAO,
 * but I added some finder methods just in case.
 */
public interface TempCodeDAO {
    void saveTempCode(TempCode tempCode);
    List<TempCode> findAll();
    TempCode findById(Integer id);
    TempCode findByCode(String code);
    List<TempCode> findByApiResponseId(Integer apiResponseId);
}
