package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;

/**
 * Method signatures for the TempCodeApiResponseDAO.
 * For the most part this will be a "write-only" DAO,
 * but I added some finder methods just in case.
 */
public interface TempCodeApiResponseDAO {
    Integer createTempCodeApiResponse(TempCodeApiResponse response);

    void deleteTempCodeApiResponse(TempCodeApiResponse response);

    //List<TempCodeApiResponse> findAll();
    TempCodeApiResponse findById(Integer id);
}
