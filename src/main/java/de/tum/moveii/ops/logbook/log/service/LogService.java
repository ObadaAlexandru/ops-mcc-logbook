package de.tum.moveii.ops.logbook.log.service;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.log.model.Log;

import java.util.List;
import java.util.Optional;

/**
 * Created by csoare on 2/3/17.
 */
public interface LogService {
    Log create(Log log);
    Optional<Log> getLog(Long logId);
    List<Log> getLogs(Predicate qLog);
}
