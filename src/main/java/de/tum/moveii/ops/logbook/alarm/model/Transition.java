package de.tum.moveii.ops.logbook.alarm.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Constantin Costescu on 07-Feb-17.
 */
@Entity
@Table(name = "logbook.transitions")
@Data
public class Transition {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transitionId;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "alertId", nullable = false)
    private Long alertId;

    @Column(name = "startState", nullable = false)
    private String startState;

    @Column(name = "endState", nullable = false)
    private String endState;

    @Column(name = "createdOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne(optional = false)
    @JoinColumn(name = "alertId", referencedColumnName = "alertId")
    private Alert alert;

    public void setAlert(Alert alert) {
        this.alert = alert;
        if (!alert.getTransitions().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            alert.getTransitions().add(this);
        }
    }
}
