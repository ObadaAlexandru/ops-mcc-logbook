package de.tum.moveii.ops.logbook.alarm.model;

import lombok.Data;
import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Entity
@Table(name = "logbook.alerts")
@Data
public class Alert {
    @Id
    @Column(name = "alertId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long alertId;

    @Column(name = "severity", nullable = false)
    private String severity;

    @Column(name = "subsystem", nullable = false)
    private String subsystem;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "createdOn", nullable = false)
    private Date createdOn;

    @Column(name = "ownerId")
    private Long ownerId;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "logmessageIds")
    private Long[] logmessageIds;

    /* I used 'LAZY' fetch because we do not need the notes, transition or ownerHistory when we load alerts.
        We only need them when we want alertDetails, so using 'LAZY' is a better option  */
    @OneToMany(mappedBy = "alert", targetEntity = Note.class,
            fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "alert", targetEntity = Transition.class,
            fetch = FetchType.LAZY)
    private List<Transition> transitions;

    @OneToMany(mappedBy = "alert", targetEntity = AlertOwnerHistory.class,
            fetch = FetchType.LAZY)
    private List<AlertOwnerHistory> ownerHistory;

    public void addNote(Note note) {
        this.notes.add(note);

        if (note.getAlert() != this) {
            note.setAlert(this);
        }
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);

        if (transition.getAlert() != this) {
            transition.setAlert(this);
        }
    }

    public void changeOwner(AlertOwnerHistory newOwner) {
        this.ownerHistory.add(newOwner);

        if (newOwner.getAlert() != this) {
            newOwner.setAlert(this);
        }
    }
}
