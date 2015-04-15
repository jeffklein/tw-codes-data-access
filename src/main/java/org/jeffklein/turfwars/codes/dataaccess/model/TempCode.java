package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Override of the base TempCode class which uses Jackson annotations.
 * This version extends the base version by adding JPA annotations.
 */
@Entity(name = "tempCode")
@Table(name="temp_code")
public class TempCode extends org.jeffklein.turfwars.codes.client.TempCode {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "expires", nullable = false)
    private Date expires;

    @Column(name = "code", nullable = false)
    private String code;

    public TempCode(Date expires, String code) {
        super(expires, code);
    }

    @Override
    public Date getExpires() {
        return super.getExpires();
    }

    @Override
    public String getCode() {
        return super.getCode();
    }
}
