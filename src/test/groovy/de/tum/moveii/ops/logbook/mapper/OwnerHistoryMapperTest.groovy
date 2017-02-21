import de.tum.moveii.ops.logbook.alert.model.OwnerHistory
import de.tum.moveii.ops.logbook.api.mapper.OwnerHistoryMapper
import de.tum.moveii.ops.logbook.api.message.OwnerHistoryMessage
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class OwnerHistoryMapperTest extends Specification {

    def ownerHistoryMapper = new OwnerHistoryMapper()

    def 'Map ownerHistory to ownerHistoryMessage'() {
        given:
        def ownerHistoryId = 1L
        def ownerHistory = OwnerHistory.builder()
                .ownerHistoryId(ownerHistoryId)
                .oldOwnerId(2L)
                .newOwnerId(3L)
                .ownerId(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def ownerHistoryMessage = OwnerHistoryMessage.builder()
                .ownerHistoryId(ownerHistoryId)
                .oldOwner(2L)
                .newOwner(3L)
                .owner(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
        when:
        def actualOwnerHistoryMessage = ownerHistoryMapper.toMessage(ownerHistory)
        then:
        actualOwnerHistoryMessage == ownerHistoryMessage
    }

    def 'Map ownerHistoryMessage to ownerHistory'() {
        given:
        def ownerHistoryId = 1L
        def ownerHistory = OwnerHistory.builder()
                .ownerHistoryId(ownerHistoryId)
                .oldOwnerId(2L)
                .newOwnerId(3L)
                .ownerId(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def ownerHistoryMessage = OwnerHistoryMessage.builder()
                .ownerHistoryId(ownerHistoryId)
                .oldOwner(2L)
                .newOwner(3L)
                .owner(2L)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
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
