package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.Alert;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Component
public class AlertMapper implements ResourceMapper<AlertMessage, Alert> {
    private LogMapper logMapper = new LogMapper();
    private NoteMapper noteMapper = new NoteMapper();
    private TransitionMapper transitionMapper = new TransitionMapper();
    private OwnerHistoryMapper ownerHistoryMapper = new OwnerHistoryMapper();

    //TODO map Transition, Notes and OwnerHistory
    @Override
    public Alert toResource(@NotNull AlertMessage message) {
        return Alert.builder()
                .alertId(message.getAlertId())
                .severity(message.getSeverity())
                .subsystem(message.getSubsystem())
                .message(message.getMessage())
                .state(message.getState())
                .createdOn(message.getCreatedOn())
                .ownerId(message.getOwner())
                .createdBy(message.getCreatedBy())
                .logMessages(message.getLogMessages() == null ? null : message.getLogMessages().stream()
                        .map(logMapper::toResource).collect(Collectors.toList()))
                .notes(message.getNotes() == null ? null : message.getNotes().stream()
                        .map(noteMapper::toResource).collect(Collectors.toList()))
                .transitions(message.getTransitions() == null ? null : message.getTransitions().stream()
                        .map(transitionMapper::toResource).collect(Collectors.toList()))
                .ownerHistory(message.getOwnerHistory() == null ? null : message.getOwnerHistory().stream()
                        .map(ownerHistoryMapper::toResource).collect(Collectors.toList()))
                .build();
    }

    @Override
    public AlertMessage toMessage(@NotNull Alert resource) {
        return AlertMessage.builder()
                .alertId(resource.getAlertId())
                .severity(resource.getSeverity())
                .subsystem(resource.getSubsystem())
                .message(resource.getMessage())
                .state(resource.getState())
                .createdOn(resource.getCreatedOn())
                .owner(resource.getOwnerId())
                .createdBy(resource.getCreatedBy())
                .logMessages(resource.getLogMessages() == null ? null : resource.getLogMessages().stream()
                        .map(logMapper::toMessage).collect(Collectors.toList()))
                .notes(resource.getNotes() == null ? null : resource.getNotes().stream()
                        .map(noteMapper::toMessage).collect(Collectors.toList()))
                .transitions(resource.getTransitions() == null ? null : resource.getTransitions().stream()
                        .map(transitionMapper::toMessage).collect(Collectors.toList()))
                .ownerHistory(resource.getOwnerHistory() == null ? null : resource.getOwnerHistory().stream()
                        .map(ownerHistoryMapper::toMessage).collect(Collectors.toList()))
                .build();
    }
}