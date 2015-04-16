package org.jeffklein.turfwars.codes.dataaccess.model;

import org.jeffklein.turfwars.codes.client.TempCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Override of the base TempCodeApiResponse class which uses Jackson annotations.
 * This version extends the base version by adding JPA annotations.
 */
@Entity
@Table(name="temp_code_api_response")
public class TempCodeApiResponse {

    public TempCodeApiResponse(org.jeffklein.turfwars.codes.client.TempCodeApiResponse jsonResponse) {
        this.timestamp = jsonResponse.getTimestamp();
        //this.codes = jsonResponse.getTempCodes();
        this.nextUpdate = jsonResponse.getNextUpdate();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payload_ts", nullable = false)
    private Date timestamp;

    @Column(name = "next_update", nullable = false)
    private Date nextUpdate;

    //@OneToMany(mappedBy = "tempCode")
    //private List<TempCode> codes;

    public Date getTimestamp() {
        return this.timestamp;
    }

    public Date getNextUpdate() {
        return this.nextUpdate;
    }

/*    public List<TempCode> getTempCodes() {
        return this.codes;
    }*/

    public Integer getId() {
        return id;
    }
}
