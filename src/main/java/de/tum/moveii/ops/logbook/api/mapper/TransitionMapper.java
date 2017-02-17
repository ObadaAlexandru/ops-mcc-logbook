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
        return Transition.builder()
                .transitionId(message.getTransitionId())
                .startState(message.getStartState())
                .endState(message.getEndState())
                .ownerId(message.getOwner())
                .createdOn(Timestamp.valueOf(message.getCreatedOn()))
                .build();
    }

    @Override
    public TransitionMessage toMessage(Transition resource) {
        return TransitionMessage.builder()
                .transitionId(resource.getTransitionId())
                .startState(resource.getStartState())
                .endState(resource.getEndState())
                .owner(resource.getOwnerId())
                .createdOn(resource.getCreatedOn().toLocalDateTime())
                .build();
    }
}
