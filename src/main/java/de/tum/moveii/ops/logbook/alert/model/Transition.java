package de.tum.moveii.ops.logbook.alert.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "TRANSITIONS", schema = "LOGBOOK")
@Data
@EqualsAndHashCode(of = "transitionId")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transition {
    @Id
    @SequenceGenerator(name = "transitions_transition_id_seq",
            sequenceName = "transitions_transition_id_seq",
            schema = "LOGBOOK",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TRANSITION_ID", nullable = false, updatable = false)
    private Long transitionId;

    @Column(name = "EXECUTED_BY", nullable = false)
    private Long executedBy;

    @Column(name = "START_STATE", nullable = false)
    @Convert(converter = AlertStateConverter.class)
    private AlertState startState;

    @Column(name = "END_STATE", nullable = false)
    @Convert(converter = AlertStateConverter.class)
    private AlertState endState;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "ALERT_ID")
    private Alert alert;
}
