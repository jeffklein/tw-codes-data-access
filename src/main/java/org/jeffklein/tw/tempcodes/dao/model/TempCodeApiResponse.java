package org.jeffklein.tw.tempcodes.dao.model;

import org.jeffklein.tw.tempcodes.client.TempCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Override of the base TempCodeApiResponse class which uses Jackson annotations.
 * This version extends the base version by adding JPA annotations.
 */
@Entity(name = "tempCodeApiResponse")
@Table(name="temp_code_api_response")
public class TempCodeApiResponse extends org.jeffklein.tw.tempcodes.client.TempCodeApiResponse {
    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payload_ts", nullable = false)
    private Date timestamp;

    @Column(name = "next_update", nullable = false)
    private Date nextUpdate;

    @OneToMany(mappedBy = "tempCode")
    private List<TempCode> codes;

    public TempCodeApiResponse(Date timestamp, Date nextUpdate, List<org.jeffklein.tw.tempcodes.client.TempCode> codes) {
        super(timestamp, nextUpdate, codes);
    }

    @Override
    public Date getTimestamp() {
        return super.getTimestamp();
    }

    @Override
    public Date getNextUpdate() {
        return super.getNextUpdate();
    }

    @Override
    public List<TempCode> getCodes() {
        return super.getCodes();
    }

    public Integer getId() {
        return id;
    }
}
