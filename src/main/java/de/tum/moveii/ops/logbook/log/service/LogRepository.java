package de.tum.moveii.ops.logbook.log.service;

import de.tum.moveii.ops.logbook.log.model.Log;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alexandru Obada on 20/02/17.
 */
interface LogRepository extends CrudRepository<Log, Long>, QueryDslPredicateExecutor<Log> {
}
