package de.tum.moveii.ops.logbook.alarm.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "logbook.notes")
@Data
public class Note {
    @Id
    @SequenceGenerator(name = "logbook.notes_noteId_seq",
            sequenceName = "logbook.notes_noteId_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "noteId", nullable = false, updatable = false)
    private Long noteId;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "alertId")
    private Alert alert;
}
