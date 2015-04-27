package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of DAO methods required by TempCodeDAO.
 */
@Repository("tempCodeDAO")
public class TempCodeDAOImpl extends AbstractHibernateDAO implements TempCodeDAO {
    @Override
    public Integer saveTempCode(TempCode tempCode) {
        return (Integer) create(tempCode);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TempCode> findAll() {
        Criteria criteria = getSession().createCriteria(TempCode.class);
        return (List<TempCode>) criteria.list();
    }

    @Override
    public List<TempCode> findAllInBatch(Integer batchId) {
        Criteria criteria = getSession().createCriteria(TempCode.class);
        criteria.add(Restrictions.eq("batchId", batchId));
        return criteria.list();
    }

    @Override
    public TempCode findById(Integer id) {
        Criteria criteria = getSession().createCriteria(TempCode.class);
        criteria.add(Restrictions.eq("id", id));
        return (TempCode) criteria.uniqueResult();
    }

    @Override
    public TempCode findByCode(String code) {
        Criteria criteria = getSession().createCriteria(TempCode.class);
        criteria.add(Restrictions.eq("code", code));
        return (TempCode) criteria.uniqueResult();
    }
}
