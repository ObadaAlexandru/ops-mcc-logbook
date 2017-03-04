package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.Transition;
import de.tum.moveii.ops.logbook.api.message.TransitionMessage;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Created by Constantin Costescu on 12-Feb-17.
 */
@Component
public class TransitionMapper implements ResourceMapper<TransitionMessage, Transition> {
    @Override
    public Transition toResource(@NotNull TransitionMessage message) {
        return Transition.builder()
                .startState(message.getStartState())
                .endState(message.getEndState())
                .executedBy(message.getExecutedBy())
                .createdOn(message.getCreatedOn())
                .build();
    }

    @Override
    public TransitionMessage toMessage(@NotNull Transition resource) {
        return TransitionMessage.builder()
                .startState(resource.getStartState())
                .endState(resource.getEndState())
                .executedBy(resource.getExecutedBy())
                .createdOn(resource.getCreatedOn())
                .build();
    }
}