package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;
import java.util.Date;

/**
 * JPA Entity class for User accounts.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "tw_name", nullable = false)
    private String turfwarsName;

    @Column(name = "last_login", nullable = false)
    private Date lastLogin;

    @Column(name = "pref_timezone", nullable = false)
    private String timezonePref;

    @Column(name = "pref_hide_used", nullable = false)
    private boolean hideUsedCodesPref;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTurfwarsName() {
        return turfwarsName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public String getTimezonePref() {
        return timezonePref;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTurfwarsName(String turfwarsName) {
        this.turfwarsName = turfwarsName;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setTimezonePref(String timezonePref) {
        this.timezonePref = timezonePref;
    }

    public void setHideUsedCodesPref(boolean hideUsedCodesPref) {
        this.hideUsedCodesPref = hideUsedCodesPref;
    }
}


