package de.tum.moveii.ops.logbook.alert.service;

import com.querydsl.core.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.Alert;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
public interface AlertService {
    Alert create(@NonNull Alert alarm);
    Optional<Alert> getAlert(@NonNull Long alertId);
    List<Alert> getAlerts(@NonNull Predicate predicate, @NonNull Pageable pageable);
}