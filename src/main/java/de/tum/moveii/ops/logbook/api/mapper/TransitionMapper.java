package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alarm.model.Transition;
import de.tum.moveii.ops.logbook.api.message.TransitionMessage;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class TransitionMapper implements ResourceMapper<TransitionMessage, Transition> {
    @Override
    public Transition toResource(TransitionMessage message) {
        Transition transition = Transition.builder()
                .startState(message.getStartState())
                .endState(message.getEndState())
                .ownerId(message.getOwner())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .build();

        return transition;
    }

    @Override
    public TransitionMessage toMessage(Transition resource) {
        TransitionMessage transitionMessage = TransitionMessage.builder()
                .startState(resource.getStartState())
                .endState(resource.getEndState())
                .owner(resource.getOwnerId())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .build();

        return transitionMessage;
    }
}
