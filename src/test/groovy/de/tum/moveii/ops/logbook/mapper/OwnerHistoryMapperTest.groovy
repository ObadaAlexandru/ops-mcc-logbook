import de.tum.moveii.ops.logbook.alert.model.OwnerHistory
import de.tum.moveii.ops.logbook.api.mapper.OwnerHistoryMapper
import de.tum.moveii.ops.logbook.api.message.OwnerHistoryMessage
import spock.lang.Specification

import java.sql.Timestamp

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class OwnerHistoryMapperTest extends Specification {

    def ownerHistoryMapper = new OwnerHistoryMapper()

    def 'Map ownerHistory to ownerHistoryMessage'() {
        given:
        def ownerHistoryId = 1L
        def ownerHistory = new OwnerHistory(ownerHistoryId, 2L, 3L, 2L, Timestamp.valueOf("2016-02-19 10:35:30"), null)

        def ownerHistoryMessage = new OwnerHistoryMessage(ownerHistoryId, 2L, 3L, 2L,
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime())
        when:
        def actualOwnerHistoryMessage = ownerHistoryMapper.toMessage(ownerHistory)
        then:
        actualOwnerHistoryMessage == ownerHistoryMessage
    }

    def 'Map ownerHistoryMessage to ownerHistory'() {
        given:
        def ownerHistoryId = 1L
        def ownerHistory = new OwnerHistory(ownerHistoryId, 2L, 3L, 2L, Timestamp.valueOf("2016-02-19 10:35:30"), null)

        def ownerHistoryMessage = new OwnerHistoryMessage(ownerHistoryId, 2L, 3L, 2L,
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime())
        when:
        def actualOwnerHistory = ownerHistoryMapper.toResource(ownerHistoryMessage)
        then:
        actualOwnerHistory == ownerHistory
    }

    def 'Null ownerHistory'() {
        when:
        ownerHistoryMapper.toMessage(null)
        then:
        thrown(NullPointerException)
    }

    def 'Null ownerHistoryMessage'() {
        when:
        ownerHistoryMapper.toResource(null)
        then:
        thrown(NullPointerException)
    }
}
