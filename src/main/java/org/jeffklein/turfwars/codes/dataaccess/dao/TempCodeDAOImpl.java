package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;

import java.util.List;

/**
 * Implementation of DAO methods required by TempCodeDAO.
 */
public class TempCodeDAOImpl extends AbstractHibernateDAO implements TempCodeDAO {
    @Override
    public void saveTempCode(TempCode tempCode) {
        persist(tempCode);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TempCode> findAll() {
        Criteria criteria = getSession().createCriteria(TempCode.class);
        return (List<TempCode>) criteria.list();
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

    @Override
    public List<TempCode> findByApiResponseId(Integer apiResponseId) {
        Criteria criteria = getSession().createCriteria(TempCode.class);
        criteria.add(Restrictions.eq("api_response_id", apiResponseId));
        return (List<TempCode>) criteria.list();
    }
}
