package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of DAO methods required by TempCodeApiResponseDAO.
 */
@Repository("tempCodeApiResponseDAO")
public class TempCodeApiResponseDAOImpl extends AbstractHibernateDAO implements TempCodeApiResponseDAO {
    @Override
    public Integer saveTempCodeApiResponse(TempCodeApiResponse response) {
        return (Integer) create(response);
    }

    @Override
    public void deleteTempCodeApiResponse(TempCodeApiResponse response) {
        super.delete(response);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TempCodeApiResponse> findAll() {
        Criteria criteria = getSession().createCriteria(TempCodeApiResponseDAO.class);
        return (List<TempCodeApiResponse>) criteria.list();
    }

    @Override
    public TempCodeApiResponse findById(Integer id) {
        Criteria criteria = getSession().createCriteria(TempCodeApiResponse.class);
        criteria.add(Restrictions.eq("id", id));
        return (TempCodeApiResponse) criteria.uniqueResult();
    }
}
