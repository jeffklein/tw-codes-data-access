package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Override of the base TempCodeApiResponse class which uses Jackson annotations.
 * This version extends the base version by adding JPA annotations.
 */
@Entity
@Table(name="temp_code_api_response")
public class TempCodeApiResponse {

    public TempCodeApiResponse(org.jeffklein.turfwars.codes.client.TempCodeApiJsonResponse jsonResponse) {
        this.timestamp = jsonResponse.getTimestamp();
        this.nextUpdate = jsonResponse.getNextUpdate();
        this.jsonCodes = jsonResponse.getTempCodes();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "payload_ts", nullable = false)
    private Date timestamp;

    @Column(name = "next_update", nullable = false)
    private Date nextUpdate;

  @Transient
    private List<org.jeffklein.turfwars.codes.client.TempCode> jsonCodes;

    public Date getTimestamp() {
        return this.timestamp;
    }

    public Date getNextUpdate() {
        return this.nextUpdate;
    }

  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  @JoinColumn(name = "api_response_id")
    public List<TempCode> getTempCodes() {
        ArrayList<TempCode> hibernateCodes = new ArrayList<TempCode>();
        hibernateCodes.add(new org.jeffklein.turfwars.codes.dataaccess.model.TempCode(jsonCodes.get(0), this));
        return hibernateCodes;
    }

    public Integer getId() {
        return id;
    }
}
