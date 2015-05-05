package org.jeffklein.turfwars.codes.dataaccess.service;

import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;

import java.util.List;
import java.util.Set;

/**
 * Entry point for using the Hibernate DAOs.
 */
public interface TempCodeService {
    Integer saveTempCodeBatch(Set<TempCode> batch);

    List<TempCode> findAllTempCodes();
    List<TempCode> findAllUnexpiredTempCodes();
}
