package org.jeffklein.turfwars.codes.dataaccess.model;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Collection;

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

    @Column(name = "server_ts", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
          parameters = {
              @Parameter(name = "databaseZone", value = "UTC"),
              @Parameter(name = "javaZone", value = "UTC")
          })
    private DateTime serverTimestamp;

    @Column(name = "next_update_ts", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {
                    @Parameter(name = "databaseZone", value = "UTC"),
                    @Parameter(name = "javaZone", value = "UTC")
            })
    private DateTime nextUpdateTimestamp;

    @Column(name = "expiration_ts", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {
                    @Parameter(name = "databaseZone", value = "UTC"),
                    @Parameter(name = "javaZone", value = "UTC")
            })
    private DateTime expirationDate;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "batch_id", nullable = false)
    private Integer batchId;

    @ManyToMany(
            targetEntity = User.class,
            mappedBy = "tempCodesAlreadyPunched",
            cascade = {CascadeType.ALL}
    )
    private Collection<User> users;

    public Integer getId() {
        return id;
    }

    public DateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(DateTime expirationDate) {
        this.expirationDate = trimMillisToSecond(expirationDate);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DateTime getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(DateTime serverTimestamp) {
        this.serverTimestamp = trimMillisToSecond(serverTimestamp);
    }

    public DateTime getNextUpdateTimestamp() {
        return nextUpdateTimestamp;
    }

    public void setNextUpdateTimestamp(DateTime nextUpdateTimestamp) {
        this.nextUpdateTimestamp = trimMillisToSecond(nextUpdateTimestamp);
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public static DateTime trimMillisToSecond(DateTime withExtraMillis) {
        return withExtraMillis.millisOfSecond().roundCeilingCopy().minusMillis(withExtraMillis.getMillisOfSecond());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TempCode)) {
            return false;
        }
        TempCode that = (TempCode) other;
        if (this.id != that.getId()) {
            return false;
        }
        if (!(this.code.equals(that.getCode()))) {
            return false;
        }
        if (!(this.expirationDate.equals(that.getExpirationDate()))) {
            return false;
        }
        if (!(this.serverTimestamp.equals(that.getServerTimestamp()))) {
            return false;
        }
        if (!(this.nextUpdateTimestamp.equals(that.getNextUpdateTimestamp()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TempCode{" +
                "id=" + id +
                ", serverTimestamp=" + serverTimestamp +
                ", nextUpdateTimestamp=" + nextUpdateTimestamp +
                ", expirationDate=" + expirationDate +
                ", code='" + code + '\'' +
                '}';
    }
}
