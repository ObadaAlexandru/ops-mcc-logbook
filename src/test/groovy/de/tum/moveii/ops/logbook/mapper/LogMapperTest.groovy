import de.tum.moveii.ops.logbook.api.mapper.LogMapper
import de.tum.moveii.ops.logbook.api.message.LogMessage
import de.tum.moveii.ops.logbook.log.model.Log
import de.tum.moveii.ops.logbook.log.model.LogSeverity
import spock.lang.Specification

import java.sql.Timestamp

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class LogMapperTest extends Specification {

    def logMapper = new LogMapper()

    def 'Map log to logMessage'() {
        given:
        def logId = 1L
        def log = new Log(logId, LogSeverity.CRITICAL, "COM", "test_msg", Timestamp.valueOf("2016-02-19 10:35:30"),
                Timestamp.valueOf("2016-02-19 10:45:30"))

        def logMessage = new LogMessage(logId, LogSeverity.CRITICAL, "COM", "test_msg",
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime(),
                Timestamp.valueOf("2016-02-19 10:45:30").toLocalDateTime())
        when:
        def actualLogMessage = logMapper.toMessage(log)
        then:
        actualLogMessage == logMessage
    }

    def 'Map logMessage to log'() {
        given:
        def logId = 1L
        def log = new Log(logId, LogSeverity.CRITICAL, "COM", "test_msg", Timestamp.valueOf("2016-02-19 10:35:30"),
                Timestamp.valueOf("2016-02-19 10:45:30"))

        def logMessage = new LogMessage(logId, LogSeverity.CRITICAL, "COM", "test_msg",
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime(),
                Timestamp.valueOf("2016-02-19 10:45:30").toLocalDateTime())
        when:
        def actualLog = logMapper.toResource(logMessage)
        then:
        actualLog == log
    }

    def 'Null log'() {
        when:
        logMapper.toMessage(null)
        then:
        thrown(NullPointerException)
    }

    def 'Null logMessage'() {
        when:
        logMapper.toResource(null)
        then:
        thrown(NullPointerException)
    }
}
