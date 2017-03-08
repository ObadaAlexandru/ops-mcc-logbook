package de.tum.moveii.ops.logbook.alert.service;

import com.querydsl.core.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.Alert;
import de.tum.moveii.ops.logbook.alert.model.AlertState;
import de.tum.moveii.ops.logbook.alert.model.Note;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
public interface AlertService {
    Alert create(@NonNull Alert alert);

    void updateState(@NonNull Long alertId, @NonNull AlertState newState);

    void updateAssignee(@NonNull Long alertId, @NonNull Long newAssignee);

    Alert addNote(@NonNull Long alertId, @NonNull Note note);

    Optional<Alert> getAlert(@NonNull Long alertId);

    List<Alert> getAlerts(@NonNull Predicate predicate, @NonNull Pageable pageable);
}