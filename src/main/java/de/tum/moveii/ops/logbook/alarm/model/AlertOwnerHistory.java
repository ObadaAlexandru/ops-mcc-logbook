package de.tum.moveii.ops.logbook.alarm.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "logbook.alertOwnerHistory")
@Data
public class AlertOwnerHistory {
    @Id
    @Column(name = "alertHistoryId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long alertHistoryId;

    @Column(name = "oldOwnerId", nullable = false)
    private Long oldOwnerId;

    @Column(name = "newOwnerId", nullable = false)
    private Long newOwnerId;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "alertId")
    private Alert alert;
}
