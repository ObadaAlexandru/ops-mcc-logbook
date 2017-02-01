package de.tum.moveii.ops.logbook.api.message;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
public class NoteMessage {
    @NotNull
    private Long author;
    @NotNull
    private String message;
    private LocalDateTime createdOn;
}
