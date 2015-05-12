package org.jeffklein.turfwars.codes.dataaccess.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JPA Entity class for User accounts.
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "tw_name", nullable = false)
    private String turfwarsName;

    @Column(name = "last_login", nullable = false)
    private DateTime lastLogin;

    @Column(name = "pref_timezone", nullable = false)
    private String timezonePref;

    @Column(name = "pref_hide_used", nullable = false)
    private boolean hideUsedCodesPref;

    @ManyToMany(
        cascade = {CascadeType.ALL},
        targetEntity = TempCode.class,
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "user_temp_code",
        joinColumns = @JoinColumn(name = "temp_code_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<TempCode> tempCodesAlreadyPunched;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "pk.user",
            cascade = { CascadeType.ALL })
    private Set<UserRole> userRoles = new HashSet<UserRole>();

    public Set<TempCode> getTempCodesAlreadyPunched() {
        return tempCodesAlreadyPunched;
    }

    public void addPunchedTempCode(TempCode alreadyPunched) {
        this.tempCodesAlreadyPunched.add(alreadyPunched);
    }

    public void addPunchedTempCodeBatch(List<TempCode> alreadyPunched) {
        this.tempCodesAlreadyPunched.addAll(alreadyPunched);
    }

    public void addUserRole(UserRole userRole) {
        this. userRoles.add(userRole);
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getTurfwarsName() {
        return turfwarsName;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public String getTimezonePref() {
        return timezonePref;
    }


    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setTempCodesAlreadyPunched(Set<TempCode> tempCodesAlreadyPunched) {
        this.tempCodesAlreadyPunched = tempCodesAlreadyPunched;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isHideUsedCodesPref() {
        return hideUsedCodesPref;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        //TODO: encrypt password prior to inserting into db
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTurfwarsName(String turfwarsName) {
        this.turfwarsName = turfwarsName;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setTimezonePref(String timezonePref) {
        this.timezonePref = timezonePref;
    }

    public void setHideUsedCodesPref(boolean hideUsedCodesPref) {
        this.hideUsedCodesPref = hideUsedCodesPref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (hideUsedCodesPref != user.hideUsedCodesPref) return false;
        if (!email.equals(user.email)) return false;
        if (!id.equals(user.id)) return false;
        if (lastLogin != null ? !lastLogin.equals(user.lastLogin) : user.lastLogin != null) return false;
        if (!password.equals(user.password)) return false;
        if (timezonePref != null ? !timezonePref.equals(user.timezonePref) : user.timezonePref != null) return false;
        if (turfwarsName != null ? !turfwarsName.equals(user.turfwarsName) : user.turfwarsName != null) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + email.hashCode();
        result = 31 * result + (turfwarsName != null ? turfwarsName.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (timezonePref != null ? timezonePref.hashCode() : 0);
        result = 31 * result + (hideUsedCodesPref ? 1 : 0);
        return result;
    }
}


