package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jklein on 5/9/15.
 */
@Entity
@Table(name = "user_role")
@AssociationOverrides(
    {
        @AssociationOverride(name = "pk.user",
            joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.role",
            joinColumns = @JoinColumn(name = "role_id"))
    }
)
public class UserRole implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;

    @EmbeddedId
    private UserRoleId pk = new UserRoleId();

    public UserRole(User user, Role role) {
        setUser(user);
        setRole(role);
    }

    public UserRole() {
    }

//    public Integer getId() {
//        return id;
//    }


    public UserRoleId getPk() {
        return this.pk;
    }

    public void setPk(UserRoleId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Role getRole() {
        return getPk().getRole();
    }

    public void setRole(Role role) {
        getPk().setRole(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserRole that = (UserRole) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }

    @Embeddable
    static class UserRoleId implements Serializable {

        @ManyToOne
        private User user;

        @ManyToOne
        private Role role;

        public UserRoleId() {
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserRoleId)) return false;

            UserRoleId that = (UserRoleId) o;

            if (!role.equals(that.role)) return false;
            if (!user.equals(that.user)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = user.hashCode();
            result = 31 * result + role.hashCode();
            return result;
        }
    }
}
