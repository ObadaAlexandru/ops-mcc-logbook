package de.tum.moveii.ops.logbook.api.endpoint;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.log.model.LogSeverity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by csoare on 2/4/17.
 */
@Data
public class LogProperties {
    private String subsystemId;
    private LogSeverity severity;
    private LocalDateTime since;
    private LocalDateTime until;
    private Long userId;
    private Long pageSize;
    private Long pageIndex;

    public Predicate buildPredicate() {
        //TODO Implement when the Alert will be defined
        return null;
    }
}
