package org.jeffklein.turfwars.codes.dataaccess.model;


import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jklein on 5/4/15.
 */
@Entity
@Table(name = "test_datetime")
public class DateTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "the_datetime", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
            parameters = {
                    @Parameter(name = "databaseZone", value = "UTC"),
                    @Parameter(name = "javaZone", value = "UTC")
            }
    )
    private DateTime theDateTime;

    public Integer getId() {
        return id;
    }

    public DateTime getTheDateTime() {
        return theDateTime;
    }

    public void setTheDateTime(DateTime theDateTime) {
        this.theDateTime = theDateTime;
    }

    @Override
    public String toString() {
        return "DateTest{" +
                "id=" + id +
                ", theDateTime=" + theDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateTest)) return false;

        DateTest dateTest = (DateTest) o;

        if (!id.equals(dateTest.id)) return false;
        if (!theDateTime.equals(dateTest.theDateTime)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + theDateTime.hashCode();
        return result;
    }
}
