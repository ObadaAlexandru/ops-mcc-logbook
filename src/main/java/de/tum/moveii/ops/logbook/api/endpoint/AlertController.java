package de.tum.moveii.ops.logbook.api.endpoint;

import de.tum.moveii.ops.logbook.alert.model.Alert;
import de.tum.moveii.ops.logbook.alert.service.AlertService;
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import de.tum.moveii.ops.logbook.api.message.ErrorMessage;
import de.tum.moveii.ops.logbook.error.AlertNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Alexandru Obada on 27/01/17.
 */
@RestController
@RequestMapping("/logbook/alerts")
@Slf4j
public class AlertController {
    private AlertService alertService;
    private AlertMapper alertMapper;

    @Autowired
    public AlertController(AlertService alertService, AlertMapper alertMapper) {
        this.alertService = alertService;
        this.alertMapper = alertMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public AlertMessage createAlert(@Valid @RequestBody AlertMessage alert) {
        Alert alarm = alertService.create(alertMapper.toResource(alert));
        return alertMapper.toMessage(alarm);
    }

    @GetMapping(path = "/{alertId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public AlertMessage getAlert(@PathVariable Long alertId) {
        return alertService.getAlert(alertId)
                .map(alertMapper::toMessage)
                .orElseThrow(() -> new AlertNotFoundException(String.format("Alert <%s> not found", alertId)));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<AlertMessage> getAlerts(AlertProperties alertProperties) {
        List<Alert> alerts = alertService.getAlerts(alertProperties.buildPredicate());
        return alerts.stream()
                .map(alertMapper::toMessage)
                .collect(Collectors.toList());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(AlertNotFoundException.class)
    public ErrorMessage handleNotFoundException(AlertNotFoundException alertNotFoundException) {
        log.info(alertNotFoundException.toString());
        return new ErrorMessage(alertNotFoundException.toString());
    }
}