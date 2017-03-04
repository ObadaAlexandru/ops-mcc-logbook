package de.tum.moveii.ops.logbook.alert.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Entity
@Table(name = "ALERTS", schema = "LOGBOOK")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Alert {
    @Id
    @SequenceGenerator(name = "alerts_alertId_seq",
            sequenceName = "alerts_alertId_seq",
            schema = "LOGBOOK",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ALERT_ID", nullable = false, updatable = false)
    private Long alertId;

    @Column(name = "SEVERITY", nullable = false)
    @Convert(converter = AlertSeverityConverter.class)
    private AlertSeverity severity;

    @Column(name = "SUBSYSTEM", nullable = false)
    private String subsystem;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "STATE", nullable = false)
    @Convert(converter = AlertStateConverter.class)
    private AlertState state;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "ASSIGNEE_ID")
    private Long assigneeId;

    @OneToMany(mappedBy = "alert", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Note> notes;

    @OneToMany(mappedBy = "alert", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transition> transitions;

    @OneToMany(mappedBy = "alert", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AssigneeHistory> assigneeHistory;

    public void addNote(@NonNull Note note) {
        note.setAlert(this);
        notes.add(note);
    }

    public void addTransition(@NonNull Transition transition) {
        transition.setAlert(this);
        transitions.add(transition);
    }

    public void addAssigneeHistoryEntry(@NonNull AssigneeHistory newOwner) {
        newOwner.setAlert(this);
        assigneeHistory.add(newOwner);
    }
}