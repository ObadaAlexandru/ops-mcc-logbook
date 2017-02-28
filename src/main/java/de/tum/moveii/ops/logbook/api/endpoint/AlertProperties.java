package de.tum.moveii.ops.logbook.api.endpoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import de.tum.moveii.ops.logbook.alert.model.AlertSeverity;
import de.tum.moveii.ops.logbook.alert.model.AlertState;
import de.tum.moveii.ops.logbook.alert.model.QAlert;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;

import static de.tum.moveii.ops.logbook.alert.model.AlertSeverity.CRITICAL;
import static de.tum.moveii.ops.logbook.alert.model.AlertState.NEW;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Data
public class AlertProperties {
    private String subsystemId;
    private AlertSeverity severity = CRITICAL;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime since = LocalDateTime.now().minus(Duration.ofDays(7));
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime until = LocalDateTime.now();

    private AlertState alertState = NEW;
    private Integer pageSize = 50;
    private Integer pageIndex = 0;

    public Predicate buildPredicate() {
        QAlert alertQuery = QAlert.alert;
        BooleanExpression booleanExpression = alertQuery.severity.eq(severity)
                .and(alertQuery.createdOn.gt(since).and(alertQuery.createdOn.lt(until)))
                .and(alertQuery.state.eq(alertState));
        return subsystemId == null ? booleanExpression : booleanExpression.and(alertQuery.subsystem.eq(subsystemId));
    }
}