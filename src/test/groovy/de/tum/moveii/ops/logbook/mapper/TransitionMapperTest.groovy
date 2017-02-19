import de.tum.moveii.ops.logbook.alert.model.AlertState
import de.tum.moveii.ops.logbook.alert.model.Transition
import de.tum.moveii.ops.logbook.api.mapper.TransitionMapper
import de.tum.moveii.ops.logbook.api.message.TransitionMessage
import spock.lang.Specification

import java.sql.Timestamp

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class TransitionMapperTest extends Specification {

    def transitionMapper = new TransitionMapper()

    def 'Map transition to transitionMessage'() {
        given:
        def transitionId = 1L
        def transition = new Transition(transitionId, 2L, AlertState.NEW, AlertState.ACKNOWLEDGED,
                Timestamp.valueOf("2016-02-19 10:35:30"), null)

        def transitionMessage = new TransitionMessage(transitionId, 2L, AlertState.NEW, AlertState.ACKNOWLEDGED,
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime())
        when:
        def actualTransitionMessage = transitionMapper.toMessage(transition)
        then:
        actualTransitionMessage == transitionMessage
    }

    def 'Map transitionMessage to transition'() {
        given:
        def transitionId = 1L
        def transition = new Transition(transitionId, 2L, AlertState.NEW, AlertState.ACKNOWLEDGED,
                Timestamp.valueOf("2016-02-19 10:35:30"), null)

        def transitionMessage = new TransitionMessage(transitionId, 2L, AlertState.NEW, AlertState.ACKNOWLEDGED,
                Timestamp.valueOf("2016-02-19 10:35:30").toLocalDateTime())
        when:
        def actualTransition = transitionMapper.toResource(transitionMessage)
        then:
        actualTransition == transition
    }

    def 'Null transition'() {
        when:
        transitionMapper.toMessage(null)
        then:
        thrown(NullPointerException)
    }

    def 'Null transitionMessage'() {
        when:
        transitionMapper.toResource(null)
        then:
        thrown(NullPointerException)
    }
}
