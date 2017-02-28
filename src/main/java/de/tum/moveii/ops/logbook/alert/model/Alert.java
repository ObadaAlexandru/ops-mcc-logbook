package de.tum.moveii.ops.logbook.alert.model;

import de.tum.moveii.ops.logbook.log.model.Log;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Entity
@Table(name = "logbook.alerts", schema = "logbook")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Alert {
    @Id
    @SequenceGenerator(name = "alerts_alertId_seq",
            sequenceName = "alerts_alertId_seq",
            schema = "logbook",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "alertId", nullable = false, updatable = false)
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
    private AlertState state;

    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "ownerId")
    private Long ownerId;

    @Column(name = "createdBy")
    private String createdBy;

    @ManyToMany
    @JoinTable(name = "logbook.alert_log",
            joinColumns = @JoinColumn(name = "alertId", referencedColumnName = "alertId"),
            inverseJoinColumns = @JoinColumn(name = "logId", referencedColumnName = "logId"))
    private List<Log> logMessages;

    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY)
    private List<Transition> transitions;

    @OneToMany(mappedBy = "alert", fetch = FetchType.LAZY)
    private List<OwnerHistory> ownerHistory;

    public void addNote(@NonNull Note note) {
        notes.add(note);
    }

    public void addTransition(@NonNull Transition transition) {
        transitions.add(transition);
    }

    public void changeOwner(@NonNull OwnerHistory newOwner) {
        ownerHistory.add(newOwner);
    }
}
