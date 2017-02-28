package de.tum.moveii.ops.logbook.log.service;

import com.querydsl.core.types.Predicate;
import de.tum.moveii.ops.logbook.log.model.Log;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by csoare on 2/3/17.
 */
@Component
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Log create(@NonNull Log log) {
        return logRepository.save(log);
    }

    @Override
    public Optional<Log> getLog(@NonNull Long logId) {
        return Optional.ofNullable(logRepository.findOne(logId));
    }

    @Override
    public List<Log> getLogs(@NonNull Predicate predicate, @NonNull Pageable pageable) {
        Page<Log> logs = logRepository.findAll(predicate, pageable);
        return logs.getContent();
    }
}
