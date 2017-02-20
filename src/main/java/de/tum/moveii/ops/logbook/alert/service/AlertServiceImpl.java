package de.tum.moveii.ops.logbook.alert.service;

import com.querydsl.core.types.Predicate;
import de.tum.moveii.ops.logbook.alert.model.Alert;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Component
public class AlertServiceImpl implements AlertService {

    private AlertRepository alertRepository;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Alert create(@NonNull Alert alarm) {
        return alertRepository.save(alarm);
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