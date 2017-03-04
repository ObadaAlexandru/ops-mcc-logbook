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
    private AssigneeHistoryMapper ownerHistoryMapper = new AssigneeHistoryMapper();

    @Override
    public Alert toResource(@NotNull AlertMessage message) {
        return Alert.builder()
                .alertId(message.getAlertId())
                .severity(message.getSeverity())
                .subsystem(message.getSubsystem())
                .message(message.getMessage())
                .state(message.getState())
                .createdOn(message.getCreatedOn())
                .assigneeId(message.getAssignee())
                .notes(Optional.ofNullable(message.getNotes())
                        .map(notes -> notes.stream().map(noteMapper::toResource).collect(Collectors.toSet()))
                        .orElse(Collections.emptySet()))
                .transitions(Optional.ofNullable(message.getTransitions())
                        .map(transitions -> transitions.stream().map(transitionMapper::toResource).collect(Collectors.toSet()))
                        .orElse(Collections.emptySet()))
                .assigneeHistory(Optional.ofNullable(message.getAssigneeHistory())
                        .map(ownerHistory -> ownerHistory.stream().map(ownerHistoryMapper::toResource).collect(Collectors.toSet()))
                        .orElse(Collections.emptySet()))
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
                .assignee(resource.getAssigneeId())
                .notes(Optional.ofNullable(resource.getNotes())
                        .map(notes -> notes.stream().map(noteMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .transitions(Optional.ofNullable(resource.getTransitions())
                        .map(transitions -> transitions.stream().map(transitionMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .assigneeHistory(Optional.ofNullable(resource.getAssigneeHistory())
                        .map(ownerHistory -> ownerHistory.stream().map(ownerHistoryMapper::toMessage).collect(Collectors.toList()))
                        .orElse(Collections.emptyList()))
                .build();
    }
}