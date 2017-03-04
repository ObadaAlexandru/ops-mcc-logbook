package de.tum.moveii.ops.logbook.api.message;

import de.tum.moveii.ops.logbook.alert.model.AlertState;
import lombok.Data;

/**
 * Created by Alexandru Obada on 02/03/17.
 */
@Data
public class AlertUpdateMessage {
    private AlertState newState;
    private Long newAssignee;

    public boolean isValid() {
        return newState != null || newAssignee != null;
    }
}