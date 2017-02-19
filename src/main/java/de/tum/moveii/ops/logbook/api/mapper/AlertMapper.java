package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.Alert;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Component
public class AlertMapper implements ResourceMapper<AlertMessage, Alert> {
    private NoteMapper noteMapper = new NoteMapper();
    private TransitionMapper transitionMapper = new TransitionMapper();
    private OwnerHistoryMapper ownerHistoryMapper = new OwnerHistoryMapper();

    //TODO map Transition, Notes and OwnerHistory
    @Override
    public Alert toResource(AlertMessage message) {
        return Alert.builder()
                .alertId(message.getAlertId())
                .severity(message.getSeverity())
                .subsystem(message.getSubsystem())
                .message(message.getMessage())
                .state(message.getState())
                .createdOn(message.getCreatedOn())
                .ownerId(message.getOwner())
                .createdBy(message.getCreatedBy())
                .notes(message.getNotes().stream()
                        .map(noteMapper::toResource).collect(Collectors.toList()))
                .transitions(message.getTransitions().stream()
                        .map(transitionMapper::toResource).collect(Collectors.toList()))
                .ownerHistory(message.getOwnerHistory().stream()
                        .map(ownerHistoryMapper::toResource).collect(Collectors.toList()))
                .build();
    }

    @Override
    public AlertMessage toMessage(Alert resource) {
        return AlertMessage.builder()
                .alertId(resource.getAlertId())
                .severity(resource.getSeverity())
                .subsystem(resource.getSubsystem())
                .message(resource.getMessage())
                .state(resource.getState())
                .createdOn(resource.getCreatedOn())
                .owner(resource.getOwnerId())
                .createdBy(resource.getCreatedBy())
                .notes(resource.getNotes().stream()
                        .map(noteMapper::toMessage).collect(Collectors.toList()))
                .transitions(resource.getTransitions().stream()
                        .map(transitionMapper::toMessage).collect(Collectors.toList()))
                .ownerHistory(resource.getOwnerHistory().stream()
                        .map(ownerHistoryMapper::toMessage).collect(Collectors.toList()))
                .build();
    }
}
