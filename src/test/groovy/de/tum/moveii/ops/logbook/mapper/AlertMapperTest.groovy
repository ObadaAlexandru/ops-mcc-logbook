import de.tum.moveii.ops.logbook.alert.model.Alert
import de.tum.moveii.ops.logbook.alert.model.AlertSeverity
import de.tum.moveii.ops.logbook.alert.model.AlertState
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper
import de.tum.moveii.ops.logbook.api.message.AlertMessage
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class AlertMapperTest extends Specification {

    def alertMapper = new AlertMapper()

    def 'Map alert to alertMessage'() {
        given:
        def alertId = 1L

        def alert = Alert.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .assigneeId(2L)
                .notes(new HashSet<>())
                .transitions(new HashSet<>())
                .assigneeHistory(new HashSet<>())
                .build()


        def alertMessage = AlertMessage.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .assignee(2L)
                .notes([])
                .transitions([])
                .assigneeHistory([])
                .build()

        when:
        def actualAlertMessage = alertMapper.toMessage(alert)
        then:
        actualAlertMessage == alertMessage
    }

    def 'Null logs, notes, transitions, ownerHistory'() {
        given:
        def alertId = 1L

        def alertMessage = AlertMessage.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def alert = Alert.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .notes(new HashSet<>())
                .transitions(new HashSet<>())
                .assigneeHistory(new HashSet<>())
                .build()
        when:
        def actualAlert = alertMapper.toResource(alertMessage)
        then:
        actualAlert == alert
    }

    def 'Null alert'() {
        when:
        alertMapper.toMessage(null)
        then:
        thrown(NullPointerException)
    }

    def 'Null alertMessage'() {
        when:
        alertMapper.toResource(null)
        then:
        thrown(NullPointerException)
    }
}
