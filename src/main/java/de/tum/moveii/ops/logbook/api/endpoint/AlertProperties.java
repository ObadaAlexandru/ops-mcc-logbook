package de.tum.moveii.ops.logbook.api.endpoint;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.AlertSeverity;
import de.tum.moveii.ops.logbook.alert.model.AlertState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Data
public class AlertProperties {
    private String subsystemId;
    private AlertSeverity severity;
    private LocalDateTime since;
    private LocalDateTime until;
    private AlertState alertState;
    private Long userId;
    private Long pageSize;
    private Long pageIndex;

    public Predicate buildPredicate() {
        //TODO Implement when the Alert will be defined
        return null;
    }
}