import de.tum.moveii.ops.logbook.alert.model.*
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper
import de.tum.moveii.ops.logbook.api.message.AlertMessage
import de.tum.moveii.ops.logbook.api.message.LogMessage
import de.tum.moveii.ops.logbook.api.message.NoteMessage
import de.tum.moveii.ops.logbook.api.message.OwnerHistoryMessage
import de.tum.moveii.ops.logbook.api.message.TransitionMessage
import de.tum.moveii.ops.logbook.log.model.Log
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
                .ownerId(2L)
                .createdBy(null)
                .logMessages(new ArrayList<Log>())
                .notes(new ArrayList<Note>())
                .transitions(new ArrayList<Transition>())
                .ownerHistory(new ArrayList<OwnerHistory>())
                .build()


        def alertMessage = AlertMessage.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .owner(2L)
                .createdBy(null)
                .logMessages(new ArrayList<LogMessage>())
                .notes(new ArrayList<NoteMessage>())
                .transitions(new ArrayList<TransitionMessage>())
                .ownerHistory(new ArrayList<OwnerHistoryMessage>())
                .build()

        when:
        def actualAlertMessage = alertMapper.toMessage(alert)
        then:
        actualAlertMessage == alertMessage
    }

    def 'Map null lists to alert'() {
        given:
        def alertId = 1L
        def alert = Alert.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .ownerId(null)
                .createdBy("com_bot")
                .logMessages(null)
                .notes(null)
                .transitions(null)
                .ownerHistory(null)
                .build()

        def alertMessage = AlertMessage.builder()
                .alertId(alertId)
                .severity(AlertSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .state(AlertState.NEW)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .owner(null)
                .createdBy("com_bot")
                .logMessages(null)
                .notes(null)
                .transitions(null)
                .ownerHistory(null)
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
