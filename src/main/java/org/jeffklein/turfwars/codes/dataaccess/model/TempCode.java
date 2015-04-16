package org.jeffklein.turfwars.codes.dataaccess.model;

import org.jeffklein.turfwars.codes.client.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Override of the base TempCode class which uses Jackson annotations.
 * This version extends the base version by adding JPA annotations.
 */
@Entity
@Table(name="temp_code")
public class TempCode {

  public TempCode(org.jeffklein.turfwars.codes.client.TempCode tempCode, org.jeffklein.turfwars.codes.dataaccess.model.TempCodeApiResponse apiResponse) {
    this.code = tempCode.getCode();
    this.expires = tempCode.getExpires();
    this.apiResponse = apiResponse;
  }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

  @ManyToOne
  @JoinColumn(name="api_response_id", insertable=false, updatable=false)
    private TempCodeApiResponse apiResponse;

    @Column(name = "expires", nullable = false)
    private Date expires;

    @Column(name = "code", nullable = false)
    private String code;

//    @Column(name = "api_response_id", nullable = false)
//    private Integer apiResponseId;

    public Date getExpires() {
        return this.expires;
    }

    public String getCode() {
        return this.code;
    }
}
