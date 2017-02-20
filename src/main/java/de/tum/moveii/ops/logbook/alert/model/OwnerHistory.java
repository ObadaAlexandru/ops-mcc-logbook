package de.tum.moveii.ops.logbook.alert.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "logbook.ownerHistory")
@Data
@Builder
public class OwnerHistory {
    @Id
    @SequenceGenerator(name = "logbook.ownerHistory_ownerHistoryId_seq",
            sequenceName = "logbook.ownerHistory_ownerHistoryId_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ownerHistoryId", nullable = false, updatable = false)
    private Long ownerHistoryId;

    @Column(name = "oldOwnerId", nullable = false)
    private Long oldOwnerId;

    @Column(name = "newOwnerId", nullable = false)
    private Long newOwnerId;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "alertId")
    private Alert alert;
}
