package org.jeffklein.turfwars.codes.dataaccess.dao;

import org.jeffklein.turfwars.codes.dataaccess.model.Role;

import java.util.List;

/**
 * Created by jklein on 5/9/15.
 */
public interface RoleDAO {
    public Integer createRole(String rolename);
    public void updateRole(Role role);
    public void deleteRole(Role role);
    public Role findRoleByName(String rolename);
    public List<Role> findAllRoles();
    public Role findOrCreateRole(String roleName);
}
