package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.TempCode;
import org.jeffklein.turfwars.codes.dataaccess.model.User;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for the User entity.
 */
@Repository("userDAO")
public class UserDAOImpl extends AbstractHibernateDAO implements UserDAO {
    @Override
    public Integer createUser(User user) {
        return (Integer) super.create(user);
    }

    @Override
    public void deleteUser(User user) {
        super.delete(user);
    }

    @Override
    public void updateUser(User user) {
        super.update(user);
    }

    @Override
    public User findByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return (User) criteria.uniqueResult();
    }

    @Override
    public User findById(Integer id) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }
}
