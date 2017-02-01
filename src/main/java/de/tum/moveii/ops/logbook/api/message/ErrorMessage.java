package de.tum.moveii.ops.logbook.api.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
}
