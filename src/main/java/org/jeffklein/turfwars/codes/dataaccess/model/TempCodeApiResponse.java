package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Override of the base TempCodeApiResponse class which uses Jackson annotations.
 * This version extends the base version by adding JPA annotations.
 */
@Entity
@Table(name = "temp_code_api_response")
public class TempCodeApiResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "payload_ts", nullable = false)
    private Date timestamp;

    @Column(name = "next_update", nullable = false)
    private Date nextUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tempCodeApiResponse", fetch = FetchType.EAGER)
    private Set<TempCode> tempCodes = new HashSet<TempCode>();

    public Set<TempCode> getTempCodes() {
        return tempCodes;
    }

    public void setTempCodes(Set<TempCode> hibernateCodes) {
        this.tempCodes = hibernateCodes;
    }

    public Integer getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getNextUpdate() {
        return nextUpdate;
    }

    public void setNextUpdate(Date nextUpdate) {
        this.nextUpdate = nextUpdate;
    }
}
