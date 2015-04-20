package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO Base class.
 */
@Transactional
public class AbstractHibernateDAO {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void update(Object entity) {
        getSession().update(entity);
    }

    public Object create(Object entity) {
        return getSession().save(entity);
    }

    public void delete(Object entity) {
        getSession().delete(entity);
    }
}
