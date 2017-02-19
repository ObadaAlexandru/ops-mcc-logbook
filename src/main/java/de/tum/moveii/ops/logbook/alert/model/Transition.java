package de.tum.moveii.ops.logbook.alert.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "logbook.transitions")
@Data
@Builder
public class Transition {
    @Id
    @SequenceGenerator(name = "logbook.transitions_transitionId_seq",
            sequenceName = "logbook.transitions_transitionId_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transitionId", nullable = false, updatable = false)
    private Long transitionId;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "startState", nullable = false)
    @Convert(converter = AlertStateConverter.class)
    private AlertState startState;

    @Column(name = "endState", nullable = false)
    @Convert(converter = AlertStateConverter.class)
    private AlertState endState;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdOn;

    @ManyToOne
    @JoinColumn(name = "alertId")
    private Alert alert;
}
