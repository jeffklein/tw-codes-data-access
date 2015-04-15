package org.jeffklein.tw.tempcodes.dataaccess.service;

import org.jeffklein.tw.tempcodes.dataaccess.model.TempCode;
import org.jeffklein.tw.tempcodes.dataaccess.model.TempCodeApiResponse;

import java.util.List;

/**
 * Entry point for using the Hibernate DAOs.
 */
public interface TempCodeService {
    void bulkInsertNewTempCodes(TempCodeApiResponse response);
    List<TempCode> findAllTempCodes();
    List<TempCode> findAllUnexpiredTempCodes();
}
