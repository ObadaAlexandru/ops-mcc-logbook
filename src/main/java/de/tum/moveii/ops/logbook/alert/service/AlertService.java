package de.tum.moveii.ops.logbook.alert.service;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.Alert;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
public interface AlertService {
    Alert create(Alert alarm);
    Optional<Alert> getAlert(Long alertId);
    List<Alert> getAlerts(Predicate qAlert);
}