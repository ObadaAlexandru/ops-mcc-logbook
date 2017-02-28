package de.tum.moveii.ops.logbook.api.endpoint;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import de.tum.moveii.ops.logbook.log.model.LogSeverity;
import de.tum.moveii.ops.logbook.log.model.QLog;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;

import static de.tum.moveii.ops.logbook.log.model.LogSeverity.CRITICAL;

/**
 * Created by csoare on 2/4/17.
 */
@Data
public class LogProperties {
    private String subsystemId;
    private LogSeverity severity = CRITICAL;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime since = LocalDateTime.now().minus(Duration.ofDays(7));
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime until = LocalDateTime.now();
    private Integer pageSize = 50;
    private Integer pageIndex = 0;

    public Predicate buildPredicate() {
        QLog logQuery = QLog.log;
        BooleanExpression booleanExpression = logQuery.severity.eq(severity)
                .and(logQuery.createdOn.gt(since).and(logQuery.createdOn.lt(until)));
        return subsystemId == null ? booleanExpression : booleanExpression.and(logQuery.subsystem.eq(subsystemId));
    }
}
