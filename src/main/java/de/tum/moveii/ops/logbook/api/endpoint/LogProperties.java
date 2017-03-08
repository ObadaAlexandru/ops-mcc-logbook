package de.tum.moveii.ops.logbook.api.endpoint;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import de.tum.moveii.ops.logbook.error.InvalidParameterException;
import de.tum.moveii.ops.logbook.log.model.LogSeverity;
import de.tum.moveii.ops.logbook.log.model.QLog;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by csoare on 2/4/17.
 */
@Data
public class LogProperties {
    private String subsystemId;
    private LogSeverity severity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime since = LocalDateTime.now().minus(Duration.ofDays(7));

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime until = LocalDateTime.now();

    private Integer pageSize = 50;
    private Integer pageIndex = 0;

    private void validateDates() {
        Optional<String> errorMessage = Stream.of(since, until)
                .filter(date -> date.isAfter(LocalDateTime.now()))
                .map(date -> String.format("Provided date <%s> is a future date", date))
                .findFirst();
        if (errorMessage.isPresent()) {
            throw new InvalidParameterException(errorMessage.get());
        }
        if (until.isBefore(since)) {
            throw new InvalidParameterException(String
                    .format("Parameter since[%s] cannot be ahead of until[%s]", since, until));
        }
    }

    public Predicate buildPredicate() {
        validateDates();
        QLog logQuery = QLog.log;
        BooleanExpression booleanExpression = logQuery.createdOn.gt(since).and(logQuery.createdOn.lt(until));
        booleanExpression = severity == null ? booleanExpression : booleanExpression.and(logQuery.severity.eq(severity));
        return subsystemId == null ? booleanExpression : booleanExpression.and(logQuery.subsystem.eq(subsystemId));
    }
}
