package de.tum.moveii.ops.logbook.api.endpoint;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import de.tum.moveii.ops.logbook.alert.model.AlertSeverity;
import de.tum.moveii.ops.logbook.alert.model.AlertState;
import de.tum.moveii.ops.logbook.alert.model.QAlert;
import de.tum.moveii.ops.logbook.error.InvalidParameterException;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Data
public class AlertProperties {
    private String subsystemId;
    private AlertSeverity severity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime since = LocalDateTime.now().minus(Duration.ofDays(7));

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime until = LocalDateTime.now();

    private AlertState state;
    private Long assigneeId;
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
        QAlert alertQuery = QAlert.alert;

        BooleanExpression booleanExpression = alertQuery.createdOn.gt(since).and(alertQuery.createdOn.lt(until));
        booleanExpression = severity == null ? booleanExpression : booleanExpression.and(alertQuery.severity.eq(severity));
        booleanExpression = state == null ? booleanExpression : booleanExpression.and(alertQuery.state.eq(state));
        booleanExpression = assigneeId == null ? booleanExpression : booleanExpression.and(alertQuery.assigneeId.eq(assigneeId));
        return subsystemId == null ? booleanExpression : booleanExpression.and(alertQuery.subsystem.eq(subsystemId));
    }
}