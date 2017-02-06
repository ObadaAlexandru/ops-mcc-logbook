package de.tum.moveii.ops.logbook.log.service;

import com.mysema.query.types.Predicate;
import de.tum.moveii.ops.logbook.log.model.Log;
import org.springframework.stereotype.Component;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

/**
 * Created by csoare on 2/3/17.
 */
@Component
public class LogServiceImpl implements LogService {
    @Override
    public Log create(@NonNull Log log) {
        return null;
    }

    @Override
    public Optional<Log> getLog(Long logId) {
        return Optional.empty();
    }

    @Override
    public List<Log> getLogs(Predicate qLog) {
        return Collections.emptyList();
    }
}
