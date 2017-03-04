import de.tum.moveii.ops.logbook.alert.model.AlertState
import de.tum.moveii.ops.logbook.alert.model.Transition
import de.tum.moveii.ops.logbook.api.mapper.TransitionMapper
import de.tum.moveii.ops.logbook.api.message.TransitionMessage
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by Constantin Costescu on 19-Feb-17.
 */
class TransitionMapperTest extends Specification {

    def transitionMapper = new TransitionMapper()

    def 'Map transition to transitionMessage'() {
        given:
        def transition = Transition.builder()
                .executedBy(2L)
                .startState(AlertState.NEW)
                .endState(AlertState.ACKNOWLEDGED)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def transitionMessage = TransitionMessage.builder()
                .executedBy(2L)
                .startState(AlertState.NEW)
                .endState(AlertState.ACKNOWLEDGED)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
        when:
        def actualTransitionMessage = transitionMapper.toMessage(transition)
        then:
        actualTransitionMessage == transitionMessage
    }

    def 'Map transitionMessage to transition'() {
        given:
        def transition = Transition.builder()
                .executedBy(2L)
                .startState(AlertState.NEW)
                .endState(AlertState.ACKNOWLEDGED)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()

        def transitionMessage = TransitionMessage.builder()
                .executedBy(2L)
                .startState(AlertState.NEW)
                .endState(AlertState.ACKNOWLEDGED)
                .createdOn(LocalDateTime.of(2016, 2, 19, 10, 35, 30))
                .build()
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
