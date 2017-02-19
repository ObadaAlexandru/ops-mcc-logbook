import de.tum.moveii.ops.logbook.alert.model.*
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper
import de.tum.moveii.ops.logbook.api.message.*
import de.tum.moveii.ops.logbook.log.model.Log
import spock.lang.Specification

import java.sql.Timestamp

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class AlertMapperTest extends Specification {

    def alertMapper = new AlertMapper()

    def 'Map alert to alertMessage'() {
        given:
        def alertId = 1L
        def alert = new Alert(alertId, AlertSeverity.CRITICAL, "COM", "test_msg", AlertState.NEW,
                Timestamp.valueOf("2016-02-19 10:35:30"),
                2L,
                null,
                new ArrayList<Log>(),
                new ArrayList<Note>(),
                new ArrayList<Transition>(),
                new ArrayList<OwnerHistory>())

        def alertMessage = new AlertMessage(alertId, AlertSeverity.CRITICAL, "COM", "test_msg", AlertState.NEW,
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime(),
                2L,
                null,
                new ArrayList<LogMessage>(),
                new ArrayList<NoteMessage>(),
                new ArrayList<TransitionMessage>(),
                new ArrayList<OwnerHistoryMessage>())
        when:
        def actualAlertMessage = alertMapper.toMessage(alert)
        then:
        actualAlertMessage == alertMessage
    }

    def 'Map null lists to alert'() {
        given:
        def alertId = 1L
        def alert = new Alert(alertId, AlertSeverity.CRITICAL, "COM", "test_msg", AlertState.NEW,
                Timestamp.valueOf("2016-02-19 10:35:30"),
                null,
                "COM",
                null,
                null,
                null,
                null)

        def alertMessage = new AlertMessage(alertId, AlertSeverity.CRITICAL, "COM", "test_msg", AlertState.NEW,
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime(),
                null,
                "COM",
                null,
                null,
                null,
                null)
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
