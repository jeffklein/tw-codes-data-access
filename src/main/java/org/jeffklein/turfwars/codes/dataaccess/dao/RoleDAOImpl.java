package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jeffklein.turfwars.codes.dataaccess.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jklein on 5/9/15.
 */
@Repository("roleDAO")
public class RoleDAOImpl extends AbstractHibernateDAO implements RoleDAO {
    @Override
    public Integer createRole(String rolename) {
        Role role = new Role(rolename);
        return (Integer) super.create(role);
    }

    @Override
    public void updateRole(Role role) {
        super.update(role);
    }

    @Override
    public void deleteRole(Role role) {
        super.delete(role);
    }

    @Override
    public Role findRoleByName(String rolename) {
        Criteria criteria = getSession().createCriteria(Role.class);
        criteria.add(Restrictions.eq("roleName", rolename));
        return (Role) criteria.uniqueResult();
    }

    @Override
    public List<Role> findAllRoles() {
        Criteria criteria = getSession().createCriteria(Role.class);
        return (List<Role>) criteria.list();
    }

    @Override
    public Role findOrCreateRole(String roleName) {
        Role role = findRoleByName(roleName);
        if (role != null) {
            return role;
        }
        else {
            createRole(roleName);
            return findRoleByName(roleName);
        }
    }
}
