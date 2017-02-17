package de.tum.moveii.ops.logbook.api.message;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Alexandru Obada on 31/01/17.
 */
@Data
@Builder
public class NoteMessage {
    @NotNull
    private Long noteId;
    @NotNull
    private Long owner;
    @NotNull
    private String message;
    @NotNull
    private LocalDateTime createdOn;
}
