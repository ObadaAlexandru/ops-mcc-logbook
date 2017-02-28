package de.tum.moveii.ops.logbook.alert.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "transitions", schema = "logbook")
@Data
@Builder
public class Transition {
    @Id
    @SequenceGenerator(name = "transitions_transitionId_seq",
            sequenceName = "transitions_transitionId_seq",
            schema = "logbook",
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
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "alertId")
    private Alert alert;
}
