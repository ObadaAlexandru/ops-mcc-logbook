package de.tum.moveii.ops.logbook.alarm.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Costi on 07-Feb-17.
 */
@Entity(name = "notes")
@Data
public class Notes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long noteId;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "alertId", nullable = false)
    private Long alertId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne(optional=false)
    @JoinColumn(name="alertId",referencedColumnName="id")
    private Alert alert;
}
