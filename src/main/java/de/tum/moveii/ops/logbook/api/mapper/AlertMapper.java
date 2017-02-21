package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alert.model.Alert;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Optional;
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
                .logMessages(Optional.ofNullable(message.getLogMessages())
                        .map(logs -> logs.stream().map(logMapper::toResource).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .notes(Optional.ofNullable(message.getNotes())
                        .map(logs -> logs.stream().map(noteMapper::toResource).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .transitions(Optional.ofNullable(message.getTransitions())
                        .map(logs -> logs.stream().map(transitionMapper::toResource).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .ownerHistory(Optional.ofNullable(message.getOwnerHistory())
                        .map(logs -> logs.stream().map(ownerHistoryMapper::toResource).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
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
                .logMessages(Optional.ofNullable(resource.getLogMessages())
                        .map(logs -> logs.stream().map(logMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .notes(Optional.ofNullable(resource.getNotes())
                        .map(logs -> logs.stream().map(noteMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .transitions(Optional.ofNullable(resource.getTransitions())
                        .map(logs -> logs.stream().map(transitionMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .ownerHistory(Optional.ofNullable(resource.getOwnerHistory())
                        .map(logs -> logs.stream().map(ownerHistoryMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .build();
    }
}