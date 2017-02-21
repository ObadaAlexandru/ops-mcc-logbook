package de.tum.moveii.ops.logbook.log.service;

import com.querydsl.core.types.Predicate;
import de.tum.moveii.ops.logbook.log.model.Log;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by csoare on 2/3/17.
 */
public interface LogService {
    Log create(@NonNull Log log);
    Optional<Log> getLog(@NonNull Long logId);
    List<Log> getLogs(@NonNull Predicate predicate, @NonNull Pageable pageable);
}
