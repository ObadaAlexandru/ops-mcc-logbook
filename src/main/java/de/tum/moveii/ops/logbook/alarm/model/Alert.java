package de.tum.moveii.ops.logbook.alarm.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Entity
@Data
public class Alert {
    @Id
    private Long alertId;
}
