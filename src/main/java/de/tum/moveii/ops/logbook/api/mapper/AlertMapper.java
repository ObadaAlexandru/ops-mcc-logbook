package de.tum.moveii.ops.logbook.api.mapper;

import de.tum.moveii.ops.logbook.alarm.model.Alert;
import de.tum.moveii.ops.logbook.api.message.AlertMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Alexandru Obada on 29/01/17.
 */
@Component
public class AlertMapper implements ResourceMapper<AlertMessage, Alert> {

    //TODO alert entity is required
    @Override
    public Alert toResource(AlertMessage message) {
        return null;
    }

    @Override
    public AlertMessage toMessage(Alert resource) {
        return null;
    }
}
