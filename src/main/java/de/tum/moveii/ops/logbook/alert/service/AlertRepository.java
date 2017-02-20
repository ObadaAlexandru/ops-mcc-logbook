package de.tum.moveii.ops.logbook.alert.service;

import de.tum.moveii.ops.logbook.alert.model.Alert;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alexandru Obada on 20/02/17.
 */
interface AlertRepository extends CrudRepository<Alert, Long>, QueryDslPredicateExecutor<Alert> {
}
