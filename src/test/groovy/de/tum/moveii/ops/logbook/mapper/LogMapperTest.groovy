import de.tum.moveii.ops.logbook.api.mapper.LogMapper
import de.tum.moveii.ops.logbook.api.message.LogMessage
import de.tum.moveii.ops.logbook.log.model.Log
import de.tum.moveii.ops.logbook.log.model.LogSeverity
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class LogMapperTest extends Specification {

    def logMapper = new LogMapper()

    def 'Map log to logMessage'() {
        given:
        def logId = 1L
        def log = Log.builder()
                .logId(logId)
                .severity(LogSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .downloadedOn(LocalDateTime.of(2016, 2, 19, 10, 45, 30))
                .build()

        def logMessage = LogMessage.builder()
                .logId(logId)
                .severity(LogSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .downloadedOn(LocalDateTime.of(2016, 2, 19, 10, 45, 30))
                .build()
        when:
        def actualLogMessage = logMapper.toMessage(log)
        then:
        actualLogMessage == logMessage

    }

    def 'Map logMessage to log'() {
        given:
        def logId = 1L
        def log = Log.builder()
                .logId(logId)
                .severity(LogSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .downloadedOn(LocalDateTime.of(2016, 2, 19, 10, 45, 30))
                .build()

        def logMessage = LogMessage.builder()
                .logId(logId)
                .severity(LogSeverity.CRITICAL)
                .subsystem("COM")
                .message("test_msg")
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .downloadedOn(LocalDateTime.of(2016, 2, 19, 10, 45, 30))
                .build()
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
