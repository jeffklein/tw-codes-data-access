package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.Sequence;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.springframework.stereotype.Repository;

/**
 * TODO: javadoc needed
 *
 * @author jeffklein
 */
@Repository("sequenceDAO")
public class SequenceDAOImpl extends AbstractHibernateDAO implements SequenceDAO {
    @Override
    public Sequence createOrUpdateSequence(String sequenceName) {
        Sequence sequence = findBySequenceName(sequenceName);
        if (sequence == null) {
            sequence = new Sequence(sequenceName);
            create(sequence);
        }
        else {
            update(sequence);
        }
        return sequence;
    }

    @Override
    public Integer getNextId(String sequenceName) {
        Sequence sequence = findBySequenceName(sequenceName);
        if (sequence == null) {
            createOrUpdateSequence(sequenceName);
            sequence = findBySequenceName(sequenceName);
        }
        Integer returnVal = sequence.getNextId();
        sequence.incrementNextId();
        createOrUpdateSequence(sequenceName);
        return returnVal;
    }

    @Override
    public Sequence findBySequenceName(String sequenceName) {
        Criteria criteria = getSession().createCriteria(Sequence.class);
        criteria.add(Restrictions.eq("sequenceName", sequenceName));
        return (Sequence) criteria.uniqueResult();
    }
}
