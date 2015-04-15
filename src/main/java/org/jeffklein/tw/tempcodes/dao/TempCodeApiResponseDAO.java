package org.jeffklein.tw.tempcodes.dao;

import org.jeffklein.tw.tempcodes.dao.model.TempCodeApiResponse;

import java.util.List;

/**
 * Method signatures for the TempCodeApiResponseDAO.
 * For the most part this will be a "write-only" DAO,
 * but I added some finder methods just in case.
 */
public interface TempCodeApiResponseDAO {
    void saveTempCodeApiResponse(TempCodeApiResponse response);
    List<TempCodeApiResponse> findAll();
    TempCodeApiResponse findById(Integer id);
}
