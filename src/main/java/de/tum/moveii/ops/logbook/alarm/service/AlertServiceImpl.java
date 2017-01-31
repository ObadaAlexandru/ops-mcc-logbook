package de.tum.moveii.ops.logbook.alarm.service;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.alarm.model.Alert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Component
public class AlertServiceImpl implements AlertService {

    //TODO requires defined alert entity
    @Override
    public Alert create(Alert alarm) {
        return null;
    }

    @Override
    public Optional<Alert> getAlert(Long alertId) {
        return null;
    }

    @Override
    public List<Alert> getAlerts(Predicate qAlert) {
        return null;
    }
}
