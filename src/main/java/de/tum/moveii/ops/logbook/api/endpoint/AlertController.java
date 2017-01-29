package de.tum.moveii.ops.logbook.api.endpoint;

import de.tum.moveii.ops.logbook.alarm.model.Alert;
import de.tum.moveii.ops.logbook.alarm.service.AlertService;
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import de.tum.moveii.ops.logbook.error.AlertNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Alexandru Obada on 27/01/17.
 */
@Controller
@RequestMapping("/logbook/alarms")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlertController {
    private AlertService alertService;
    private AlertMapper alertMapper;

    public AlertController(AlertService alertService, AlertMapper alertMapper) {
        this.alertService = alertService;
        this.alertMapper = alertMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public AlertMessage createAlert(AlertMessage alarmMessage) {
        Alert alarm = alertService.create(alertMapper.toResource(alarmMessage));
        return alertMapper.toMessage(alarm);
    }

    @GetMapping(path = "/{alertId}", produces = APPLICATION_JSON_VALUE)
    public AlertMessage getAlert(@PathVariable Long alertId) {
        return alertService.getAlert(alertId)
                .map(alertMapper::toMessage)
                .orElseThrow(() -> new AlertNotFoundException(String.format("Alert<%s> not found", alertId)));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AlertMessage> getAlerts(AlertProperties alertProperties) {
        List<Alert> alerts = alertService.getAlerts(alertProperties.buildPredicate());
        return alerts.stream()
                .map(alertMapper::toMessage)
                .collect(Collectors.toList());
    }
}