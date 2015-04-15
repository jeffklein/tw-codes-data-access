package org.jeffklein.tw.tempcodes.dao.service;

import org.jeffklein.tw.tempcodes.dao.model.TempCode;
import org.jeffklein.tw.tempcodes.dao.model.TempCodeApiResponse;

import java.util.List;

/**
 * Entry point for using the Hibernate DAOs.
 */
public interface TempCodeService {
    void bulkInsertNewTempCodes(TempCodeApiResponse response);
    List<TempCode> findAllTempCodes();
    List<TempCode> findAllUnexpiredTempCodes();
}
