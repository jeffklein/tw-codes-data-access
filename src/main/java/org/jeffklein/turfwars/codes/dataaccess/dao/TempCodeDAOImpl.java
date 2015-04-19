package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Implementation of DAO methods required by TempCodeDAO.
 */
@Repository("tempCodeDAO")
@Transactional
public class TempCodeDAOImpl extends AbstractHibernateDAO implements TempCodeDAO {
    @Override
    public void saveTempCode(TempCode tempCode, Integer apiResponseId) {
        //tempCode.setApiResponseId(apiResponseId);
        persist(tempCode);
    }

    @Override
    public void saveTempCodes(Set<TempCode> tempCodes, Integer apiResponseId) {
        for (TempCode code : tempCodes) {
            saveTempCode(code, apiResponseId);
        }
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
