package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;

import java.util.List;

/**
 * Entry point for using the Hibernate DAOs.
 */
public interface TempCodeService {
    void bulkInsertNewTempCodes(TempCodeApiResponse response);
    List<TempCode> findAllTempCodes();
    List<TempCode> findAllUnexpiredTempCodes();
}
