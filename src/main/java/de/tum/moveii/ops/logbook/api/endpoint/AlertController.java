package de.tum.moveii.ops.logbook.api.endpoint;

import de.tum.moveii.ops.logbook.alert.model.Alert;
import de.tum.moveii.ops.logbook.alert.service.AlertService;
import de.tum.moveii.ops.logbook.api.mapper.AlertMapper;
import de.tum.moveii.ops.logbook.api.mapper.NoteMapper;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import de.tum.moveii.ops.logbook.api.message.AlertUpdateMessage;
import de.tum.moveii.ops.logbook.api.message.ErrorMessage;
import de.tum.moveii.ops.logbook.api.message.NoteMessage;
import de.tum.moveii.ops.logbook.error.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
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
    private NoteMapper noteMapper;

    @Autowired
    public AlertController(AlertService alertService, AlertMapper alertMapper, NoteMapper noteMapper) {
        this.alertService = alertService;
        this.alertMapper = alertMapper;
        this.noteMapper = noteMapper;
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
        List<Alert> alerts = alertService.getAlerts(alertProperties.buildPredicate(), new PageRequest(alertProperties.getPageIndex(), alertProperties.getPageSize()));
        return alerts.stream()
                .map(alertMapper::toMessage)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/{alertId}/notes", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addNote(@PathVariable Long alertId, @RequestBody NoteMessage noteMessage) {
        alertService.addNote(alertId, noteMapper.toResource(noteMessage));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.format("/logbook/alerts/%d", alertId));
        return new ResponseEntity<>(headers, SEE_OTHER);
    }

    @PatchMapping(path = "/{alertId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateAlert(@PathVariable Long alertId, @RequestBody AlertUpdateMessage alertUpdate) {
        update(alertId, alertUpdate);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.format("/logbook/alerts/%d", alertId));
        return new ResponseEntity<>(headers, SEE_OTHER);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(AlertNotFoundException.class)
    public ErrorMessage handleNotFoundException(AlertNotFoundException alertNotFoundException) {
        log.info(alertNotFoundException.toString());
        return new ErrorMessage(alertNotFoundException.toString());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({InvalidAlertUpdateException.class, UserNotFoundException.class, InvalidParameterException.class})
    public ErrorMessage handleInvalidUpdate(BaseException exception) {
        log.warn(exception.toString());
        return new ErrorMessage(exception.toString());
    }

    private void update(Long alertId, AlertUpdateMessage alertUpdateMessage) {
        if (!alertUpdateMessage.isValid()) {
            throw new InvalidAlertUpdateException();
        }
        if (null != alertUpdateMessage.getNewAssignee()) {
            alertService.updateAssignee(alertId, alertUpdateMessage.getNewAssignee());
        }
        if (null != alertUpdateMessage.getNewState()) {
            alertService.updateState(alertId, alertUpdateMessage.getNewState());
        }
    }
}