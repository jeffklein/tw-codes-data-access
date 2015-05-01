package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.Sequence;

/**
 * TODO: javadoc needed
 *
 * @author jeffklein
 */
public interface SequenceDAO {
    Sequence createOrUpdateSequence(String sequenceName);

    Integer getNextId(String sequenceName);

    void incrementNextId(String sequenceName);

    Sequence findBySequenceName(String sequenceName);
}
