package org.jeffklein.turfwars.codes.dataaccess.model;

import javax.persistence.*;

/**
 * Entity class that creates a simple sequence generator.
 *
 * @author jeffklein
 */
@Entity
@Table(name = "sequence")
public class Sequence {

    public static final String TW_TEMP_CODE_BATCH_ID = "tw.temp.code.batch.id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String sequenceName;

    @Column(name = "next_id", nullable = false, insertable = false, updatable = true)
    private Integer nextId = 1;

    public Sequence() {
    }

    public Sequence(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Integer getId() {
        return id;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public Integer getNextId() {
        return this.nextId;
    }

    public void incrementNextId() {
        this.nextId++;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Sequence)) {
            return false;
        }
        Sequence that = (Sequence) other;
        if (!this.sequenceName.equals(that.getSequenceName())) {
            return false;
        }
        if (this.id != that.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + sequenceName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "id=" + id +
                ", sequenceName='" + sequenceName + '\'' +
                ", nextId=" + nextId +
                '}';
    }
}
