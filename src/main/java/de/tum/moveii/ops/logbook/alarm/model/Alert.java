package de.tum.moveii.ops.logbook.alarm.model;

import de.tum.moveii.ops.logbook.log.model.Log;
import lombok.Data;
import lombok.NonNull;

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
    @Convert(converter = AlertSeverityConverter.class)
    private AlertSeverity severity;

    @Column(name = "subsystem", nullable = false)
    private String subsystem;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "state", nullable = false)
    @Convert(converter = AlertStateConverter.class)
    private String state;

    @Column(name = "createdOn", nullable = false)
    private Date createdOn;

    @Column(name = "ownerId")
    private Long ownerId;

    @Column(name = "createdBy")
    private String createdBy;

    @ManyToMany
    @JoinTable(name = "logbook.alert_log",
            joinColumns = @JoinColumn(name = "alertId", referencedColumnName = "alertId"),
            inverseJoinColumns = @JoinColumn(name = "logId", referencedColumnName = "logId"))
    private List<Log> logMessages;

    /* I used 'LAZY' fetch because we do not need the notes, transition or ownerHistory when we load alerts.
        We only need them when we want alertDetails, so using 'LAZY' is a better option  */
    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY)
    private List<Transition> transitions;

    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY)
    private List<AlertOwnerHistory> ownerHistory;

    public void addNote(@NonNull Note note) {
        notes.add(note);
    }

    public void addTransition(@NonNull Transition transition) {
        transitions.add(transition);
    }

    public void changeOwner(@NonNull AlertOwnerHistory newOwner) {
        ownerHistory.add(newOwner);
    }
}
