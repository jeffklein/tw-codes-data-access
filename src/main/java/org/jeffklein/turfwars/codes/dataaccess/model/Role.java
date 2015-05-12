package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jklein on 5/9/15.
 */
@Entity
@Table(name = "role",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"role_name"}
    )
)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.role")
    private Set<UserRole> userRoles = new HashSet<UserRole>();

    @Column(name = "role_name", nullable = false, length = 60)
    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }


    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role1 = (Role) o;

        if (!id.equals(role1.id)) return false;
        if (!roleName.equals(role1.roleName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + roleName.hashCode();
        return result;
    }
}
