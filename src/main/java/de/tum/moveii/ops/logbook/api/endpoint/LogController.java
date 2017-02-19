package de.tum.moveii.ops.logbook.api.endpoint;

import de.tum.moveii.ops.logbook.api.mapper.LogMapper;
import de.tum.moveii.ops.logbook.api.message.ErrorMessage;
import de.tum.moveii.ops.logbook.api.message.LogMessage;
import de.tum.moveii.ops.logbook.error.LogNotFoundException;
import de.tum.moveii.ops.logbook.log.model.Log;
import de.tum.moveii.ops.logbook.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Cristian Soare on 2/3/17.
 */
@RestController
@RequestMapping("logbook/logs")
@Slf4j
public class LogController {
    private LogService logService;
    private LogMapper logMapper;

    @Autowired
    public LogController(LogService logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public LogMessage createLog(@Valid @RequestBody LogMessage message) {
        Log log = logService.create(logMapper.toResource(message));
        return logMapper.toMessage(log);
    }

    @GetMapping(path = "/{logId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public LogMessage getLog(@PathVariable Long logId) {
        return logService.getLog(logId)
                .map(logMapper::toMessage)
                .orElseThrow(() -> new LogNotFoundException(String.format("Log <%s> not found", logId)));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<LogMessage> getLogs(LogProperties logProperties) {
        List<Log> logs = logService.getLogs(logProperties.buildPredicate());
        return logs.stream()
                .map(logMapper::toMessage)
                .collect(Collectors.toList());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(LogNotFoundException.class)
    public ErrorMessage handleNotFoundException(LogNotFoundException logNotFoundException) {
        log.info(logNotFoundException.toString());
        return new ErrorMessage(logNotFoundException.toString());
    }
}
