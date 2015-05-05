package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.DateTest;
import org.springframework.stereotype.Repository;

/**
 * Created by jklein on 5/4/15.
 */
@Repository("dateTestDAO")
public class DateTestDAOImpl extends AbstractHibernateDAO implements DateTestDAO {
    @Override
    public Integer insertDateTest(DateTest dateTest) {
        return (Integer) super.create(dateTest);
    }

    @Override
    public DateTest findById(Integer id) {
        Criteria criteria = getSession().createCriteria(DateTest.class);
        criteria.add(Restrictions.eq("id", id));
        return (DateTest) criteria.uniqueResult();
    }
}
