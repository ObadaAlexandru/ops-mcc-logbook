/**
 * Created by Cristian Soare on 2/4/17.
 */

import com.mysema.query.types.Predicate
import de.tum.moveii.ops.logbook.log.model.Log
import de.tum.moveii.ops.logbook.log.service.LogService
import de.tum.moveii.ops.logbook.api.endpoint.LogController
import de.tum.moveii.ops.logbook.api.endpoint.LogProperties
import de.tum.moveii.ops.logbook.api.mapper.LogMapper
import de.tum.moveii.ops.logbook.api.message.LogMessage
import de.tum.moveii.ops.logbook.error.LogNotFoundException
import spock.lang.Specification

class LogControllerTest extends Specification {

    def logService = Mock(LogService)
    def logMapper = Mock(LogMapper)

    def logController = new LogController(logService, logMapper)

    def 'Create log'() {
        given:
        def logMessage = new LogMessage()
        when:
        def actualLogMessage = logController.createLog(logMessage)
        then:
        1 * logMapper.toResource(logMessage) >> new Log()
        1 * logService.create(_) >> new Log()
        1 * logMapper.toMessage(_) >> logMessage
        actualLogMessage == logMessage
    }

    def 'Get existing log'() {
        given:
        def logId = 1L
        def log = new Log()
        def expectedLogMessage = new LogMessage()
        when:
        def actualLogmessage = logController.getLog(logId)
        then:
        1 * logService.getLog(logId) >> Optional.of(log)
        1 * logMapper.toMessage(log) >> expectedLogMessage
        actualLogmessage == expectedLogMessage
    }

    def 'If log is not found the call should fail'() {
        when:
        logController.getLog(1L)
        then:
        1 * logService.getLog(_) >> Optional.empty()
        thrown(LogNotFoundException)
    }

    def 'Get logs by properties'() {
        given:
        def logProperties = Mock(LogProperties)
        logMapper.toMessage(_) >> new LogMessage()
        logProperties.buildPredicate() >> Mock(Predicate)
        when:
        def actualResult = logController.getLogs(new LogProperties())
        then:
        1 * logService.getLogs(_) >> logs
        actualResult.size() == logs.size()
        where:
        logs                   | _
        [new Log(), new Log()] | _
        []                     | _
    }
}
