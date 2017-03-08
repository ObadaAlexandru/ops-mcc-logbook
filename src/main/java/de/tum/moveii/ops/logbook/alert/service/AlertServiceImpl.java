package de.tum.moveii.ops.logbook.alert.service;

import com.querydsl.core.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.*;
import de.tum.moveii.ops.logbook.error.AlertNotFoundException;
import de.tum.moveii.ops.logbook.error.InvalidAlertUpdateException;
import de.tum.moveii.ops.logbook.error.UserNotFoundException;
import de.tum.moveii.ops.logbook.user.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Slf4j
@Component
public class AlertServiceImpl implements AlertService {

    private AlertRepository alertRepository;
    private UserService userService;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository, UserService userService) {
        this.alertRepository = alertRepository;
        this.userService = userService;
    }

    @Override
    public Alert create(@NonNull Alert alert) {
        alert.setCreatedOn(LocalDateTime.now());
        alert.setState(AlertState.NEW);
        alert.setAssigneeId(userService.getCurrentUser());
        return alertRepository.save(alert);
    }

    @Override
    public void updateState(@NonNull Long alertId, @NonNull AlertState newState) {
        Alert alert = alertRepository.findOne(alertId);
        if (null == alert) {
            throw new AlertNotFoundException(String.format("Alert <%s> not found", alertId));
        }
        if (newState.compareTo(alert.getState()) > 0) {
            Transition transition = Transition.builder()
                    .alert(alert)
                    .createdOn(LocalDateTime.now())
                    .startState(alert.getState())
                    .endState(newState)
                    .executedBy(userService.getCurrentUser())
                    .build();
            alert.addTransition(transition);
            alert.setState(newState);
            alertRepository.save(alert);
        } else {
            throw new InvalidAlertUpdateException(String.format("Invalid state transition from %s to %s", alert.getState(), newState));
        }
    }

    @Override
    public void updateAssignee(@NonNull Long alertId, @NonNull Long newAssignee) {
        Alert alert = alertRepository.findOne(alertId);
        if (null == alert) {
            throw new AlertNotFoundException(String.format("Alert <%s> not found", alertId));
        }
        if (newAssignee.equals(alert.getAssigneeId())) {
            throw new InvalidAlertUpdateException(String.format("Alert already assigned to user<%s>", newAssignee));
        }
        if (userService.exists(newAssignee)) {
            Long oldAssignee = alert.getAssigneeId();
            alert.setAssigneeId(newAssignee);
            AssigneeHistory assigneeHistory = AssigneeHistory.builder()
                    .oldAssigneeId(oldAssignee)
                    .newAssigneeId(newAssignee)
                    .createdOn(LocalDateTime.now())
                    .executedBy(userService.getCurrentUser())
                    .build();
            alert.addAssigneeHistoryEntry(assigneeHistory);
            alertRepository.save(alert);
            log.info("Alert {} executedBy has been changed from {} to {}", alertId, oldAssignee, newAssignee);
        } else {
            throw new UserNotFoundException(String.format("User <%s> doesn't exist", newAssignee));
        }
    }

    @Override
    public Alert addNote(@NonNull Long alertId, @NonNull Note note) {
        Alert alert = alertRepository.findOne(alertId);
        if (null == alert) {
            throw new AlertNotFoundException(String.format("Alert <%s> not found", alertId));
        }
        note.setCreatedOn(LocalDateTime.now());
        note.setOwnerId(userService.getCurrentUser());
        alert.addNote(note);
        return alertRepository.save(alert);
    }

    @Override
    public Optional<Alert> getAlert(@NonNull Long alertId) {
        return Optional.ofNullable(alertRepository.findOne(alertId));
    }

    @Override
    public List<Alert> getAlerts(Predicate predicate, Pageable pageable) {
        return alertRepository.findAll(predicate, pageable).getContent();
    }
}