import de.tum.moveii.ops.logbook.alert.model.AssigneeHistory
import de.tum.moveii.ops.logbook.api.mapper.AssigneeHistoryMapper
import de.tum.moveii.ops.logbook.api.message.AssigneeHistoryMessage
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class AssigneeHistoryMapperTest extends Specification {

    def assigneeHistoryMapper = new AssigneeHistoryMapper()

    def 'Map assigneeHistory to assigneeHistoryMessage'() {
        given:
        def assigneeHistory = AssigneeHistory.builder()
                .oldAssigneeId(2L)
                .newAssigneeId(3L)
                .executedBy(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def assigneeHistoryMessage = AssigneeHistoryMessage.builder()
                .oldAssignee(2L)
                .newAssignee(3L)
                .executedBy(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
        when:
        def actualAssigneeHistoryMessage = assigneeHistoryMapper.toMessage(assigneeHistory)
        then:
        actualAssigneeHistoryMessage == assigneeHistoryMessage
    }

    def 'Map assigneeHistoryMessage to assigneeHistory'() {
        given:
        def assigneeHistory = AssigneeHistory.builder()
                .oldAssigneeId(2L)
                .newAssigneeId(3L)
                .executedBy(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def assigneeHistoryMessage = AssigneeHistoryMessage.builder()
                .oldAssignee(2L)
                .newAssignee(3L)
                .executedBy(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
        when:
        def actualAssigneeHistory = assigneeHistoryMapper.toResource(assigneeHistoryMessage)
        then:
        actualAssigneeHistory == assigneeHistory
    }

    def 'Null assigneeHistory'() {
        when:
        assigneeHistoryMapper.toMessage(null)
        then:
        thrown(NullPointerException)
    }

    def 'Null assigneeHistoryMessage'() {
        when:
        assigneeHistoryMapper.toResource(null)
        then:
        thrown(NullPointerException)
    }
}
