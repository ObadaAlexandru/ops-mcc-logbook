package de.tum.moveii.ops.logbook.alert.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "ASSIGNEE_HISTORY", schema = "LOGBOOK")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "assigneeHistoryId")
@Builder
public class AssigneeHistory {
    @Id
    @SequenceGenerator(name = "ownerHistory_ownerHistoryId_seq",
            sequenceName = "ownerHistory_ownerHistoryId_seq",
            schema = "LOGBOOK",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ASSIGNEE_HISTORY_ID", nullable = false, updatable = false)
    private Long assigneeHistoryId;

    @Column(name = "OLD_ASSIGNEE_ID", nullable = false)
    private Long oldAssigneeId;

    @Column(name = "NEW_ASSIGNEE_ID", nullable = false)
    private Long newAssigneeId;

    @Column(name = "EXECUTED_BY", nullable = false)
    private Long executedBy;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "ALERT_ID")
    private Alert alert;
}
