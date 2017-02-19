package de.tum.moveii.ops.logbook.alert.service;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.Alert;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Component
public class AlertServiceImpl implements AlertService {

    //TODO requires defined alert entity
    @Override
    public Alert create(@NonNull Alert alarm) {
        return null;
    }

    @Override
    public Optional<Alert> getAlert(@NonNull Long alertId) {
        return Optional.empty();
    }

    @Override
    public List<Alert> getAlerts(Predicate predicate) {
        return Collections.emptyList();
    }
}
