package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * JPA Entity class for TempCode's
 */
@Entity
@Table(name = "temp_code")
public class TempCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "api_response_id", updatable = false, nullable = false)
    private TempCodeApiResponse tempCodeApiResponse;

    @Column(name = "expires", nullable = false)
    private Date expires;

    @Column(name = "code", nullable = false)
    private String code;

    @ManyToMany(
            targetEntity = User.class,
            mappedBy = "tempCodesAlreadyPunched",
            cascade = {CascadeType.ALL}
    )
    private Collection<User> users;

    public Integer getId() {
        return id;
    }

    public TempCodeApiResponse getTempCodeApiResponse() {
        return tempCodeApiResponse;
    }

    public void setTempCodeApiResponse(TempCodeApiResponse apiResponse) {
        this.tempCodeApiResponse = apiResponse;
    }

    public Integer getApiResponseId() {
        return this.tempCodeApiResponse.getId();
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
